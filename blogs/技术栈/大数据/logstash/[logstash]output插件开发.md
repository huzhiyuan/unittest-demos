#如何开发一个logstash output插件
> https://www.elastic.co/guide/en/logstash/current/output-new-plugin.html  

为了开发logstash output插件, 你需要创建一个独立的ruby gem, 他的源码在自己的github库里
这个ruby gem可以在RubyGems.org上保存并共享

##进入正题
### 1. 为你的插件创建一个github仓库
每个logstash插件都存活在它自己的github仓库里. 为你的插件创建一个github仓库:

> 步骤: 略[见github网站创建仓库步骤]   
> 插件名可以是: logstash-output-pluginname

### 2. 使用插件生成工具

下面命令可以生成插件的基础设施,比如文件结构,模板文件, gemspec文件,以及依赖

> bin/logstash-plugin generate --type input --name xkcd --path ~/ws/elastic/plugins  
   > 1.  --type: Type of plugin - input, filter, output, or codec     
   > 2.  --name: Name for the new plugin  
   > 3.  --path: 新插件创建的位置. 如果不指定,在当前位置创建.

### 3. Copy output代码
或者,你可以使用我们托管在github上的example代码, 拷贝进入你自己本地的git目录

按需修改3个文件名:
  > cd /path/to/logstash-output-mypluginname  
  > mv logstash-output-example.gemspec logstash-output-mypluginname.gemspec  
  > mv lib/logstash/outputs/example.rb lib/logstash/outputs/mypluginname.rb  
  > mv spec/outputs/example_spec.rb spec/outputs/mypluginname_spec.rb  

####你的文件结构:  
$ tree logstash-output-mypluginname  
├── Gemfile  
├── LICENSE  
├── README.md  
├── Rakefile  
├── lib  
│   └── logstash  
│       └── outputs  
│           └── mypluginname.rb  
├── logstash-output-mypluginname.gemspec  
└── spec  
    └── outputs  
        └── mypluginname_spec.rb  

####来看下插件内容:
```ruby
# encoding: utf-8   
require "logstash/outputs/base"
require "logstash/namespace"
#依赖其他gem也是可以的,直接放在这里即可

#类名应该和插件名一致
class LogStash::Outputs::Example < LogStash::Outputs::Base
  # 这个confi_name是你的插件读取配置block中的名字
  config_name "example"

  # This sets the concurrency behavior of this plugin. By default it is :legacy, which was the standard
  # way concurrency worked before Logstash 2.4
  #
  # You should explicitly set it to either :single or :shared as :legacy will be removed in Logstash 6.0
  #
  # When configured as :single a single instance of the Output will be shared among the
  # pipeline worker threads. Access to the `#multi_receive/#multi_receive_encoded/#receive` method will be synchronized
  # i.e. only one thread will be active at a time making threadsafety much simpler.
  #
  # You can set this to :shared if your output is threadsafe. This will maximize
  # concurrency but you will need to make appropriate uses of mutexes in `#multi_receive/#receive`.
  #
  # Only the `#multi_receive/#multi_receive_encoded` methods need to actually be threadsafe, the other methods
  # will only be executed in a single thread
  concurrency :single

  public
  def register
  end # def register

  public
  # Takes an array of events
  # Must be threadsafe if `concurrency :shared` is set
  def multi_receive(events)
  end # def multi_receive
end # class LogStash::Outputs::Example
```

If you set config_name "example" in your plugin code, the corresponding Logstash configuration block would need to look like this:
如果你设置 config_name "example"在你的插件代码里, 那么相应的logstash 配置block应该像下面:

#####Configuration Parameters
  config :variable_name, :validate => :variable_type, :default => "Default value", :required => boolean, :deprecated => boolean, :obsolete => string  
  
下面是一些配置属性:

:validate - allows you to enforce passing a particular data type to Logstash for this configuration option, such as :string, :password, :boolean, :number, :array, :hash, :path (a file-system path), uri, :codec (since 1.2.0), :bytes. Note that this also works as a coercion in that if I specify "true" for boolean (even though technically a string), it will become a valid boolean in the config. This coercion works for the :number type as well where "1.2" becomes a float and "22" is an integer.  
:default - lets you specify a default value for a parameter  
:required - whether or not this parameter is mandatory (a Boolean true or  
:list - whether or not this value should be a list of values. Will typecheck the list members, and convert scalars to one element lists. 
Note that this mostly obviates the array type, though if you need lists of complex objects that will be more suitable. false)  
:deprecated - informational (also a Boolean true or false)  
:obsolete - used to declare that a given setting has been removed and is no longer functioning. 
The idea is to provide an informed upgrade path to users who are still using a now-removed setting.  


#### 插件方法 
Logstash outputs must implement the register and multi_receive methods.  
logstash output插件必须实现一个register和一些receive方法

register Method  
  public  
  def register  
  end # def register  
  
Logstash register方法类似一个初始化方法. 设计初衷是强制执行super call, 防止新手头疼
(注意: It may go away in favor of initialize, in conjunction with some enforced testing to ensure super is called.)

你也可以在这里指定实例变量 (变量以@开头). 配置变量现在在实例变量范围内,类似@message

###编译插件
At this point in the process you have coded your plugin and are ready to build a Ruby Gem from it. The following information will help you complete the process.

External dependencies
A require statement in Ruby is used to include necessary code. 
In some cases your plugin may require additional files. For example, the collectd plugin uses the types.db file provided by collectd. 
In the main directory of your plugin, a file called vendor.json is where these files are described.

The vendor.json file contains an array of JSON objects, each describing a file dependency. This example comes from the collectd codec plugin:

[{
        "sha1": "a90fe6cc53b76b7bdd56dc57950d90787cb9c96e",
        "url": "http://collectd.org/files/collectd-5.4.0.tar.gz",
        "files": [ "/src/types.db" ]
}]
sha1 is the sha1 signature used to verify the integrity of the file referenced by url.
url is the address from where Logstash will download the file.
files is an optional array of files to extract from the downloaded file. Note that while tar archives can use absolute or relative paths, 
treat them as absolute in this array. If files is not present, all files will be uncompressed and extracted into the vendor directory.
Another example of the vendor.json file is the geoip filter

用来下载这些依赖的程序叫做: rake vendor

另一类依赖是jar包依赖, 这会在 "Add a gemspec file"章节中介绍

#### 添加一个Gemfile
Gemfiles 允许 Ruby’s Bundler(打包机) 来维护你的插件的依赖, 现在, 我们最需要的是gem文件,但是如果你依赖于其他gem, 也应该把他们加在这里(Gemfiles)

Tip  
见 Bundler’s Gemfile page for more details. https://bundler.io/gemfile.html  
```
source 'https://rubygems.org'
gemspec
gem "logstash", :github => "elastic/logstash", :branch => "7.3"
```

#### 添加一个gemspec文件
gemspecs定义了哪些ruby gem 将会被编译和包含进你的插件

Tip
More information can be found on the Rubygems Specification page.
```
Gem::Specification.new do |s|
  s.name = 'logstash-output-example'
  s.version = '0.1.0'
  s.licenses = ['Apache License (2.0)']
  s.summary = "This output does x, y, z in Logstash"
  s.description = "This gem is a logstash plugin required to be installed on top of the Logstash core pipeline using $LS_HOME/bin/logstash-plugin install gemname. This gem is not a stand-alone program"
  s.authors = ["Elastic"]
  s.email = 'info@elastic.co'
  s.homepage = "http://www.elastic.co/guide/en/logstash/current/index.html"
  s.require_paths = ["lib"]

  # Files
  s.files = Dir['lib/**/*','spec/**/*','vendor/**/*','*.gemspec','*.md','CONTRIBUTORS','Gemfile','LICENSE','NOTICE.TXT']
   # Tests
  s.test_files = s.files.grep(%r{^(test|spec|features)/})

  # Special flag to let us know this is actually a logstash plugin
  s.metadata = { "logstash_plugin" => "true", "logstash_group" => "output" }

  # Gem dependencies
  s.add_runtime_dependency "logstash-core-plugin-api", ">= 1.60", "<= 2.99"
  s.add_development_dependency 'logstash-devutils'
end
```
适当的修改里面的配置来适应你的插件, 比如s.name 和 s.summary应该反映你的插件的名称和用途
s.licenses 和 s.version 在发布插件的时候同样重要,用来展示你的发布信息

Logstash and all its plugins are licensed under Apache License, version 2 ("ALv2"). If you make your plugin publicly available via RubyGems.org, please make sure to have this line in your gemspec:
logstash和他所有的插件都被apache license许可(Apache License, version 2 ("ALv2")), 如果你在RubyGems.org 上发布你的插件,请保证你的gemspec文件有以下内容:  
> s.licenses = ['Apache License (2.0)']

The gem version, designated by s.version, helps track changes to plugins over time. You should use semver versioning strategy for version numbers.

### 运行时和开发依赖
你也可以给你的依赖指定版本,包含其他插件:
```
  # Gem dependencies
  s.add_runtime_dependency "logstash-core-plugin-api", ">= 1.60", "<= 2.99"
  s.add_development_dependency 'logstash-devutils'
```

This gemspec has a runtime dependency on the logstash-core-plugin-api and requires that it have a
 version number greater than or equal to version 1.60 and less than or equal to version 2.99.

Important
All plugins have a runtime dependency on the logstash-core-plugin-api gem, and a development dependency on logstash-devutils.

### Jar dependencies
在一些场合中, 如 Elasticsearch output插件,  你的插件可能依赖一个jar文件, 这类情况,gemspec文件有如下格式:
```
  # Jar dependencies
  s.requirements << "jar 'org.elasticsearch:elasticsearch', '5.0.0'"
  s.add_runtime_dependency 'jar-dependencies'

```
有了这些定义, install程序会自动从http://mvnrepository.com 搜索需要的jar


Add Tests
Logstash loves tests. Lots of tests. If you’re using your new output plugin in a production environment, you’ll want to have some tests to ensure you are not breaking any existing functionality.

Note
A full exposition on RSpec is outside the scope of this document. Learn more about RSpec at http://rspec.info

For help learning about tests and testing, look in the spec/outputs/ directory of several other similar plugins.

Clone and test!
Now let’s start with a fresh clone of the plugin, build it and run the tests.

Clone your plugin into a temporary location Replace GITUSERNAME with your github username, and MYPLUGINNAME with your plugin name.

git clone https://github.com/GITUSERNAME/logstash-output-MYPLUGINNAME.git

alternately, via ssh: git clone git@github.com:GITUSERNAME/logstash-output-MYPLUGINNAME.git
cd logstash-output-MYPLUGINNAME
Then, you’ll need to install your plugins dependencies with bundler:

bundle install
Important
If your plugin has an external file dependency described in vendor.json, you must download that dependency before running or testing. You can do this by running:

rake vendor
And finally, run the tests:

bundle exec rspec
You should see a success message, which looks something like this:

Finished in 0.034 seconds
1 example, 0 failures
Hooray! You’re almost there! (Unless you saw failures…​ you should fix those first).

####编译和测试
现在你可以把你的插件编译成一个ruby gem了

Build
You already have all the necessary ingredients, so let’s go ahead and run the build command:

gem build logstash-output-example.gemspec
That’s it! Your gem should be built and be in the same path with the name

logstash-output-mypluginname-0.1.0.gem
The s.version number from your gemspec file will provide the gem version, in this case, 0.1.0.

Test installation
You should test install your plugin into a clean installation of Logstash. Download the latest version from the Logstash downloads page.

Untar and cd in to the directory:

curl -O https://download.elastic.co/logstash/logstash/logstash-7.3.1.tar.gz
tar xzvf logstash-7.3.1.tar.gz
cd logstash-7.3.1
Using the plugin tool, we can install the gem we just built.

Replace /my/logstash/plugins with the correct path to the gem for your environment, and 0.1.0 with the correct version number from the gemspec file.

bin/logstash-plugin install /my/logstash/plugins/logstash-output-example/logstash-output-example-0.1.0.gem
After running this, you should see feedback from Logstash that it was successfully installed:

validating /my/logstash/plugins/logstash-output-example/logstash-output-example-0.1.0.gem >= 0
Valid logstash plugin. Continuing...
Successfully installed 'logstash-output-example' with version '0.1.0'
Tip
You can also use the Logstash plugin tool to determine which plugins are currently available:

bin/logstash-plugin list
Depending on what you have installed, you might see a short or long list of plugins: inputs, codecs, filters and outputs.

Now try running Logstash with a simple configuration passed in via the command-line, using the -e flag.

Note
Your results will depend on what your output plugin is designed to do.

Congratulations! You’ve built, deployed and successfully run a Logstash output.

####Submitting your plugin to RubyGems.org and logstash-plugins
略: 需要再翻

####Publishing to RubyGems.org
略: 需要再翻

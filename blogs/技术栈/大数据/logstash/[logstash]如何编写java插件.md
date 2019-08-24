# [logstash] 如何编写java output插件
>原文: https://www.elastic.co/guide/en/logstash/current/java-output-plugin.html

> win10环境

## 一. logstash基础代码
###1. 下载源码  
编写插件需要依赖一些logstash基础代码, 这些代码需要先下载logstash源码,打包成jar使用. 
源码地址:
> https://github.com/elastic/logstash/tree/7.3 

下载命令:
>  git clone --branch 7.3 --single-branch https://github.com/elastic/logstash.git logstash-source-7.3

###2. 打包
logstash源码bin目录下执行:
> .\gradlew.bat assemble  

会生成文件: $LS_HOME/logstash-core/build/libs/logstash-core-x.y.z.jar

##二. java插件代码
代码下载地址:
> https://github.com/logstash-plugins/logstash-output-java_output_example

注意: 
> 经测试, 需要用 git checkout -b old_commit_id 检出历史版本. 原文中提到插件的beta版本适用于logstash-6.7, 而从git库下载的是ga版本, 
用git log查看历史提交, 找到最近的信息中包含beta version的检出, 然后build的时候就不报错了

在插件代码根目录下创建gradle.properties并写入如下内容
> LOGSTASH_CORE_PATH=%logstash_root_path%\\logstash-core  
  LS_HOME=%logstash_root_path%
  
注意: %%内容替换成你本地的路径 

 
  
  



# gradle

##构建脚本 build.gradle
hello world!
```groovy
task helloword << {
   println 'hello gradle world! '
}
```
> \<< 表示快捷键

##gradle 仓库下载缓慢

### 1. 全局修改
> 在.gradle文件夹下创建init.gradle, 内容如下:
```groovy
allprojects{
    repositories {
        def ALIYUN_REPOSITORY_URL = 'http://maven.aliyun.com/nexus/content/groups/public'
        def ALIYUN_JCENTER_URL = 'http://maven.aliyun.com/nexus/content/repositories/jcenter'
        all { ArtifactRepository repo ->
            if(repo instanceof MavenArtifactRepository){
                def url = repo.url.toString()
                if (url.startsWith('https://repo1.maven.org/maven2')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                    remove repo
                }
                if (url.startsWith('https://jcenter.bintray.com/')) {
                    project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                    remove repo
                }
            }
        }
        maven {
                url ALIYUN_REPOSITORY_URL
            url ALIYUN_JCENTER_URL
        }
    }
}
```
### 2. 单个项目
build.gradle , 替换mavenCentral() 为下面的
```groovy
buildscript {
    repositories {
        //mavenCentral() 卡
        allprojects {
            repositories {
                maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}
            }
        }
        jcenter()
    }
```

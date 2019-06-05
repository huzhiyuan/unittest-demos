#1. 快捷键
    Alt+Shift+M : 将选中的代码封装成方法, 如果你是一位习惯良好的程序员,这个快捷键会用的最多.
    Ctrl+E : 列出最近改动的文件, 方便跳回历史文件.
    Alt+Insert :  添加get/set方法, pom文件中添加dependency..., 方便来回查看.
    Ctrl+Shift+U : 大小写转换
    Ctrl+Shift+K : git快速push
    
    我定制的:
    Alt+Shift+E : expand all 展开类中所有方法
    Alt+Shift+C :  collapse all 折叠类中所有方法
    Alt+F : file Structure,列出类中所有方法
    ps: keymap那里是可以用快捷键搜动作的,改快捷键非常容易
    
    List<User> users = new ArrayList<User>();
    users.for 然后按tab键,即可生成for循环代码
    fori : 自动生成for循环
 # 2. 必备插件
    dependency analyzer
     
#3. 新增项目
##3.1 jdk版本问题(编译会报source版本1.5)
```xml
<properties>
  <maven.compiler.source>1.8</maven.compiler.source>
  <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```
```xml
<!--据说上面的有时不够,加上下面的配置实现双保险-->
<build>
    <plugins>
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

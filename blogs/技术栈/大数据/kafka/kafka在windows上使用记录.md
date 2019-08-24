---
0. 安装jdk和scala

1. 下载kafka和zookeeper并解压  
目录:  
kafka:  
C:\server/kafka/kafka1   
C:\server/kafka/kafka2  
C:\server/kafka/kafka3  
zookeeper:    
C:\server\zookeeper\zookeeper1  
C:\server\zookeeper\zookeeper2  
C:\server\zookeeper\zookeeper3  

启动命令:  
zk:  
cd bin > .\zkServer.cmd  
kafka:
cd bin>windows > .\kafka-server-start.bat ..\config\server.properties 

2. 关键配置
```xml
broker.id=1
port=9094
# socket server的发送缓存区
socket.send.buffer.bytes=102400
# socket server的接收缓存区
socket.receive.buffer.bytes=102400
# socket server能接收一个请求的最大字节数,防止内存溢出
socket.request.max.bytes=104857600
log.dirs=C:\\server\\kafka\\kafka3\\log
#默认分区数
num.partitions=1
#kafka消息保存小时数
log.retention.hours=168
#每个分区的最大文件大小,topic大小限制=分区数*log.retention.bytes
log.retention.bytes=1073741824
#日志目录下,每个partition又会切成多个segment,这是每个segment的上限设置
log.segment.bytes=1073741824
#zookeeper.connect=localhost:2181,localhost:2182,localhost:2183
zookeeper.connect=localhost:2181
zookeeper.connection.timeout.ms=6000
```

# tiger
 
```
 1、配置mysql,用户名：root，密码：12345678 启动mysql ./usr/local/Cellar/mysql/8.0.32/bin/mysql.server start
 2、配置redis，启动redis /usr/local/opt/redis@6.2/bin/redis-server /usr/local/etc/redis.conf
 3、启动zk，/usr/local/bin/zookeeper-server-start /usr/local/etc/zookeeper/zoo.cfg
 4、启动kafka， /usr/local/opt/kafka/bin/kafka-server-start /usr/local/etc/kafka/server.properties
 5、启动项目，测试路径
 ```
``` 
curl --location --request GET 'http://127.0.0.1:8888/demo/getUser' \
 --header 'Content-Type: application/json' \
 --data '{"user_id":"1"}'
```
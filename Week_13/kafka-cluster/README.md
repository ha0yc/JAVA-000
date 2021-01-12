## 配置
备份出两个配置文件，即server.properties.
注意需要修改其中的broker的id，log存储位置，partitions个数和监听端口。示例如下所示
```
broker.id=2
listeners=PLAINTEXT://:9094
log.dir=/temp/kafka-logs-9094
num.partitions=2
```
其中，log存储位置不修改，所有的server都会从同一个log位置读取元信息，导致无法启动。从单broker变成多broker时，一定要清理zk。

## 启动
```
~/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh server.properties &
~/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh server-9093.properties &
~/tools/kafka_2.12-2.7.0/bin/kafka-server-start.sh server-9094.properties &
```

## 验证
1. 将前边写的spring-kafka-demo中的kafka地址修改为3个broker地址
```
spring.kafka.bootstrap-servers=127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094
```
按照单broker方式重新启动，并测试。验证单机多broker部署方式无问题。

2. 执行创建新topic命令，其中副本因子为3，partition数量为2
```
~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 2 --topic cluster-test
```
log提示创建成功。

查询现有topic列表
```
haoyongchen@ha0yc  ~/Documents/code/JAVA-000/Week_13/kafka-cluster   main ●✚  ~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-topics.sh --list --bootstrap-server localhost:9092
__consumer_offsets
cluster-test
test
```

查询topic分布
```
haoyongchen@ha0yc  ~/Documents/code/JAVA-000/Week_13/kafka-cluster   main ●✚  ~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-topics.sh --zookeeper 127.0.0.1:2181 --topic cluster-test --describe
Topic: cluster-test	PartitionCount: 2	ReplicationFactor: 3	Configs:
	Topic: cluster-test	Partition: 0	Leader: 1	Replicas: 1,0,2	Isr: 1,0,2
	Topic: cluster-test	Partition: 1	Leader: 0	Replicas: 0,2,1	Isr: 0,2,1
```
可以看出，一共有两个partition。其中partition0的leader在broker1上，三个副本分别在1，0，2上。其中partition1的leader在broker0上，三个副本分别在0，2，1上。

由此可知kafka单机多broker集群部署成功。

继续测试消息生产和消费。

向Broker(id=0)的Topic=clsuter-test发送消息。
```
⚙ haoyongchen@ha0yc  ~/Documents/code/JAVA-000/Week_13/kafka-cluster   main ●✚  ~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic cluster-test
>hello broker1,2
```
消费Broker1的消息
```
haoyongchen@ha0yc  ~/Documents/code/JAVA-000/Week_13/kafka-cluster   main ●✚  ~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-console-consumer.sh --bootstrap-server localhost:9093 --topic cluster-test --from-beginning
[2021-01-12 22:41:13,276] INFO [GroupCoordinator 2]: Preparing to rebalance group console-consumer-91888 in state PreparingRebalance with old generation 0 (__consumer_offsets-15) (reason: Adding new member consumer-console-consumer-91888-1-7253449d-a8c5-42f0-83c1-c806f23158ad with group instance id None) (kafka.coordinator.group.GroupCoordinator)
[2021-01-12 22:41:13,290] INFO [GroupCoordinator 2]: Stabilized group console-consumer-91888 generation 1 (__consumer_offsets-15) (kafka.coordinator.group.GroupCoordinator)
[2021-01-12 22:41:13,309] INFO [GroupCoordinator 2]: Assignment received from leader for group console-consumer-91888 for generation 1 (kafka.coordinator.group.GroupCoordinator)
hello broker1,2
```
可以看出，由于新增了consumer group所以进行了rebalance。rebalance结束之后消费到了我们之前发出的消息.

消费broker2的消息
```
⚙ haoyongchen@ha0yc  ~/Documents/code/JAVA-000/Week_13/kafka-cluster   main ●✚  ~/Documents/tools/kafka_2.12-2.7.0/bin/kafka-console-consumer.sh --bootstrap-server localhost:9094 --topic cluster-test --from-beginning
[2021-01-12 22:43:42,637] INFO [GroupCoordinator 0]: Preparing to rebalance group console-consumer-10055 in state PreparingRebalance with old generation 0 (__consumer_offsets-22) (reason: Adding new member consumer-console-consumer-10055-1-84280cb6-4bd3-43ec-9156-227d423d6741 with group instance id None) (kafka.coordinator.group.GroupCoordinator)
[2021-01-12 22:43:42,659] INFO [GroupCoordinator 0]: Stabilized group console-consumer-10055 generation 1 (__consumer_offsets-22) (kafka.coordinator.group.GroupCoordinator)
[2021-01-12 22:43:42,688] INFO [GroupCoordinator 0]: Assignment received from leader for group console-consumer-10055 for generation 1 (kafka.coordinator.group.GroupCoordinator)
hello broker1,2
```
消费者也从broker2上消费到了消息。

## 总结
1. 在启动kafka的单机多broker之前，一定要将配置文件中的log.dir分别配置好，避免冲突。
2. 因为两次消费者都是新建，导致消费之前消费者数目发生变化，所以导致了rebalance。
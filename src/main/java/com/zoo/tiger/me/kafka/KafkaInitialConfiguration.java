package com.zoo.tiger.me.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class KafkaInitialConfiguration {

    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    // @Bean
    // public NewTopic initialTopic() {
    //     return new NewTopic("topic-tiger",3, (short) 1 );
    // }

    // 在某些情况下，Spring容器在初始化Bean的时候，希望在初始化bean前和销毁bean前进行一些资源的加载和释放的操作。可以通过一下三种方式完成。
    // Bean的方法加上@PostConstruct和@PreDestroy注解
    // 在xml中定义init-method和destory-method方法
    // Bean实现InitializingBean和DisposableBean接口
    @PostConstruct
    public void init() {
        System.out.println("topic1 init method ...");
    }

    @PreDestroy
    public void cleanUp() {
        System.out.println("topic1 cleanUp method ...");
    }


    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic("topic1", 10, (short) 1);
    }

}
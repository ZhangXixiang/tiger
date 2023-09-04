package com.zoo.tiger.me.rabbitMQ;

import com.alibaba.fastjson2.JSON;
import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
// 详细的配置
// @RabbitListener(bindings = {@QueueBinding(
//         value = @Queue(value = "aaa",durable = "true"),//如果不括号中不指定队列名称，那么这时候创建的就是临时队列，当消费者连接断开的时候，该队列就会消失
//         exchange = @Exchange(value = "abd",durable = "true",type = "direct"),
//         key = "")})
@Slf4j
@RabbitListener(queues = "direct-abd-1")

public class RabbitMQListener {
    /**
     * 基于推送模式的消费者消费消息
     */
    /*@Component
    public class DirectConsumer {

    }*/
    @RabbitHandler
    public void process(String str, Channel channel, Message message) throws IOException {
        log.info("direct-abd-1消费者接受到的消息是：" + str);
        // 拉取模式 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //推送模式
        channel.basicConsume("direct-abd-1",new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) { }

            @Override
            public void handleCancelOk(String consumerTag) { }

            @Override
            public void handleCancel(String consumerTag) throws IOException {
            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
            }

            @Override
            public void handleRecoverOk(String consumerTag) { }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            }
        });
    }

    /*@RabbitListener(queues = "direct-abd-2")
    @Component
    public class DirectConsumer2 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("direct-abd-2消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }*//*

    @RabbitListener(queues = "fanout-abd-1")
    @Component
    public class FanoutConsumer {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("fanout-abd-1消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = "fanout-abd-2")
    @Component
    public class FanoutConsumer2 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("fanout-abd-2消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = "fanout-abd-3")
    @Component
    public class FanoutConsumer3 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("fanout-abd-3消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = "topic-abd-1")
    @Component
    public class TopicConsumer1 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("topic-abd-1消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = "topic-abd-2")
    @Component
    public class TopicConsumer2 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("topic-abd-2消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = "topic-abd-3")
    @Component
    public class TopicConsumer3 {
        @RabbitHandler
        public void process(String str, Channel channel, Message message) throws IOException {
            log.info("topic-abd-3消费者接受到的消息是：" + str);
            // 由于配置设置了手动应答，所以这里要进行一个手动应答。注意：如果设置了自动应答，这里又进行手动应答，会出现double ack，那么程序会报错。
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }*/
}

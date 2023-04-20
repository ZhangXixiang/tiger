package com.zoo.tiger.me.rabbitMQ;

import com.alibaba.fastjson2.JSON;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQListener implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @Autowired
    private RabbitMQHelper rabbitMQHelper;

    //使用异常处理机制. 发生指定类型异常就加入到队列重试. 无限重试
    //执行过程中的意外断开, 依赖mq自动ack机制
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info(getMsg(message));

    }

    public static String getMsg(Message message) {
        try {
            if (message == null) {
                return null;
            }
            byte[] body = message.getBody();
            return new String(body, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("消息解析异常: {}", JSON.toJSONString(message), e);
        }
        return null;
    }


}

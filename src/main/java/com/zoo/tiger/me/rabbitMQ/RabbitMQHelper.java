package com.zoo.tiger.me.rabbitMQ;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tiger
 */
@Component
@Slf4j
public class RabbitMQHelper {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageWithEmptyRoutingKey(String exchangeName, String message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, StringUtils.EMPTY, message);
            log.info("发送消息: {}", message);
        } catch (Exception e) {
            log.error(JSON.toJSONString(e.getStackTrace()));
            String error = e.getMessage();
            log.error("添加消息到队列失败. 失败原因: {}. 消息内容: {}", error, message);
        }
    }



}

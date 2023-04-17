package com.zoo.tiger.me.config;

import com.zoo.tiger.me.redis.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 配置文件
 *
 * @Tiger
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.pool.max-active}")
    private Integer maxActive;
    @Value("${redis.pool.max-wait}")
    private Integer maxWait;
    @Value("${redis.pool.min-idle}")
    private Integer minIdle;
    @Value("${redis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${redis.password:}")
    private String password;

    @Bean(destroyMethod = "destroy", name = "redisConnectionFactory")
    public JedisConnectionFactory redisConnectionFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(true);
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.setHostName(host);
        factory.setPort(port);
        if (StringUtils.isNotEmpty(password)) {
            factory.setPassword(password);
        }
        return factory;
    }

    @Bean(name = "redisClient")
    public JedisClient redisClient(JedisConnectionFactory redisConnectionFactory) {
        return new JedisClient(redisConnectionFactory);
    }
}

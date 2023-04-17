package com.zoo.tiger.me.redis.jedis;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.zoo.tiger.me.sentinel.SentinelRedisHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.params.SetParams;

import java.util.*;


/**
 * Jedis方式连接redis
 * @Tiger
 */
public class JedisClient {
    private static final Logger logger = LoggerFactory.getLogger(JedisClient.class);

    private JedisConnectionFactory jedisConnectionFactory;

    public JedisClient(JedisConnectionFactory factory) {
        this.jedisConnectionFactory = factory;
        logger.info("RedisClient initial...");
    }

    @SentinelResource(value = "redisClient-get",blockHandler = "get",
            blockHandlerClass = SentinelRedisHandler.class)
    public Object get(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            String valueJson = jedisConnection.getNativeConnection().get(key);
            return JSON.parse(valueJson);
        } finally {
            returnResource(jedisConnection);
        }
    }

    /**
     * @param key
     * @return
     */
    @SentinelResource(value = "redisClient-exists",blockHandler = "exists",
        blockHandlerClass = SentinelRedisHandler.class)
    public boolean exists(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().exists(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    /**
     * @param key
     * @return
     */
    public Long incr(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().incr(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-expire",blockHandler = "expire",
            blockHandlerClass = SentinelRedisHandler.class)
    public Long expire(String key, int seconds) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().expire(key, seconds);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Long expireAt(String key, long unixTime) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            Long result = jedisConnection.getNativeConnection().expireAt(key, unixTime);
            return result;
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-set",blockHandler = "set",
        blockHandlerClass = SentinelRedisHandler.class)
    public String set(String key, String value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            String valueJson;
            valueJson = JSON.toJSONString(value, JSONWriter.Feature.WriteClassName);
            return jedisConnection.getNativeConnection().set(key, valueJson);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-setEx",blockHandler = "setEx",
        blockHandlerClass = SentinelRedisHandler.class)
    public String setEx(String key, int seconds, Object value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            String valueJson;
            valueJson = JSON.toJSONString(value, JSONWriter.Feature.WriteClassName);

            return jedisConnection.getNativeConnection().setex(key, seconds, valueJson);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-del",blockHandler = "del",
        blockHandlerClass = SentinelRedisHandler.class)
    public Long del(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().del(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-getString",blockHandler = "getString",
        blockHandlerClass = SentinelRedisHandler.class)
    public String getString(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().get(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-setStringEx",blockHandler = "setStringEx",
        blockHandlerClass = SentinelRedisHandler.class)
    public String setStringEx(String key, int seconds, String value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().setex(key, seconds, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    /****
     * map
     */

    @SentinelResource(value = "redisClient-hset",blockHandler = "hset",
        blockHandlerClass = SentinelRedisHandler.class)
    public Long hset(String key, String field, String value) {
        Long result;

        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            result = jedisConnection.getNativeConnection().hset(key, field, value);
        } catch (Exception e) {
            result = -1L;
        } finally {
            returnResource(jedisConnection);
        }

        return result;
    }

    public Long hincrby(String key, String field, long value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hincrBy(key, field, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-hdel",blockHandler = "hdel",
        blockHandlerClass = SentinelRedisHandler.class)
    public Long hdel(String key, String... fields) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hdel(key, fields);
        } catch (Exception e) {
            return -1L;
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-hget",blockHandler = "hget",
        blockHandlerClass = SentinelRedisHandler.class)
    public String hget(String key, String field) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hget(key, field);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-hexists",blockHandler = "hexists",
            blockHandlerClass = SentinelRedisHandler.class)
    public boolean hexists(String key, String field) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hexists(key, field);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public <T> T hget(String key, String field, Class<T> tClass) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            String value = jedisConnection.getNativeConnection().hget(key, field);
            return JSON.parseObject(value, tClass);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-hgetAll",blockHandler = "hgetAll",
        blockHandlerClass = SentinelRedisHandler.class)
    public Map<String, String> hgetAll(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hgetAll(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public <T> Map<String, T> hgetAll(String key, Class<T> tClass) {
        Map<String, T> ret = new HashMap<String, T>();
        Map<String, String> all = hgetAll(key);
        for (String k : all.keySet()) {
            String v = all.get(k);
            ret.put(k, JSON.parseObject(v, tClass));
        }
        return ret;
    }

    @SentinelResource(value = "redisClient-hgetAllValue",blockHandler = "hgetAllValue",
        blockHandlerClass = SentinelRedisHandler.class)
    public List<String> hgetAllValue(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hvals(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public <T> List<T> hgetAllValue(String key, Class<T> tClass) {
        List<T> ret = new ArrayList<T>();
        List<String> values = hgetAllValue(key);
        for (String v : values) {
            ret.add(JSON.parseObject(v, tClass));
        }
        return ret;
    }

    public List<String> hmget(String key, List<String> fields) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hmget(key, fields.toArray(new String[fields.size()]));
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-hmset",blockHandler = "hmset",
        blockHandlerClass = SentinelRedisHandler.class)
    public String hmset(String key, Map<String, String> fieldMap) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().hmset(key, fieldMap);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Long zrank(String key, String member) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().zrank(key, member);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Long rpush(String key, String value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().rpush(key, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-lpush",blockHandler = "lpush",
            blockHandlerClass = SentinelRedisHandler.class)
    public Long lpush(String key, String[] value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().lpush(key, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-lrange",blockHandler = "lrange",
        blockHandlerClass = SentinelRedisHandler.class)
    public List<String> lrange(String key, int start, int end) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().lrange(key, start, end);
        } finally {
            returnResource(jedisConnection);
        }
    }

    @SentinelResource(value = "redisClient-setnx",blockHandler = "setnx",
        blockHandlerClass = SentinelRedisHandler.class)
    public boolean setnx(String key, String value, int expireTime) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            // return "OK".equals(jedisConnection.getNativeConnection().set(key, value, "nx", "ex", expireTime));
            return "OK".equals(jedisConnection.getNativeConnection().set(key, value, new SetParams().nx().ex(expireTime)));
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Long setnx(String key, String value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().setnx(key, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public String getSet(String key, String value) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().getSet(key, value);
        } finally {
            returnResource(jedisConnection);
        }
    }

    //set
    public Long sAdd(String key, String... members) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().sadd(key, members);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Long sRem(String key, String... members) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().srem(key, members);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public Set<String> sMembers(String key) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().smembers(key);
        } finally {
            returnResource(jedisConnection);
        }
    }

    public boolean isMembers(String key, String member) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().sismember(key, member);
        } finally {
            returnResource(jedisConnection);
        }
    }

    private JedisConnection getJedisConnection() {
        return (JedisConnection) jedisConnectionFactory.getConnection();
    }

    /****
     * @param key
     */
    private void checkKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Cache key is null or not a length of 0.");
        }
    }

    /**
     * 返还Jedis到连接池
     *
     * @param jedisConnection
     */
    private void returnResource(JedisConnection jedisConnection) {
        if (jedisConnection != null) {
            jedisConnection.close();
        }
    }

    /**
     * SET方法第三个参数
     * NX：key不存在则执行SET
     * XX：key存在则执行SET
     */
    private static final String SET_IF_NOT_EXIST = "NX";

    /**
     * SET方法第四个参数：控制第五个参数的时间单位
     * EX：秒
     * PX：毫秒
     */
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 设置分布式锁
     *
     * @param key   需要上锁的key。
     * @param value 上锁的主人，可以为IP、UUID、HOSTNAME等。
     * @param time  锁的过期时间。毫秒
     * @return String "OK"：获取锁成功；"NIL"：获取锁失败。
     */
    @SentinelResource(value = "redisClient-lock",blockHandler = "lock",
        blockHandlerClass = SentinelRedisHandler.class)
    public String lock(String key, String value, long time) {
        checkKey(key);
        JedisConnection jedisConnection = null;
        try {
            jedisConnection = getJedisConnection();
            // return jedisConnection.getNativeConnection().set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, time);
            return jedisConnection.getNativeConnection().set(key, value, new SetParams().ex(1000L).nx());
        } finally {
            returnResource(jedisConnection);
        }
    }

    /**
     * 释放分布式锁
     */
    public Object unlock(String lockKey, String requestId) {
        checkKey(lockKey);
        JedisConnection jedisConnection = null;
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        try {
            jedisConnection = getJedisConnection();
            return jedisConnection.getNativeConnection().eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        } finally {
            returnResource(jedisConnection);
        }
    }
}

package com.zoo.tiger.me.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zoo.tiger.me.redis.jedis.JedisClient;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by tu on 2021/5/14.
 */
public final class SentinelRedisHandler {
    private static final Logger logger = LoggerFactory.getLogger(JedisClient.class);
    private static final String OK = "OK";
    private static final String EMPTY = "";


    public static Object get(String key, BlockException exception) {
        warningLog(key, exception);
        return null;
    }

    public static Long lpush(String key, String[] value, BlockException exception) {
        warningLog(key, exception);
        return 1L;
    }

    public static Long expire(String key, int seconds, BlockException exception) {
        warningLog(key, exception);
        return -1L;
    }

    public static boolean exists(String key, BlockException exception) {
        warningLog(key, exception);
        return false;
    }

    public static Long ttl(String key, BlockException exception) {
        warningLog(key, exception);
        return -1L;
    }

    public static String set(String key, String value, BlockException exception) {
        warningLog(key, exception);
        return OK;
    }


    public static Long del(String key, BlockException exception) {
        warningLog(key, exception);
        return 1L;
    }

    public static String getString(String key, BlockException exception) {
        warningLog(key, exception);
        return EMPTY;
    }

    public static String setStringEx(String key, int seconds, String value, BlockException exception) {
        warningLog(key, exception);
        return OK;
    }

    public static String setEx(String key, int seconds, Object value, BlockException exception) {
        warningLog(key, exception);
        return OK;
    }

    public static Long hset(String key, String field, String value, BlockException exception) {
        warningLog(key, exception);
        return 1L;
    }

    public static Long hdel(String key, String fields, BlockException exception) {
        warningLog(key, exception);
        return 1L;
    }

    public static String hget(String key, String field, BlockException exception) {
        warningLog(key, exception);
        return EMPTY;
    }

    public boolean hexists(String key, String field, BlockException exception) {
        warningLog(key, exception);
        return false;
    }

    public static Map<String, String> hgetAll(String key, BlockException exception) {
        warningLog(key, exception);
        return Collections.emptyMap();
    }

    public static List<String> hgetAllValue(String key, BlockException exception) {
        warningLog(key, exception);
        return Collections.emptyList();
    }

    public static String hmset(String key, Map<String, String> fieldMap, BlockException exception) {
        warningLog(key, exception);
        return EMPTY;
    }

    public static List<String> lrange(String key, int start, int end, BlockException exception) {
        warningLog(key, exception);
        return Collections.emptyList();
    }

    public static boolean setnx(String key, String value, int expireTime, BlockException exception) {
        warningLog(key, exception);
        return true;
    }

    public static String lock(String key, String value, long time, BlockException exception) {
        warningLog(key, exception);
        return "OK";
    }

    private static void warningLog(String key, BlockException exception) {
        logger.warn("redis {} 异常达到阀值, 降级执行, key: {}, error: {}",
            Thread.currentThread().getStackTrace()[2].getMethodName(), key, ExceptionUtils.getStackTrace(exception));
    }
}

package com.zoo.tiger.me.test.cache;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhCacheExample {


    /**
     * ecache
     * @param args
     */
    public static void main(String[] args) {
        //    创建缓存管理器</span>
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        //    初始化    EhCache</span>
        cacheManager.init();
        //    创建缓存（存储器）</span>
        Cache myCache = cacheManager.createCache("MYCACHE",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class,
                        ResourcePoolsBuilder.heap(10)));        //    设置缓存的最大容量
        //    设置缓存</span>
        myCache.put("key", "Hello,Java.");
        //    读取缓存</span>
        String value = (String) myCache.get("key");
        //    输出缓存</span>
        System.out.println(value);
        //    关闭缓存</span>
        cacheManager.close();
    }

}

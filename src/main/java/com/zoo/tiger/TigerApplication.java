package com.zoo.tiger;

import com.zoo.tiger.me.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

import java.util.concurrent.Callable;

@SpringBootApplication
// 不加这个也能扫描到mapper
@MapperScan(basePackages = {"com.zoo.tiger.*"})
@Import({com.zoo.tiger.me.config.SelfConfigTest.class})
@Slf4j
public class TigerApplication implements CommandLineRunner {

    private static volatile int i = 0;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        // 自行发布一个事件。
        applicationContext.publishEvent(new ApplicationEvent("selfEvent") {});
    }

    public static void main(String[] args) {
        // 启动方式2 这样启动会报错找不到配置redis.host还需要再看下
        /*AnnotationConfigApplicationContext springApplication = new AnnotationConfigApplicationContext(TigerApplication.class);
        // MyBeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor = new MyBeanDefinitionRegistryPostProcessor();
        // springApplication.addBeanFactoryPostProcessor(beanDefinitionRegistryPostProcessor);
        springApplication.refresh();
        SysUser bean = springApplication.getBean(SysUser.class);
        System.out.println(bean);*/

        // 启动方式1
        /*SpringApplication springApplication1 = new SpringApplication(TigerApplication.class);
        springApplication1.setBannerMode(Banner.Mode.OFF);
        springApplication1.run(args);*/

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 100; k++) {
                    i++;
                }
                log.info("thread");
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100; j++) {
                    i++;
                }
                log.info("thread1");
            }
        });
        thread.start();
        thread1.start();
    }


}



    /*class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(SysUser.class).getBeanDefinition();
            registry.registerBeanDefinition("user", beanDefinition);
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        }

    }*/

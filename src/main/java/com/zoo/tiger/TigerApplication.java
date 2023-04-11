package com.zoo.tiger;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

@SpringBootApplication
// 不加这个也能扫描到mapper
@MapperScan(basePackages = {"com.zoo.tiger.*"})
public class TigerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(TigerApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
		// SpringApplication.run(TigerApplication.class, args);
	}



	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		//自行发布一个事件。
		applicationContext.publishEvent(new ApplicationEvent("selfEvent") {
		});
	}

}

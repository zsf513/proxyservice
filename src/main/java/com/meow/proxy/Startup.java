package com.meow.proxy;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.meow.proxy.dao")
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.meow")
public class Startup {
	private final static Logger LOG = LoggerFactory.getLogger(Startup.class);

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		LOG.info("启动成功");
	}
}

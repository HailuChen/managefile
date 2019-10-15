package com.chenhailu.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource(value = "classpath:Spring.xml")
@EnableDubbo
public class ConsumerApplication /*extends SpringBootServletInitializer*/ {


	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

/*

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ConsumerApplication.class);
	}
*/

}

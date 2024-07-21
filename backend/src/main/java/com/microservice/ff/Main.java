package com.microservice.ff;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EntityScan(basePackages = {"com.microservice.ff"})
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class,
		OpenAiAutoConfiguration.class,
		RedisAutoConfiguration.class
})
@EnableScheduling
@EnableAsync
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("""
				8888888888 8888888888      .d8888b.                            888                 .d8888b.           888 888                   888                  \s
				888        888            d88P  Y88b                           888                d88P  Y88b          888 888                   888                  \s
				888        888            888    888                           888                888    888          888 888                   888                  \s
				8888888    8888888        888        888d888 888  888 88888b.  888888 .d88b.      888         .d88b.  888 888  .d88b.   .d8888b 888888 .d88b.  888d888
				888        888            888        888P"   888  888 888 "88b 888   d88""88b     888        d88""88b 888 888 d8P  Y8b d88P"    888   d88""88b 888P" \s
				888        888            888    888 888     888  888 888  888 888   888  888     888    888 888  888 888 888 88888888 888      888   888  888 888   \s
				888        888            Y88b  d88P 888     Y88b 888 888 d88P Y88b. Y88..88P     Y88b  d88P Y88..88P 888 888 Y8b.     Y88b.    Y88b. Y88..88P 888   \s
				888        888             "Y8888P"  888      "Y88888 88888P"   "Y888 "Y88P"       "Y8888P"   "Y88P"  888 888  "Y8888   "Y8888P  "Y888 "Y88P"  888   \s
				                                                  888 888                                                                                            \s
				                                             Y8b d88P 888                                                                                            \s
				                                              "Y88P"  888                                                                                            \s
				""");
	}

}

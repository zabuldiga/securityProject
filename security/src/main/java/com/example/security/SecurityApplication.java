package com.example.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableJpaRepositories(basePackages = {"com/example/security/repository","products.example"})
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication(scanBasePackages = {
		"com.example.security",
		"products.example"
})
@EntityScan(basePackages = {
		"products.example",
		"com/example/security/entity"
})
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner testRedis(RedisTemplate<String, Object> redisTemplate) {
//		return args -> {
//			try {
//				redisTemplate.opsForValue().set("ping", "pong");
//				Object response = redisTemplate.opsForValue().get("ping");
//				System.out.println("✅ Успешное подключение к Redis! Ответ: " + response);
//			} catch (Exception e) {
//				System.err.println("❌ Ошибка подключения к Redis: " + e.getMessage());
//			}
//		};
//	}

}

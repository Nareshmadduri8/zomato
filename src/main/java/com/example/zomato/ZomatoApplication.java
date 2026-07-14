package com.example.zomato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ZomatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZomatoApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

	    RedisTemplate<String, Object> redistemplate = new RedisTemplate<>();
	    redistemplate.setConnectionFactory(connectionFactory);
	    redistemplate.setKeySerializer(new StringRedisSerializer());
	    redistemplate.setValueSerializer(new StringRedisSerializer());
	    redistemplate.setHashKeySerializer(new StringRedisSerializer());
	    redistemplate.setHashValueSerializer(new StringRedisSerializer());
	    redistemplate.afterPropertiesSet();
	    return redistemplate;
	}

}

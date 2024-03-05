package com.example.demo.chat.repository;

import com.example.demo.chat.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {
	@Bean
    public Mapper mapper() {
        return new Mapper();
    }
}

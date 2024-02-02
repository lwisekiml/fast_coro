package com.uno.getinline.config;

import com.uno.getinline.repository.EventRepository;
import org.springframework.context.annotation.Bean;

public class RepositoryConfig {

    @Bean
    public EventRepository eventRepository() {
        return new EventRepository() {};
    }
}

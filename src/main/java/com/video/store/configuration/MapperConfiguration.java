package com.video.store.configuration;

import com.video.store.api.mapping.MovieMapper;
import com.video.store.api.mapping.MovieMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public MovieMapper movieMapperConfiguration() {
        return new MovieMapperImpl();
    }
}
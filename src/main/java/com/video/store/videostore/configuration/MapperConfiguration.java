package com.video.store.videostore.configuration;

import com.video.store.videostore.api.mapping.MovieMapper;
import com.video.store.videostore.api.mapping.MovieMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public MovieMapper movieMapperConfiguration() {
        return new MovieMapperImpl();
    }
}
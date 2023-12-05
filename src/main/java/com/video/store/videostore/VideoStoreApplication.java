package com.video.store.videostore;

import com.video.store.videostore.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoStoreApplication implements CommandLineRunner {

    @Autowired
    private MovieService movieService;

    public static void main(String[] args) {
        SpringApplication.run(VideoStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.movieService.save("Star Wars episode IV", "The fate of the galaxy is forever changed when " +
                "Luke Skywalker discovers his powerful connection to a mysterious Force, and blasts into space to rescue " +
                "Princess Leia.");
    }
}

package com.songify_rest_template_client.config;

import com.songify_rest_template_client.received.Song;
import com.songify_rest_template_client.client.SongifyClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Log4j2
@Configuration
public class RunnerConfig {
    
    @Bean
    CommandLineRunner init(SongifyClient songifyClient) {
        return args -> {
            log.info("Requesting all songs...");
            Map<Integer, Song> allSongs = songifyClient.getAllSongs();
            allSongs.forEach(
                    (id, value) -> log.info("ID {}: '{}' of '{}'", id, value.name(), value.artist())
            );
            log.info("Requesting all songs limited to 3...");
            Map<Integer, Song> allSongsLimited = songifyClient.getAllSongsLimited(3);
            allSongsLimited.forEach(
                    (id, value) -> log.info("ID {}: '{}' of '{}'", id, value.name(), value.artist())
            );
        };
    }
}

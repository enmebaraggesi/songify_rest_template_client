package com.songify_rest_template_client.config;

import com.songify_rest_template_client.client.SongifyClient;
import com.songify_rest_template_client.received.Song;
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
            log.info("Requesting all songs limited to 3...");
            Map<Integer, Song> allSongsLimited = songifyClient.getAllSongsLimited(3);
            allSongsLimited.forEach(
                    RunnerConfig::getInformed
            );
            log.info("Adding new song...");
            Song newSong = new Song("newSong", "newArtist");
            Song postedNewSong = songifyClient.postNewSong(newSong);
            log.info(postedNewSong);
            log.info("Requesting all songs...");
            Map<Integer, Song> allSongs = songifyClient.getAllSongs();
            allSongs.forEach(
                    RunnerConfig::getInformed
            );
            Integer idForSongById = 2;
            log.info("Requesting song by ID {}...", idForSongById);
            Song songById = songifyClient.getSongById(idForSongById);
            getInformed(idForSongById, songById);
        };
    }
    
    private static void getInformed(Integer id, Song song) {
        log.info("ID {}: '{}' of '{}'", id, song.name(), song.artist());
    }
}

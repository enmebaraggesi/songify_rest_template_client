package com.songify_rest_template_client.config;

import com.songify_rest_template_client.client.Song;
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
            log.info("Step 1. Requesting all songs limited to 3...");
            Map<Integer, Song> allSongsLimited = songifyClient.getAllSongsLimited(3);
            allSongsLimited.forEach(
                    RunnerConfig::getInformed
            );
            
            log.info("Step 2. Adding new song...");
            Song newSong = new Song("newSong", "newArtist");
            Song postedNewSong = songifyClient.postNewSong(newSong);
            log.info(postedNewSong);
            
            log.info("Step 3. Requesting all songs...");
            Map<Integer, Song> allSongs = songifyClient.getAllSongs();
            allSongs.forEach(
                    RunnerConfig::getInformed
            );
            
            Integer idForSongById = 2;
            Song updatedSong = new Song("updatedSong", "updatedArtist");
            log.info("Step 4. Updating song by ID {}...", idForSongById);
            Song updatedSongById = songifyClient.putSongById(idForSongById, updatedSong);
            getInformed(idForSongById, updatedSongById);
            
            log.info("Step 5. Requesting song by ID {}...", idForSongById);
            Song songById = songifyClient.getSongById(idForSongById);
            getInformed(idForSongById, songById);
            
            log.info("Step 6. Deleting song with ID {}... ", idForSongById);
            String message = songifyClient.deleteSongById(idForSongById);
            log.info(message);
            
            log.info("Step 7. Requesting all songs...");
            Map<Integer, Song> allSongs2 = songifyClient.getAllSongs();
            allSongs2.forEach(
                    RunnerConfig::getInformed
            );
            
            // For more info see --> SongifyService.class
//            Integer idForPatch = 3;
//            Song patchedSong = new Song("patchedSong", null);
//            log.info("Patching song by ID {}...", idForPatch);
//            String message = songifyClient.patchSongById(idForPatch, patchedSong);
//            log.info(message);
        };
    }
    
    private static void getInformed(Integer id, Song song) {
        log.info("ID {}: '{}' of '{}'", id, song.name(), song.artist());
    }
}

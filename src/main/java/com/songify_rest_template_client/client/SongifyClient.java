package com.songify_rest_template_client.client;

import com.songify_rest_template_client.received.Song;
import com.songify_rest_template_client.service.SongifyService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
public class SongifyClient {
    
    private final SongifyService songifyService;
    
    public SongifyClient(SongifyService songifyService) {
        this.songifyService = songifyService;
    }
    
    @GetMapping
    public Map<Integer, Song> getAllSongs() {
        return songifyService.getAllSongs();
    }
    
    @GetMapping
    public Map<Integer, Song> getAllSongsLimited(Integer limit) {
        return songifyService.getAllSongsLimited(limit);
    }
    
    @GetMapping
    public Song getSongById(Integer id) {
        return songifyService.getSongById(id);
    }
    
    @PostMapping
    public Song postNewSong(Song song) {
        return songifyService.postNewSong(song);
    }
    
    @PutMapping
    public Song putSongById(Integer id, Song song) {
        return songifyService.putSongById(id, song);
    }
    
    // For more info see --> SongifyService.class
//    @PatchMapping
//    public String patchSongById(Integer id, Song song) {
//        return songifyService.patchSongById(id, song);
//    }
}

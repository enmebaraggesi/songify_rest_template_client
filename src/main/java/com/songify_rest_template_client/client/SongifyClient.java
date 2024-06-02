package com.songify_rest_template_client.client;

import com.songify_rest_template_client.received.Song;
import com.songify_rest_template_client.service.SongifyService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

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
}

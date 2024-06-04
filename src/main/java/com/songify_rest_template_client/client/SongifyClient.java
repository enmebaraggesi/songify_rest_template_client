package com.songify_rest_template_client.client;

import com.songify_rest_template_client.service.SongifyService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SongifyClient {
    
    private final SongifyService songifyService;
    
    public SongifyClient(SongifyService songifyService) {
        this.songifyService = songifyService;
    }
    
    public Map<Integer, Song> getAllSongs() {
        return songifyService.getAllSongs();
    }
    
    public Map<Integer, Song> getAllSongsLimited(Integer limit) {
        return songifyService.getAllSongsLimited(limit);
    }
    
    public Song getSongById(Integer id) {
        return songifyService.getSongById(id);
    }
    
    public Song postNewSong(Song song) {
        return songifyService.postNewSong(song);
    }
    
    public Song putSongById(Integer id, Song song) {
        return songifyService.putSongById(id, song);
    }
    
    public String deleteSongById(Integer id) {
        return songifyService.deleteSongById(id);
    }
    
    // For more info see --> SongifyService.class
//    public String patchSongById(Integer id, Song song) {
//        return songifyService.patchSongById(id, song);
//    }
}

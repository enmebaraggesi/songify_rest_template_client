package com.songify_rest_template_client.service;

import com.songify_rest_template_client.client.Song;
import com.songify_rest_template_client.received.*;
import com.songify_rest_template_client.requested.SongRequestDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class SongMapper {
    
    public Map<Integer, Song> mapAllSongsReceivedToSongList(AllSongsReceivedDto body) {
        if (body == null) {
            return Collections.emptyMap();
        }
        return body.songs();
    }
    
    public Song mapSongReceivedToSong(SongReceivedDto body) {
        if (body == null) {
            return new Song("", "");
        }
        return body.song();
    }
    
    public SongRequestDto mapSongToSongRequest(Song song) {
        return new SongRequestDto(song.name(), song.artist());
    }
    
    public Song mapSongUpdatedDtoToSong(SongUpdatedDto body) {
        if (body == null) {
            return new Song("", "");
        }
        return new Song(body.songName(), body.artist());
    }
    
    public String mapSongDeletedDtoToString(SongDeletedDto body) {
        if (body == null) {
            return "";
        }
        return body.message() + ", status: " + body.status();
    }
    
    // For more info see --> SongifyService.class
//    public String mapSongPatchedDtoToSong(SongPatchedDto body) {
//        return body.message();
//    }
}

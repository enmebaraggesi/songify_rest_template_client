package com.songify_rest_template_client.service;

import com.songify_rest_template_client.received.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class SongMapper {
    
    public Map<Integer, Song> mapAllSongsReceivedToSongList(AllSongsReceived body) {
        if (body == null) {
            return Collections.emptyMap();
        }
        return body.songs();
    }
    
    public Song mapSongReceivedToSong(SongReceived body) {
        if (body == null) {
            return null;
        }
        return body.song();
    }
}

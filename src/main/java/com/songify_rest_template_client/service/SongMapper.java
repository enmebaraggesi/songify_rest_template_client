package com.songify_rest_template_client.service;

import com.songify_rest_template_client.received.AllSongsReceived;
import com.songify_rest_template_client.received.Song;
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
}

package com.songify_rest_template_client.service;

import com.songify_rest_template_client.received.AllSongsReceived;
import com.songify_rest_template_client.received.Song;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class SongifyService {
    
    @Value("${com.songify.url}")
    String songifyUrl;
    
    @Value("${com.songify.port}")
    Integer songifyPort;
    
    private final SongMapper songMapper;
    private final RestTemplate restTemplate;
    
    public SongifyService(SongMapper songMapper, RestTemplate restTemplate) {
        this.songMapper = songMapper;
        this.restTemplate = restTemplate;
    }
    
    
    public Map<Integer, Song> getAllSongs() {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs")
                                      .build()
                                      .toUri();
        try {
            ResponseEntity<AllSongsReceived> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    AllSongsReceived.class);
            return songMapper.mapAllSongsReceivedToSongList(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
}

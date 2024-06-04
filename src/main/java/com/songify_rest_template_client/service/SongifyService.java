package com.songify_rest_template_client.service;

import com.songify_rest_template_client.received.*;
import com.songify_rest_template_client.requested.SongRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Random;

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
    
    public Map<Integer, Song> getAllSongsLimited(Integer limit) {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs")
                                      .queryParam("limit", limit)
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
    
    public Song getSongById(Integer id) {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs/" + id)
                                      .build()
                                      .toUri();
        int randomSeed = new Random().nextInt();
        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", String.valueOf(randomSeed));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<SongReceived> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    SongReceived.class);
            return songMapper.mapSongReceivedToSong(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Song postNewSong(Song song) {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs")
                                      .build()
                                      .toUri();
        SongRequestDto songRequestDto = songMapper.mapSongToSongRequest(song);
        HttpEntity<SongRequestDto> entity = new HttpEntity<>(songRequestDto);
        try {
            ResponseEntity<SongReceived> response = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    entity,
                    SongReceived.class);
            return songMapper.mapSongReceivedToSong(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Song putSongById(Integer id, Song song) {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs/" + id)
                                      .build()
                                      .toUri();
        SongRequestDto songRequestDto = songMapper.mapSongToSongRequest(song);
        HttpEntity<SongRequestDto> entity = new HttpEntity<>(songRequestDto);
        try {
            ResponseEntity<SongUpdatedDto> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    entity,
                    SongUpdatedDto.class);
            return songMapper.mapSongUpdatedDtoToSong(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String deleteSongById(Integer id) {
        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme("http")
                                      .host(songifyUrl)
                                      .port(songifyPort)
                                      .path("songs/" + id)
                                      .build()
                                      .toUri();
        try {
            ResponseEntity<SongDeletedDto> response = restTemplate.exchange(
                    uri,
                    HttpMethod.DELETE,
                    null,
                    SongDeletedDto.class);
            return songMapper.mapSongDeletedDtoToString(response.getBody());
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
    
    // PATCH method not working with RestTemplate HttpConnection methods
//    public String patchSongById(Integer id, Song song) {
//        URI uri = UriComponentsBuilder.newInstance()
//                                      .scheme("http")
//                                      .host(songifyUrl)
//                                      .port(songifyPort)
//                                      .path("songs/" + id)
//                                      .build()
//                                      .toUri();
//        SongRequestDto songRequestDto = songMapper.mapSongToSongRequest(song);
//        HttpEntity<SongRequestDto> entity = new HttpEntity<>(songRequestDto);
//        try {
//            ResponseEntity<SongPatchedDto> response = restTemplate.exchange(
//                    uri,
//                    HttpMethod.PATCH,
//                    entity,
//                    SongPatchedDto.class);
//            return songMapper.mapSongPatchedDtoToSong(response.getBody());
//        } catch (RestClientException e) {
//            throw new RuntimeException(e);
//        }
//    }


}

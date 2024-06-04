package com.songify_rest_template_client.received;

import com.songify_rest_template_client.client.Song;

import java.util.Map;

public record AllSongsReceivedDto(Map<Integer, Song> songs) {

}

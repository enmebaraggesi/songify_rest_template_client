package com.songify_rest_template_client.received;

import java.util.Map;

public record AllSongsReceived(Map<Integer, Song> songs) {

}

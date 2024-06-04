package com.songify_rest_template_client.received;

import org.springframework.http.HttpStatus;

public record SongDeletedDto(String message, HttpStatus status) {

}

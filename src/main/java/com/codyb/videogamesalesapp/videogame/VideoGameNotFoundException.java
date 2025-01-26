package com.codyb.videogamesalesapp.videogame;

public class VideoGameNotFoundException extends RuntimeException {

    public VideoGameNotFoundException() {
        super("Video Game Not Found");
    }
}

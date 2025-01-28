package com.codyb.videogamesalesapp.videogame;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videogames")

public class VideoGameController {

    private final VideoGameRepository videoGameRepository;

    public VideoGameController(VideoGameRepository videoGameRepository) {
        this.videoGameRepository = videoGameRepository;
    }

    @GetMapping("")
    List<VideoGame> findAll() {
        return videoGameRepository.findAll();
    }

    @GetMapping("/id={id}")
    VideoGame findById(@PathVariable Integer id) {
        Optional<VideoGame> videoGame = videoGameRepository.findById(id);
        if (videoGame.isEmpty()) {
            throw new VideoGameNotFoundException();
        }
        return videoGame.get();
    }

    @GetMapping("/genre={genre}")
    List<VideoGame> findByGenre(@PathVariable String genre) {
        return videoGameRepository.findByGenre(genre);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody VideoGame videoGame) {
        Number num = videoGameRepository.create(videoGame);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/id={id}")
    void delete(@PathVariable Integer id) {
        videoGameRepository.delete(id);
    }

    @PutMapping("/id={id}")
    void update(@PathVariable Integer id, @RequestBody VideoGame videoGame) {
        videoGameRepository.update(id, videoGame);
    }
}

package com.codyb.videogamesalesapp.videogame;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VideoGameController.class)
class VideoGameControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    VideoGameRepository videoGameRepository;

    private final List<VideoGame> videoGameList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        videoGameList.add(new VideoGame(1,
                "New Game",
                "PS2",
                "Action",
                "Game Store",
                "Game Store",
                10.0,
                5.5,
                2.3,
                1.1,
                0.2,
                0.1,
                LocalDate.parse("2024-01-24"),
                LocalDate.parse("2024-01-25")));
    }

    @Test
    void shouldFindAll() throws Exception {
        when(videoGameRepository.findAll()).thenReturn(videoGameList);
        mockMvc.perform(get("/api/videogames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(videoGameList.size())));
    }

    @Test
    void shouldFindOne() throws Exception {
        VideoGame videoGame = videoGameList.getFirst();
        when(videoGameRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(videoGame));
        mockMvc.perform(get("/api/videogames/id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Game")))
                .andExpect(jsonPath("$.console", is("PS2")))
                .andExpect(jsonPath("$.genre", is("Action")));
    }

    @Test
    void shouldNotFindOne() throws Exception {
        mockMvc.perform(get("/api/videogames/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindByGenre() throws Exception {
        when(videoGameRepository.findByGenre(ArgumentMatchers.anyString())).thenReturn(videoGameList);
        mockMvc.perform(get("/api/videogames/genre=Action"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(videoGameList.size())));
    }

    @Test
    void shouldCreateNewGame() throws Exception {
        var videoGame = new VideoGame(2, "NG", "PS", "Action", "CB", "CB", null, null, null, null, null, null, LocalDate.parse("2024-01-01"), null);
        when(videoGameRepository.create(ArgumentMatchers.any(VideoGame.class))).thenReturn(2);
        mockMvc.perform(post("/api/videogames")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(videoGame)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    void shouldNotCreateNewGame() throws Exception {
        var videoGame = new VideoGame(2, "NG", "PS", "Action", "CB", "CB", null, null, null, null, null, null, null, null);
        mockMvc.perform(post("/api/videogames")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(videoGame)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteGame() throws Exception {
        mockMvc.perform(delete("/api/videogames/id=1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateGame() throws Exception {
        String json = "{ \"title\": \"Changed Title\" }";
        mockMvc.perform(put("/api/videogames/id=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
    }


}
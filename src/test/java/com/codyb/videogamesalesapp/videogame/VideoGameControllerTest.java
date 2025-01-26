package com.codyb.videogamesalesapp.videogame;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


}
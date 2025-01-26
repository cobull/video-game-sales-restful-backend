package com.codyb.videogamesalesapp.videogame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(VideoGameRepository.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class VideoGameRepositoryTest {

    static Number id1;
    static Number id2;
    static VideoGame updatevideogame = new VideoGame(
            null,
            null,
            null,
            null,
            "Different publisher",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

    @Autowired
    private VideoGameRepository videoGameRepository;

    @BeforeEach
    void setUp() {
        id1 = videoGameRepository.create(new VideoGame(
                1482,
                "CB new game",
                "Xbox 360",
                "Action",
                "CB games",
                "CB games",
                9.4,
                5.5,
                1.2,
                3.2,
                0.1,
                0.0,
                LocalDate.parse("2025-10-01"),
                LocalDate.parse("2025-11-12")
        ));

        id2 = videoGameRepository.create(new VideoGame(
                1,
                "CB new game2",
                "PS5",
                "Action",
                "CB games",
                "CB games",
                1.2,
                6.3,
                2.3,
                1.4,
                1.1,
                1.0,
                LocalDate.parse("2025-10-07"),
                LocalDate.parse("2025-11-15")
        ));
    }
    @Test
    void findAll() {
        List<VideoGame> videoGames = videoGameRepository.findAll();
        assertEquals(2, videoGames.size());
    }

    @Test
    void findById() {
        var videoGame = videoGameRepository.findById(id1.intValue()).get();
        assertEquals(9.4, videoGame.critic_score());
    }

    @Test
    void notFindByInvalidID() {
        var videoGame = videoGameRepository.findById(100000);
        assertTrue(videoGame.isEmpty());
    }

    @Test
    void findByGenre() {
        List<VideoGame> videoGames = videoGameRepository.findByGenre("Action");
        assertEquals(2, videoGames.size());
    }

    @Test
    void delete() {
        videoGameRepository.delete(id1.intValue());
        var videoGames = videoGameRepository.findById(id1.intValue());
        assertTrue(videoGames.isEmpty());
    }

    @Test
    void update() {
        videoGameRepository.update(id1.intValue(), updatevideogame);
        var videoGames = videoGameRepository.findById(id1.intValue()).get();
        assertEquals("Different publisher", videoGames.publisher());
    }
}
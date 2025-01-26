package com.codyb.videogamesalesapp.videogame;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class VideoGameRepository {

    private final JdbcClient jdbcClient;

    public VideoGameRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<VideoGame> findAll() {
        return jdbcClient.sql("SELECT * FROM videogamesalestest")
                .query(VideoGame.class)
                .list();
    }

    public Optional<VideoGame> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM videogamesalestest WHERE id= :id")
                .param("id", id)
                .query(VideoGame.class)
                .optional();
    }

    public List<VideoGame> findByGenre(String genre) {
        return jdbcClient.sql("SELECT * FROM videogamesalestest WHERE genre= :genre")
                .param("genre", genre)
                .query(VideoGame.class)
                .list();
    }

    public Number create(VideoGame videoGame) {
        GeneratedKeyHolder key = new GeneratedKeyHolder();
        var newVideoGame = jdbcClient.sql("INSERT INTO videogamesalestest (title, console, genre, publisher, developer, critic_score, total_sales, na_sales, jp_sales, pal_sales, other_sales, release_date, last_update)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                .params(videoGame.title(), videoGame.console(), videoGame.genre(), videoGame.publisher(), videoGame.developer(), videoGame.critic_score(),
                        videoGame.total_sales(), videoGame.na_sales(), videoGame.jp_sales(), videoGame.pal_sales(), videoGame.other_sales(), videoGame.release_date(), videoGame.last_update())
                .update(key);
        Assert.state(newVideoGame == 1, "Failed to insert new videoGame");
        return (Number) key.getKeyList().getFirst().get("id");
    }

    public void delete(Integer id) {
        var deletedVideoGame = jdbcClient.sql("DELETE FROM videogamesalestest WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(deletedVideoGame == 1, "Failed to delete videoGame");
    }

    public void update(Integer id, VideoGame videoGame) {
        Optional<VideoGame> preexistingVideoGame = findById(id);
        VideoGame updatedVideoGame;
        if (preexistingVideoGame.isPresent()) {
            VideoGameDTO videoGameDTO1 = VideoGameDTOMapper.INSTANCE.toVideoGameDTO(videoGame);
            VideoGameDTO videoGameDTO2 = VideoGameDTOMapper.INSTANCE.toVideoGameDTO(preexistingVideoGame.get());
            BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(videoGameDTO1);
            java.beans.PropertyDescriptor[] propertyDescriptors = wrapper.getPropertyDescriptors();
            Set<String> emptyNames = new HashSet<>();
            for (java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (wrapper.getPropertyValue(propertyDescriptor.getName()) == null) {
                    emptyNames.add(propertyDescriptor.getName());
                }
            }
            String[] nullNames = new String[emptyNames.size()];
            emptyNames.toArray(nullNames);
            BeanUtils.copyProperties(videoGameDTO1, videoGameDTO2, nullNames);
            updatedVideoGame = VideoGameMapper.INSTANCE.toVideoGame(videoGameDTO2);

        } else {
            throw new VideoGameNotFoundException();
        }
        var rowsUpdated = jdbcClient.sql("UPDATE videogamesalestest SET (title, console, genre, publisher, developer, critic_score, total_sales, na_sales, jp_sales, pal_sales, other_sales, release_date, last_update) = " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE id = ?;")
                .params(updatedVideoGame.title(), updatedVideoGame.console(), updatedVideoGame.genre(), updatedVideoGame.publisher(), updatedVideoGame.developer(), updatedVideoGame.critic_score(), updatedVideoGame.total_sales(),
                        updatedVideoGame.na_sales(), updatedVideoGame.jp_sales(), updatedVideoGame.pal_sales(), updatedVideoGame.other_sales(), updatedVideoGame.release_date(), updatedVideoGame.last_update(), id)
                .update();
        Assert.state(rowsUpdated == 1, "Failed to update videoGame");
    }
}

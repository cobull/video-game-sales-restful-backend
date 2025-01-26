package com.codyb.videogamesalesapp.videogame;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VideoGameMapper {

    VideoGameMapper INSTANCE = Mappers.getMapper(VideoGameMapper.class);

    VideoGame toVideoGame(VideoGameDTO videoGameDTO);
}

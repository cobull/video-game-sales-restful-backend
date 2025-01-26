package com.codyb.videogamesalesapp.videogame;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VideoGameDTOMapper {

    VideoGameDTOMapper INSTANCE = Mappers.getMapper(VideoGameDTOMapper.class);

    VideoGameDTO toVideoGameDTO(VideoGame videoGame);

}

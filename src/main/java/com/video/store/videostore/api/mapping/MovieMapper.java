package com.video.store.videostore.api.mapping;

import com.video.store.videostore.api.dto.MovieDto;
import com.video.store.videostore.domain.entity.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    Movie movieDtoToMovie(MovieDto movieDto);

    MovieDto movieToMovieDto(Movie movie);
}

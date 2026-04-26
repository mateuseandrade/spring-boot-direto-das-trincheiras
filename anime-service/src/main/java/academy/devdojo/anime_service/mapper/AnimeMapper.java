package academy.devdojo.anime_service.mapper;

import academy.devdojo.anime_service.domain.Anime;
import academy.devdojo.anime_service.request.AnimePostRequest;
import academy.devdojo.anime_service.request.AnimePutRequest;
import academy.devdojo.anime_service.response.AnimeGetResponse;
import academy.devdojo.anime_service.response.AnimePostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnimeMapper {
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")

    Anime toAnime(AnimePostRequest animePostRequest);
    Anime toAnime(AnimePutRequest request);

    AnimePostResponse toAnimePostResponse(Anime anime);
    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animeList);

}

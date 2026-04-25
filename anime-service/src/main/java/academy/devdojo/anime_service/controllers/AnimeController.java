package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Anime;
import academy.devdojo.anime_service.mapper.AnimeMapper;
import academy.devdojo.anime_service.request.AnimePostRequest;
import academy.devdojo.anime_service.response.AnimeGetResponse;
import academy.devdojo.anime_service.response.AnimePostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll() {
        log.debug("Searching all animes");

        var animes = Anime.getListaAnimes();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> listAllAnimesById(@PathVariable Long id) {
        log.debug("Searching anime by id: {}", id);
        var animeGetResponse = Anime.getListaAnimes().stream()
                .filter(anime -> Objects.equals(anime.getId(), id))
                .findFirst()
                .map(MAPPER::toAnimeGetResponse)
                .orElse(null);
        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        var anime = MAPPER.toAnime(request);
        var response = MAPPER.toAnimePostResponse(anime);
        Anime.getListaAnimes().add(anime);

        return ResponseEntity.status(201).body(response);
    }

}

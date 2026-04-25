package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Anime;
import academy.devdojo.anime_service.mapper.AnimeMapper;
import academy.devdojo.anime_service.request.AnimePostRequest;
import academy.devdojo.anime_service.request.AnimePutRequest;
import academy.devdojo.anime_service.response.AnimeGetResponse;
import academy.devdojo.anime_service.response.AnimePostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private static final String ANIME_NOT_FOUND = "Anime not found";

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ANIME_NOT_FOUND));
        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        var anime = MAPPER.toAnime(request);
        var response = MAPPER.toAnimePostResponse(anime);
        Anime.getListaAnimes().add(anime);

        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Deleting anime by id: {}", id);
        var animeToDelete = Anime.getListaAnimes().stream()
                .filter(anime -> Objects.equals(anime.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ANIME_NOT_FOUND));
        Anime.getListaAnimes().remove(animeToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Updating anime: {}", request);

        var animeToRemove = Anime.getListaAnimes()
                .stream()
                .filter(anime -> Objects.equals(anime.getId(), request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ANIME_NOT_FOUND));

        var animeUpdated = MAPPER.toAnime(request);
        Anime.getListaAnimes().remove(animeToRemove);
        Anime.getListaAnimes().add(animeUpdated);

        return ResponseEntity.noContent().build();
    }

}

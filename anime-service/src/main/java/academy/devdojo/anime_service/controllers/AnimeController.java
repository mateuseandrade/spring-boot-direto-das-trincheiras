package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.mapper.AnimeMapper;
import academy.devdojo.anime_service.request.AnimePostRequest;
import academy.devdojo.anime_service.request.AnimePutRequest;
import academy.devdojo.anime_service.response.AnimeGetResponse;
import academy.devdojo.anime_service.response.AnimePostResponse;
import academy.devdojo.anime_service.service.AnimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {
    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;
    private final AnimeService animeService;

    public AnimeController() { this.animeService = new AnimeService();}

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll() {
        var animes = animeService.findAll();
        var animeGetResponseList = MAPPER.toAnimeGetResponseList(animes);
        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> listAllAnimesById(@PathVariable Long id) {
        var anime = animeService.findByIdOrThrowNotFound(id);
        var animeGetResponse = MAPPER.toAnimeGetResponse(anime);
        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        var anime = animeService.save(MAPPER.toAnime(request));
        var response = MAPPER.toAnimePostResponse(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        animeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        animeService.update(MAPPER.toAnime(request));
        return ResponseEntity.noContent().build();
    }

}

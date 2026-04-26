package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.mapper.AnimeMapper;
import academy.devdojo.anime_service.request.AnimePostRequest;
import academy.devdojo.anime_service.request.AnimePutRequest;
import academy.devdojo.anime_service.response.AnimeGetResponse;
import academy.devdojo.anime_service.response.AnimePostResponse;
import academy.devdojo.anime_service.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeMapper mapper;
    private final AnimeService service;

    @GetMapping
    public ResponseEntity<List<AnimeGetResponse>> listAll() {
        var animes = service.findAll();
        var animeGetResponseList = mapper.toAnimeGetResponseList(animes);
        return ResponseEntity.ok(animeGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> listAllAnimesById(@PathVariable Long id) {
        var anime = service.findByIdOrThrowNotFound(id);
        var animeGetResponse = mapper.toAnimeGetResponse(anime);
        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimePostResponse> save(@RequestBody AnimePostRequest request) {
        var anime = service.save(mapper.toAnime(request));
        var response = mapper.toAnimePostResponse(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        service.update(mapper.toAnime(request));
        return ResponseEntity.noContent().build();
    }

}

package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {

    @GetMapping
    public List<Anime> listAll() {
        return Anime.getListaAnimes();
    }

    @GetMapping("{id}")
    public Anime listAllAnimesById(@PathVariable("id") Long id) {
        return Anime.getListaAnimes().stream().filter(anime -> Objects.equals(anime.getId(), id)).findFirst().orElse(null);
    }

    @PostMapping
    public Anime save(@RequestBody Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(1, 1000));
        Anime.getListaAnimes().add(anime);
        return anime;
    }

}

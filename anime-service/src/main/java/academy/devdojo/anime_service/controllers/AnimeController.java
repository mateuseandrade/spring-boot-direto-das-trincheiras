package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Anime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/animes")
@Slf4j
public class AnimeController {

    @GetMapping
    public List<Anime> listAll(@RequestParam(required = false) String name) {
        var animes = Anime.listAll();
        if (name == null || name.isBlank()) return animes;
        return animes.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    @GetMapping("{id}")
    public Anime listAllAnimesById(@PathVariable("id") Long id) {
        return Anime.listAll().stream().filter(anime -> Objects.equals(anime.getId(), id)).findFirst().orElse(null);
    }

}

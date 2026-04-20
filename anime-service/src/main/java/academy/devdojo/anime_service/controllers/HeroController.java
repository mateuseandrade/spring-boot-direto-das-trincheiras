package academy.devdojo.anime_service.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/heroes")
public class HeroController {
    private static final List<String> heroes = List.of("Batman", "Superman", "Flash");

    @GetMapping
    public List<String> listAllHeroes() {
        return heroes;
    }

    @GetMapping("filter")
    public List<String> listAllHeroesParam(@RequestParam(defaultValue = "") String name) {
        return heroes.stream().filter(hero -> hero.equalsIgnoreCase(name)).toList();
    }

    @GetMapping("filterList")
    public List<String> listAllHeroesParamList(@RequestParam(defaultValue = "") List<String> names) {
        return heroes.stream().filter(hero -> names.stream().anyMatch(hero::equalsIgnoreCase)).toList();
    }

    @GetMapping("{name}")
    public String findByName(@PathVariable String name) {
        return heroes
                .stream()
                .filter(hero -> hero.equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}

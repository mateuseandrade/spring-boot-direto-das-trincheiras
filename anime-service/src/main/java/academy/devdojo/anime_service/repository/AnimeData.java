package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Anime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimeData {
    private static final List<Anime> animes = new ArrayList<>();

    static {
        var naturo = Anime.builder().id(1L).name("Naturo").build();
        var bleach = Anime.builder().id(2L).name("Bleach").build();
        var deathNote = Anime.builder().id(3L).name("Death Note").build();
        animes.addAll(List.of(naturo, bleach, deathNote));
    }

    public List<Anime> getAnimes() {
        return animes;
    }

}

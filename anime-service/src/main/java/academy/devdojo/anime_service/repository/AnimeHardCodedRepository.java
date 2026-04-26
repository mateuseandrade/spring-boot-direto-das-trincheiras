package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Anime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimeHardCodedRepository {
    private static final List<Anime> ANIME = new ArrayList<>();

    static {
        var deathNote = Anime.builder().id(1L).name("Death Note");
        var naruto = Anime.builder().id(2L).name("Naruto");
        var bleach = Anime.builder().id(3L).name("Bleach");
        ANIME.add(deathNote.build());
        ANIME.add(naruto.build());
        ANIME.add(bleach.build());
    }

    public List<Anime> findAll() {
        return ANIME;
    }

    public Optional<Anime> findById(Long id) {
        return ANIME.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public Anime save(Anime anime) {
        ANIME.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        ANIME.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}

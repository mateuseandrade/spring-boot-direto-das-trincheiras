package academy.devdojo.anime_service.service;

import academy.devdojo.anime_service.domain.Anime;
import academy.devdojo.anime_service.repository.AnimeHardCodedRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class AnimeService {
    private final AnimeHardCodedRepository animeHardCodedRepository;
    private static final String ANIME_NOT_FOUND = "Anime not found";


    public AnimeService() {
        animeHardCodedRepository = new AnimeHardCodedRepository();
    }

    public List<Anime> findAll() {
        return animeHardCodedRepository.findAll();
    }

    public Anime findByIdOrThrowNotFound(Long id) {
        return animeHardCodedRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ANIME_NOT_FOUND));
    }

    public Anime save(Anime anime) {
        return animeHardCodedRepository.save(anime);
    }

    public void delete(Long id) {
        var anime = findByIdOrThrowNotFound(id);
        animeHardCodedRepository.delete(anime);
    }

    public void update(Anime animeToUpdate) {
        animeHardCodedRepository.update(animeToUpdate);
    }

}

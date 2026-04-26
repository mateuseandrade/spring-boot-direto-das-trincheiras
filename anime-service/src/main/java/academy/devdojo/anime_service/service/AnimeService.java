package academy.devdojo.anime_service.service;

import academy.devdojo.anime_service.domain.Anime;
import academy.devdojo.anime_service.repository.AnimeHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeHardCodedRepository repository;
    private static final String ANIME_NOT_FOUND = "Anime not found";

    public List<Anime> findAll() {
        return repository.findAll();
    }

    public Anime findByIdOrThrowNotFound(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ANIME_NOT_FOUND));
    }

    public Anime save(Anime anime) {
        return repository.save(anime);
    }

    public void delete(Long id) {
        var anime = findByIdOrThrowNotFound(id);
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate) {
        repository.update(animeToUpdate);
    }

}

package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Producer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProducerHardCodedRepository {
    private static final List<Producer> PRODUCER = new ArrayList<>();

    static {
        var mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now());
        var studioGhibli = Producer.builder().id(2L).name("Studio Ghibli").createdAt(LocalDateTime.now());
        var toeiAnimation = Producer.builder().id(3L).name("Toei Animation").createdAt(LocalDateTime.now());
        PRODUCER.add(mappa.build());
        PRODUCER.add(studioGhibli.build());
        PRODUCER.add(toeiAnimation.build());
    }

    public List<Producer> findAll() {
        return PRODUCER;
    }

    public Optional<Producer> findById(Long id) {
        return PRODUCER.stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }

    public Producer save(Producer producer) {
        PRODUCER.add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        PRODUCER.remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}

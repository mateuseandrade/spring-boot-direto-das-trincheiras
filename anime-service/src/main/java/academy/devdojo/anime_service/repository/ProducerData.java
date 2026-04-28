package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Producer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProducerData {
    private static final List<Producer> producers = new ArrayList<>();

    static {
        var mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(2L).name("Studio Ghibli").createdAt(LocalDateTime.now()).build();
        var toeiAnimation = Producer.builder().id(3L).name("Toei Animation").createdAt(LocalDateTime.now()).build();
        producers.addAll(List.of(mappa, studioGhibli, toeiAnimation));
    }

    public List<Producer> getProducers() {
        return producers;
    }

}

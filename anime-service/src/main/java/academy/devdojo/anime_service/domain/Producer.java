package academy.devdojo.anime_service.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Producer {
    private String name;
    private Long id;
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private static List<Producer> producers = new ArrayList<>();

    static {
        var mappa = Producer.builder().id(1L).name("Mappa").createdAt(LocalDateTime.now());
        var studioGhibli = Producer.builder().id(2L).name("Studio Ghibli").createdAt(LocalDateTime.now());
        var toeiAnimation = Producer.builder().id(3L).name("Toei Animation").createdAt(LocalDateTime.now());
        producers.add(mappa.build());
        producers.add(studioGhibli.build());
        producers.add(toeiAnimation.build());
    }

}

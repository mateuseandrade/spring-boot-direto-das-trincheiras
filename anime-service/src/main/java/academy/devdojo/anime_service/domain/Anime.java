package academy.devdojo.anime_service.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Anime {
    private String name;
    private Long id;

    public static List<Anime> listAll() {
        List<Anime> animes = new ArrayList<>();
        animes.add(new Anime("Naruto", 1L));
        animes.add(new Anime("One Piece", 2L));
        animes.add(new Anime("Death Note", 3L));
        return animes;
    }
}

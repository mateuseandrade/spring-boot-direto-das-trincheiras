package academy.devdojo.anime_service.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Anime {
    private String name;
    private Long id;

    @Getter
    @Setter
    private static List<Anime> listaAnimes = new ArrayList<>();

}

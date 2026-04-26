package academy.devdojo.anime_service.domain;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Anime {

    private String name;

    @EqualsAndHashCode.Include
    private Long id;
}

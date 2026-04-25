package academy.devdojo.anime_service.domain;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {
    private String name;
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime createdAt;

}

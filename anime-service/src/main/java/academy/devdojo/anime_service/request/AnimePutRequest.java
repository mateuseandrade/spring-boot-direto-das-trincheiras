package academy.devdojo.anime_service.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnimePutRequest {
    private Long id;
    private String name;

}

package academy.devdojo.anime_service.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ProducerPutRequest {
    private Long id;
    private String name;

}

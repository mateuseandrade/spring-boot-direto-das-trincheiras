package academy.devdojo.anime_service.mapper;

import academy.devdojo.anime_service.domain.Producer;
import academy.devdojo.anime_service.request.ProducerPostRequest;
import academy.devdojo.anime_service.request.ProducerPutRequest;
import academy.devdojo.anime_service.response.ProducerGetResponse;
import academy.devdojo.anime_service.response.ProducerPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProducerMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")

    Producer toProducer(ProducerPostRequest producerPostRequest);
    Producer toProducer(ProducerPutRequest request);

    ProducerGetResponse toProducerGetResponse(Producer producer);
    ProducerPostResponse toProducerPostResponse(Producer producerSaved);

    List<ProducerGetResponse> toProducerGetResponseList(List<Producer> producerList);

}

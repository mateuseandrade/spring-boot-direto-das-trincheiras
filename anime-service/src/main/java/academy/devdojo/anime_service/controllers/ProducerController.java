package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Producer;
import academy.devdojo.anime_service.mapper.ProducerMapper;
import academy.devdojo.anime_service.request.ProducerPostRequest;
import academy.devdojo.anime_service.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    public static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll() {
        var producers = Producer.getProducers();
        var producerGetResponseList = MAPPER.toProducerGetResponseList(producers);
        return ResponseEntity.ok(producerGetResponseList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Searching producer by id: {}", id);

        var producerResponse = Producer.getProducers()
                .stream()
                .filter(producer -> Objects.equals(producer.getId(), id))
                .findFirst()
                .map(MAPPER::toProducerGetResponse)
                .orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(producerResponse);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {

        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerGetResponse(producer);

        Producer.getProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

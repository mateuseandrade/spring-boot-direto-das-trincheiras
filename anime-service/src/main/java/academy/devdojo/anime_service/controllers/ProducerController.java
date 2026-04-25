package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Producer;
import academy.devdojo.anime_service.mapper.ProducerMapper;
import academy.devdojo.anime_service.request.ProducerPostRequest;
import academy.devdojo.anime_service.request.ProducerPutRequest;
import academy.devdojo.anime_service.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    public static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    private static final String PRODUCER_NOT_FOUND = "Producer not found";

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCER_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(producerResponse);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {

        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerGetResponse(producer);

        Producer.getProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Deleting producer by id: {}", id);
        var producerToDelete = Producer.getProducers()
                .stream()
                .filter(producer -> Objects.equals(producer.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCER_NOT_FOUND));
        Producer.getProducers().removeIf(producer -> Objects.equals(producer.getId(), id));
        Producer.getProducers().remove(producerToDelete);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.debug("Updating producer: {}", request);

        var producerToRemove = Producer.getProducers()
                .stream()
                .filter(producer -> Objects.equals(producer.getId(), request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCER_NOT_FOUND));

        var producerUpdated = MAPPER.toProducer(request, producerToRemove.getCreatedAt());
        Producer.getProducers().remove(producerToRemove);
        Producer.getProducers().add(producerUpdated);

        return ResponseEntity.noContent().build();
    }

}

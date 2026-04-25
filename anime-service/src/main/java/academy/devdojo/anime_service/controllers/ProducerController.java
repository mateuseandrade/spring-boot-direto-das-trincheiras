package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.mapper.ProducerMapper;
import academy.devdojo.anime_service.request.ProducerPostRequest;
import academy.devdojo.anime_service.request.ProducerPutRequest;
import academy.devdojo.anime_service.response.ProducerGetResponse;
import academy.devdojo.anime_service.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {
    public static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;
    private final ProducerService producerService;

    public ProducerController() {
        this.producerService = new ProducerService();
    }

    @GetMapping
    public ResponseEntity<List<ProducerGetResponse>> listAll() {
        var producers = producerService.findAll();
        var producerGetResponses = MAPPER.toProducerGetResponseList(producers);
        return ResponseEntity.ok(producerGetResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        log.debug("Searching producer by id: {}", id);

        var producer = producerService.findByIdOrThrowNotFound(id);
        var producerGetResponse = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.status(HttpStatus.OK).body(producerGetResponse);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {

        var producer = MAPPER.toProducer(producerPostRequest);
        var producerSaved = producerService.save(producer);
        var producerGetResponse = MAPPER.toProducerGetResponse(producerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(producerGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("Deleting producer by id: {}", id);
        producerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ProducerPutRequest request) {
        log.debug("Updating producer: {}", request);

        var producerToUpdate = MAPPER.toProducer(request);
        producerService.update(producerToUpdate);

        return ResponseEntity.noContent().build();
    }

}

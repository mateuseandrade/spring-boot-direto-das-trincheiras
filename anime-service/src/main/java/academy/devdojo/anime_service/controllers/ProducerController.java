package academy.devdojo.anime_service.controllers;

import academy.devdojo.anime_service.domain.Producer;
import academy.devdojo.anime_service.request.ProducerPostRequest;
import academy.devdojo.anime_service.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("v1/producers")
@Slf4j
public class ProducerController {

    @GetMapping
    public List<Producer> listAll() {
        return Producer.getProducers();
    }

    @GetMapping("{id}")
    public Producer listAllProducersById(@PathVariable("id") Long id) {
        return Producer.getProducers().stream().filter(producer -> Objects.equals(producer.getId(), id)).findFirst().orElse(null);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {

        Producer producer = Producer.builder().id(ThreadLocalRandom.current().nextLong(1, 1000))
                .name(producerPostRequest.getName())
                .createdAt(LocalDateTime.now()).build();
        Producer.getProducers().add(producer);

        ProducerGetResponse produceResProducer = ProducerGetResponse.builder().id(producer.getId()).name(producer.getName()).createdAt(producer.getCreatedAt()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body
                (produceResProducer);
    }

}

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
    public List<Producer> listAll() {
        return Producer.getProducers();
    }

    @GetMapping("{id}")
    public Producer listAllProducersById(@PathVariable Long id) {
        return Producer.getProducers().stream().filter(producer -> Objects.equals(producer.getId(), id)).findFirst().orElse(null);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest) {

        var producer = MAPPER.toProducer(producerPostRequest);
        var response = MAPPER.toProducerGetResponse(producer);

        Producer.getProducers().add(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

package academy.devdojo.anime_service.service;

import academy.devdojo.anime_service.domain.Producer;
import academy.devdojo.anime_service.repository.ProducerHardCodedRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProducerService {
    private final ProducerHardCodedRepository producerHardCodedRepository;
    private static final String PRODUCER_NOT_FOUND = "Producer not found";


    public ProducerService() {
        producerHardCodedRepository = new ProducerHardCodedRepository();
    }

    public List<Producer> findAll() {
        return producerHardCodedRepository.findAll();
    }

    public Producer findByIdOrThrowNotFound(Long id) {
        return producerHardCodedRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUCER_NOT_FOUND));
    }

    public Producer save(Producer producer) {
        return producerHardCodedRepository.save(producer);
    }

    public void delete(Long id) {
        var producer = findByIdOrThrowNotFound(id);
        producerHardCodedRepository.delete(producer);
    }

    public void update(Producer producerToUpdate) {
        var producer = findByIdOrThrowNotFound(producerToUpdate.getId());
        producerToUpdate.setCreatedAt(producer.getCreatedAt());
        producerHardCodedRepository.update(producerToUpdate);
    }

}

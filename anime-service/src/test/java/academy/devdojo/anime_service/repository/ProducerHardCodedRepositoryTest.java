package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Producer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProducerHardCodedRepositoryTest {
    @InjectMocks
    private ProducerHardCodedRepository repository;

    @Mock
    private ProducerData producerData;

    private final List<Producer> producersList = new ArrayList<>();

    @BeforeEach
    void init() {
        var ufotable = Producer.builder().id(1L).name("Ufotable").createdAt(LocalDateTime.now()).build();
        var witStudio = Producer.builder().id(2L).name("Wit Studio").createdAt(LocalDateTime.now()).build();
        var studioGhibli = Producer.builder().id(3L).name("Studio Ghibli").createdAt(LocalDateTime.now()).build();
        producersList.addAll(List.of(ufotable, witStudio, studioGhibli));
    }

    @Test
    @DisplayName("findAll returns a list with all producers")
    @Order(1)
    void findAll_ReturnAllProducers_WhenSuccessfull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var producers = repository.findAll();
        Assertions.assertThat(producers).isNotNull().hasSameElementsAs(producersList);
    }

    @Test
    @DisplayName("findById returns a producer with given id")
    @Order(2)
    void findById_ReturnsProducerById_WhenSuccessfull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        var expectedProducer = producersList.getFirst();
        var producer = repository.findById(expectedProducer.getId());
        Assertions.assertThat(producer).isPresent().contains(expectedProducer);
    }

    @Test
    @DisplayName("Save creates a producer")
    @Order(3)
    void save_CreatesProducer_WhenSuccessfull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var producerToSave = Producer.builder().id(4L).name("Prodicer to save").createdAt(LocalDateTime.now()).build();
        var producer = repository.save(producerToSave);

        Assertions.assertThat(producer).isEqualTo(producerToSave).hasNoNullFieldsOrProperties();

        var producerSaveOptional = repository.findById(producerToSave.getId());
        Assertions.assertThat(producerSaveOptional).isPresent().contains(producerToSave);

        Assertions.assertThat(producerSaveOptional.get().getCreatedAt()).isNotNull();
    }

    @Test
    @DisplayName("Delete removes a producer")
    @Order(4)
    void delete_RemoveProducer_WhenSuccessfull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);

        var producerToDelete = producersList.getFirst();
        repository.delete(producerToDelete);

        Assertions.assertThat(repository.findById(producerToDelete.getId())).isEmpty();
    }

    @Test
    @DisplayName("Update updates a producer")
    @Order(5)
    void update_UpdatesProducer_WhenSuccessfull() {
        BDDMockito.when(producerData.getProducers()).thenReturn(producersList);
        
        var producerToUpdate = producersList.getFirst();
        producerToUpdate.setName("Aniplex");
        repository.update(producerToUpdate);

        Assertions.assertThat(this.producersList).contains(producerToUpdate);

        var producerUpdatedOpcional = repository.findById(producerToUpdate.getId());

        Assertions.assertThat(producerUpdatedOpcional).isPresent();
        Assertions.assertThat(producerUpdatedOpcional.get().getName()).isEqualTo(producerToUpdate.getName());
    }

}
package academy.devdojo.anime_service.repository;

import academy.devdojo.anime_service.domain.Anime;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnimeHardCodedRepositoryTest {
    @InjectMocks
    private AnimeHardCodedRepository repository;

    @Mock
    private AnimeData animeData;
    private List<Anime> animesList;

    @BeforeEach
    void init() {
        var naturo = Anime.builder().id(1L).name("Naturo").build();
        var bleach = Anime.builder().id(2L).name("Bleach").build();
        var deathNote = Anime.builder().id(3L).name("Death Note").build();
        animesList = new ArrayList<>(List.of(naturo, bleach, deathNote));
    }

    @Test
    @DisplayName("findAll returns a list with all animes")
    @Order(1)
    void findAll_ReturnAllAnimes_WhenSuccessfull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);
        var animes = repository.findAll();
        Assertions.assertThat(animes).isNotNull().hasSameElementsAs(animesList);
    }

    @Test
    @DisplayName("findById returns a anime with given id")
    @Order(2)
    void findById_ReturnsAnimeById_WhenSuccessfull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);
        var expectedAnime = animesList.getFirst();
        var anime = repository.findById(expectedAnime.getId());
        Assertions.assertThat(anime).isPresent().contains(expectedAnime);
    }

    @Test
    @DisplayName("Save creates a anime")
    @Order(3)
    void save_CreatesAnime_WhenSuccessfull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animeToSave = Anime.builder().id(4L).name("Pokemon").build();
        var anime = repository.save(animeToSave);

        Assertions.assertThat(anime).isEqualTo(animeToSave).hasNoNullFieldsOrProperties();

        var animeSaveOptional = repository.findById(animeToSave.getId());
        Assertions.assertThat(animeSaveOptional).isPresent().contains(animeToSave);

        Assertions.assertThat(animeSaveOptional.get()).isNotNull();
    }

    @Test
    @DisplayName("Delete removes a anime")
    @Order(4)
    void delete_RemoveAnime_WhenSuccessfull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);

        var animeToDelete = animesList.getFirst();
        repository.delete(animeToDelete);

        Assertions.assertThat(repository.findById(animeToDelete.getId())).isEmpty();
    }

    @Test
    @DisplayName("Update updates a anime")
    @Order(5)
    void update_UpdatesAnime_WhenSuccessfull() {
        BDDMockito.when(animeData.getAnimes()).thenReturn(animesList);
        
        var animeToUpdate = animesList.getFirst();
        animeToUpdate.setName("One Punch Man");
        repository.update(animeToUpdate);

        Assertions.assertThat(this.animesList).contains(animeToUpdate);

        var animeUpdatedOpcional = repository.findById(animeToUpdate.getId());

        Assertions.assertThat(animeUpdatedOpcional).isPresent();
        Assertions.assertThat(animeUpdatedOpcional.get().getName()).isEqualTo(animeToUpdate.getName());
    }

}
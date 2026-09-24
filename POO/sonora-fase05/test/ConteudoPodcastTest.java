import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConteudoPodcastTest {

    @Test
    @DisplayName("Fase 05 - Musica herda de Conteudo")
    public void musica_herdaDeConteudo() {
        Musica musica = new Musica("Bohemian Rhapsody", "Queen", 355);
        assertTrue(musica instanceof Conteudo);
    }

    @Test
    @DisplayName("Fase 05 - Conteudo rejeita titulo vazio")
    public void conteudo_tituloVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Conteudo("", 120));
    }

    @Test
    @DisplayName("Fase 05 - Podcast valida numero de episodio")
    public void podcast_numeroEpisodioInvalido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", "Guy Raz", 1800, 0));
    }

    @Test
    @DisplayName("Fase 05 - Musica reutiliza toString da superclasse")
    public void musica_toString_reaproveitaConteudo() {
        Musica musica = new Musica("Aquarela", "Toquinho", 180);
        assertTrue(musica.toString().contains("Aquarela"));
        assertTrue(musica.toString().contains("Toquinho"));
        assertEquals(180, musica.getDuracaoSegundos());
    }
}

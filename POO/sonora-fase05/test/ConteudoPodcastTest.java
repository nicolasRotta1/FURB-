import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConteudoPodcastTest {

    @Test
    @DisplayName("PL26 - Conteudo rejeita titulo vazio")
    public void Conteudo_rejeita_titulo_vazio() {
        assertThrows(IllegalArgumentException.class, () -> new Conteudo("", 120));
    }

    @Test
    @DisplayName("PL27 - Podcast valida numero de episodio")
    public void Podcast_valida_numero_de_episodio() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", "Guy Raz", 1800, 0));
    }
}

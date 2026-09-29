import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PodcastTest {

    @Test
    @DisplayName("PL26 - Podcast com apresentador vazio lança IllegalArgumentException")
    public void construtorPodcast_apresentadorVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", "", 1800, 1));
    }

    @Test
    @DisplayName("PL26 - Podcast com apresentador nulo lança IllegalArgumentException")
    public void construtorPodcast_apresentadorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", null, 1800, 1));
    }

    @Test
    @DisplayName("PL27 - Podcast com número de episódio inválido lança IllegalArgumentException")
    public void construtorPodcast_numeroEpisodioInvalido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", "Guy Raz", 1800, 0));
    }

    @Test
    @DisplayName("PL27 - Podcast com número de episódio negativo lança IllegalArgumentException")
    public void construtorPodcast_numeroEpisodioNegativo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Podcast("How I Built This", "Guy Raz", 1800, -3));
    }

    @Test
    @DisplayName("PL28 - Dados válidos criam um podcast corretamente")
    public void construtorPodcast_dadosValidos_criaPodcastCorretamente() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        assertEquals("How I Built This", podcast.getTitulo());
        assertEquals("Guy Raz", podcast.getApresentador());
        assertEquals(1800, podcast.getDuracaoSegundos());
        assertEquals(12, podcast.getNumeroEpisodio());
    }

    @Test
    @DisplayName("PL28 - setApresentador com valor válido atualiza o apresentador")
    public void setApresentador_valorValido_atualizaApresentador() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        podcast.setApresentador("Npratta");

        assertEquals("Npratta", podcast.getApresentador());
    }

    @Test
    @DisplayName("PL28 - setApresentador com valor nulo lança IllegalArgumentException")
    public void setApresentador_valorNulo_lancaIllegalArgumentException() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        assertThrows(IllegalArgumentException.class, () -> podcast.setApresentador(null));
    }

    @Test
    @DisplayName("PL29 - setNumeroEpisodio com valor válido atualiza o episódio")
    public void setNumeroEpisodio_valorValido_atualizaNumeroEpisodio() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        podcast.setNumeroEpisodio(21);

        assertEquals(21, podcast.getNumeroEpisodio());
    }

    @Test
    @DisplayName("PL29 - setNumeroEpisodio com valor zero lança IllegalArgumentException")
    public void setNumeroEpisodio_valorZero_lancaIllegalArgumentException() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        assertThrows(IllegalArgumentException.class, () -> podcast.setNumeroEpisodio(0));
    }

    @Test
    @DisplayName("PL30 - toString do podcast inclui título, apresentador e episódio")
    public void toString_podcastComDadosValidos_retornaTextoEsperado() {
        Podcast podcast = new Podcast("How I Built This", "Guy Raz", 1800, 12);

        String texto = podcast.toString();

        assertTrue(texto.contains("How I Built This"));
        assertTrue(texto.contains("Guy Raz"));
        assertTrue(texto.contains("Episódio 12"));
    }
}

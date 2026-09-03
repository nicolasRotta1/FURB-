
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;;
import static org.junit.Assert.assertThrows;;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class MusicaTeste {

 private static Musica musica;

    @BeforeAll
    public static void inicializaMusica() {
        musica = new Musica("Titulo", "Artista", 120);
    }

    @Test
    public void testCriacaoMusica() {
        assertNotNull(musica);
    }

    @Test
    public void testTituloInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            Musica mus = new Musica("", "Artista", 150);
        });
    }

    @Test
    public void testDuracaoValida() {
        assertEquals(120, musica.getDuracaoSegundos());
    }

    @Test
    public void testDuracaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            Musica mus = new Musica("Titulo", "Artista", -150);
        });
    }

}

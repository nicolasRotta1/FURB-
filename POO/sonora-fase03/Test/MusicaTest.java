import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MusicaTest {
    private Musica musica;

    @BeforeEach
    public void setUp() {
        musica = new Musica("Titulo", "Artista", 120);
    }

    @Test
    @DisplayName("PL01 - Duração com minutos e segundos resulta em 02:05")
    public void getDuracaoFormatada_duracaoComMinutosESegundos_retornaFormatoCorreto() {
        Musica m = new Musica("Teste", "Artista", 125);
        assertEquals("02:05", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("PL01 - Duração redonda em minutos resulta em 01:30")
    public void getDuracaoFormatada_duracaoRedonda_retornaFormatoCorreto() {
        Musica m = new Musica("Teste", "Artista", 90);
        assertEquals("01:30", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("PL01 - Menos de um minuto deve mostrar zero à esquerda")
    public void getDuracaoFormatada_menosDeUmMinuto_retornaZeroAEsquerda() {
        Musica m = new Musica("Teste", "Artista", 5);
        assertEquals("00:05", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("PL01 - Duração de 600 segundos resulta em 10:00")
    public void getDuracaoFormatada_doisDigitosNosMinutos_retornaFormatoCorreto() {
        Musica m = new Musica("Teste", "Artista", 600);
        assertEquals("10:00", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("PL01 - Duração de 599 segundos resulta em 09:59")
    public void getDuracaoFormatada_valorLogoAbaixoDeDezMinutos_retornaFormatoCorreto() {
        Musica m = new Musica("Teste", "Artista", 599);
        assertEquals("09:59", m.getDuracaoFormatada());
    }

    @Test
    @DisplayName("PL02 - Título vazio deve ser rejeitado")
    public void construtorMusica_tituloVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Musica("", "Queen", 355));
    }

    @Test
    @DisplayName("PL02 - Título nulo deve ser rejeitado")
    public void construtorMusica_tituloNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Musica(null, "Queen", 355));
    }

    @Test
    @DisplayName("PL02 - Artista vazio deve ser rejeitado")
    public void construtorMusica_artistaVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Musica("Bohemian Rhapsody", "", 355));
    }

    @Test
    @DisplayName("PL02 - Duração zero deve ser rejeitada")
    public void construtorMusica_duracaoZero_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Musica("Bohemian Rhapsody", "Queen", 0));
    }

    @Test
    @DisplayName("PL02 - Duração negativa deve ser rejeitada")
    public void construtorMusica_duracaoNegativa_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Musica("Bohemian Rhapsody", "Queen", -10));
    }

    @Test
    @DisplayName("PL02 - Dados válidos criam música com id maior que zero")
    public void construtorMusica_dadosValidos_criaMusicaComIdMaiorQueZero() {
        Musica m = new Musica("Bohemian Rhapsody", "Queen", 355);
        assertNotNull(m);
        assertTrue(m.getId() > 0);
    }

    @Test
    @DisplayName("PL07 - Uma reprodução aumenta o contador em 1")
    public void reproduzir_umaVez_aumentaContadorEmUm() {
        musica.reproduzir();
        assertEquals(1, musica.getReproducoes());
    }

    @Test
    @DisplayName("PL07 - Três reproduções acumulam contador em 3")
    public void reproduzir_tresVezes_acumulaReproducoes() {
        musica.reproduzir();
        musica.reproduzir();
        musica.reproduzir();
        assertEquals(3, musica.getReproducoes());
    }

    @Test
    @DisplayName("PL07 - Reproduzir não altera título, artista ou duração")
    public void reproduzir_naoAlteraOutrosAtributos() {
        musica.reproduzir();
        assertEquals("Titulo", musica.getTitulo());
        assertEquals("Artista", musica.getArtista());
        assertEquals(120, musica.getDuracaoSegundos());
    }

    @Test
    @DisplayName("PL09 - setTitulo com valor válido atualiza o título")
    public void setTitulo_valorValido_atualizaTitulo() {
        musica.setTitulo("Novo Título");
        assertEquals("Novo Título", musica.getTitulo());
    }

    @Test
    @DisplayName("PL09 - setTitulo com valor nulo lança IllegalArgumentException")
    public void setTitulo_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setTitulo(null));
    }

    @Test
    @DisplayName("PL09 - setTitulo com valor em branco lança IllegalArgumentException")
    public void setTitulo_valorEmBranco_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setTitulo("   "));
    }

    @Test
    @DisplayName("PL09 - setTitulo com valor vazio lança IllegalArgumentException")
    public void setTitulo_valorVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setTitulo(""));
    }

    @Test
    @DisplayName("PL09 - setArtista com valor válido atualiza o artista")
    public void setArtista_valorValido_atualizaArtista() {
        musica.setArtista("Novo Artista");
        assertEquals("Novo Artista", musica.getArtista());
    }

    @Test
    @DisplayName("PL09 - setArtista com valor nulo lança IllegalArgumentException")
    public void setArtista_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setArtista(null));
    }

    @Test
    @DisplayName("PL09 - setArtista com valor em branco lança IllegalArgumentException")
    public void setArtista_valorEmBranco_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setArtista("   "));
    }

    @Test
    @DisplayName("PL09 - setArtista com valor vazio lança IllegalArgumentException")
    public void setArtista_valorVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setArtista(""));
    }

    @Test
    @DisplayName("PL09 - setDuracaoSegundos com valor válido atualiza duração")
    public void setDuracaoSegundos_valorValido_atualizaDuracao() {
        musica.setDuracaoSegundos(200);
        assertEquals(200, musica.getDuracaoSegundos());
    }

    @Test
    @DisplayName("PL09 - setDuracaoSegundos com valor zero lança IllegalArgumentException")
    public void setDuracaoSegundos_valorZero_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setDuracaoSegundos(0));
    }

    @Test
    @DisplayName("PL09 - setDuracaoSegundos com valor negativo lança IllegalArgumentException")
    public void setDuracaoSegundos_valorNegativo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> musica.setDuracaoSegundos(-5));
    }
}

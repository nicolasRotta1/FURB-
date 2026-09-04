import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaylistTest {
    private Usuario usuario;
    private Plataforma plataforma;
    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("Ana", "ana@mail.com");
        plataforma = new Plataforma();
        playlist = new Playlist("Favoritas", usuario, plataforma);
    }

    @Test
    @DisplayName("PL12 - Playlist com nome vazio lança IllegalArgumentException")
    public void construtorPlaylist_nomeVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Playlist("", usuario, plataforma));
    }

    @Test
    @DisplayName("PL12 - Playlist com nome nulo lança IllegalArgumentException")
    public void construtorPlaylist_nomeNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Playlist(null, usuario, plataforma));
    }

    @Test
    @DisplayName("PL12 - Playlist com dono nulo lança IllegalArgumentException")
    public void construtorPlaylist_donoNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Playlist("Favoritas", null, plataforma));
    }

    @Test
    @DisplayName("PL12 - Playlist válida sem plataforma cria objeto corretamente")
    public void construtorPlaylist_dadosValidosSemPlataforma_criaPlaylist() {
        Playlist p = new Playlist("Favoritas", usuario);
        assertNotNull(p);
        assertEquals(0, p.getQuantidade());
    }

    @Test
    @DisplayName("PL12 - Playlist válida com plataforma cria objeto corretamente")
    public void construtorPlaylist_dadosValidosComPlataforma_criaPlaylist() {
        Playlist p = new Playlist("Favoritas", usuario, plataforma);
        assertNotNull(p);
        assertEquals(0, p.getQuantidade());
    }

    @Test
    @DisplayName("PL13 - setNome com valor válido atualiza nome")
    public void setNomePlaylist_valorValido_atualizaNome() {
        playlist.setNome("Rock Clássico");
        assertEquals("Rock Clássico", playlist.getNome());
    }

    @Test
    @DisplayName("PL13 - setNome com valor nulo lança IllegalArgumentException")
    public void setNomePlaylist_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> playlist.setNome(null));
    }

    @Test
    @DisplayName("PL13 - setNome com valor em branco lança IllegalArgumentException")
    public void setNomePlaylist_valorEmBranco_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> playlist.setNome("   "));
    }

    @Test
    @DisplayName("PL13 - setNome com valor vazio lança IllegalArgumentException")
    public void setNomePlaylist_valorVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> playlist.setNome(""));
    }

    @Test
    @DisplayName("PL13 - setNome com espaços nas bordas não faz trim")
    public void setNomePlaylist_espacosNasBordas_naoFazTrim() {
        playlist.setNome("  Rock  ");
        assertEquals("  Rock  ", playlist.getNome());
    }

    @Test
    @DisplayName("PL13 - setDono com valor válido atualiza dono")
    public void setDonoPlaylist_valorValido_atualizaDono() {
        Usuario novo = new Usuario("Bruno", "bruno@mail.com");
        playlist.setDono(novo);
        assertEquals(novo, playlist.getDono());
    }

    @Test
    @DisplayName("PL13 - setDono com valor nulo lança IllegalArgumentException")
    public void setDonoPlaylist_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> playlist.setDono(null));
    }

    @Test
    @DisplayName("PL03 - Adicionar música com espaço disponível retorna true")
    public void adicionar_musicaValidaComEspaco_retornaTrue() {
        plataforma.cadastrarMusica(new Musica("Aquarela", "Toquinho", 180));
        Musica m = plataforma.getMusicaPorTitulo("Aquarela");
        assertTrue(playlist.adicionar(m));
        assertEquals(1, playlist.getQuantidade());
    }

    @Test
    @DisplayName("PL03 - Playlist cheia retorna false")
    public void adicionar_playlistCheia_retornaFalse() {
        Playlist cheia = new Playlist("Cheia", usuario, plataforma);
        for (int i = 0; i < 100; i++) {
            Musica m = new Musica("Musica " + i, "Artista", 100 + i);
            plataforma.cadastrarMusica(m);
            assertTrue(cheia.adicionar(m));
        }
        Musica extra = new Musica("Musica Extra", "Artista", 120);
        plataforma.cadastrarMusica(extra);
        assertFalse(cheia.adicionar(extra));
    }

    @Test
    @DisplayName("PL03 - Adicionar música nula lança IllegalArgumentException")
    public void adicionar_musicaNula_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> playlist.adicionar(null));
    }

    @Test
    @DisplayName("PL03 - Adicionar música repetida não duplica na playlist")
    public void adicionar_musicaDuplicada_retornaFalse() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        plataforma.cadastrarMusica(m);
        assertTrue(playlist.adicionar(m));
        assertFalse(playlist.adicionar(m));
    }

    @Test
    @DisplayName("PL03 - Adicionar música fora da plataforma retorna false")
    public void adicionar_musicaForaDaPlataforma_retornaFalse() {
        Musica fora = new Musica("Fora", "Artista", 150);
        assertFalse(playlist.adicionar(fora));
    }

    @Test
    @DisplayName("PL04 - getNaPosicao com índice válido retorna música correta")
    public void getNaPosicao_indiceValido_retornaMusicaCorreta() {
        Musica m1 = new Musica("A", "X", 100);
        Musica m2 = new Musica("B", "Y", 110);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        playlist.adicionar(m1);
        playlist.adicionar(m2);
        assertEquals(m2, playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("PL04 - getNaPosicao com índice negativo lança IndexOutOfBoundsException")
    public void getNaPosicao_indiceNegativo_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getNaPosicao(-1));
    }

    @Test
    @DisplayName("PL04 - getNaPosicao com índice igual à quantidade lança IndexOutOfBoundsException")
    public void getNaPosicao_indiceIgualAQuantidade_lancaException() {
        Musica m1 = new Musica("A", "X", 100);
        Musica m2 = new Musica("B", "Y", 110);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        playlist.adicionar(m1);
        playlist.adicionar(m2);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getNaPosicao(2));
    }

    @Test
    @DisplayName("PL04 - getNaPosicao com índice fora da faixa lança IndexOutOfBoundsException")
    public void getNaPosicao_indiceForaDaFaixa_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.getNaPosicao(1));
    }

    @Test
    @DisplayName("PL05 - removerNaPosicao de posição válida reorganiza a playlist")
    public void removerNaPosicao_posicaoValida_reorganizaPlaylist() {
        Musica a = new Musica("A", "X", 100);
        Musica b = new Musica("B", "Y", 110);
        Musica c = new Musica("C", "Z", 120);
        plataforma.cadastrarMusica(a);
        plataforma.cadastrarMusica(b);
        plataforma.cadastrarMusica(c);
        playlist.adicionar(a);
        playlist.adicionar(b);
        playlist.adicionar(c);

        assertTrue(playlist.removerNaPosicao(0));
        assertEquals(b, playlist.getNaPosicao(0));
        assertEquals(2, playlist.getQuantidade());
    }

    @Test
    @DisplayName("PL05 - removerNaPosicao da última posição remove corretamente")
    public void removerNaPosicao_ultimaPosicao_reorganizaPlaylist() {
        Musica a = new Musica("A", "X", 100);
        Musica b = new Musica("B", "Y", 110);
        Musica c = new Musica("C", "Z", 120);
        plataforma.cadastrarMusica(a);
        plataforma.cadastrarMusica(b);
        plataforma.cadastrarMusica(c);
        playlist.adicionar(a);
        playlist.adicionar(b);
        playlist.adicionar(c);

        assertTrue(playlist.removerNaPosicao(2));
        assertEquals(a, playlist.getNaPosicao(0));
        assertEquals(b, playlist.getNaPosicao(1));
        assertEquals(2, playlist.getQuantidade());
    }

    @Test
    @DisplayName("PL05 - removerNaPosicao com índice negativo lança IndexOutOfBoundsException")
    public void removerNaPosicao_indiceNegativo_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removerNaPosicao(-1));
    }

    @Test
    @DisplayName("PL05 - removerNaPosicao com índice fora da faixa lança IndexOutOfBoundsException")
    public void removerNaPosicao_indiceForaDaFaixa_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.removerNaPosicao(1));
    }

    @Test
    @DisplayName("PL14 - getTitulo com título existente retorna a música correta")
    public void getTitulo_tituloExistente_retornaMusica() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertEquals(m, playlist.getTitulo("AQUARELA"));
    }

    @Test
    @DisplayName("PL14 - getTitulo com título inexistente retorna null")
    public void getTitulo_tituloInexistente_retornaNull() {
        assertNull(playlist.getTitulo("Não Existe"));
    }

    @Test
    @DisplayName("PL14 - getTitulo com título nulo retorna null")
    public void getTitulo_tituloNulo_retornaNull() {
        assertNull(playlist.getTitulo(null));
    }

    @Test
    @DisplayName("PL15 - atualizarPlaylist com dados válidos atualiza a música")
    public void atualizarPlaylist_dadosValidos_atualizaMusica() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);

        assertTrue(playlist.atualizarPlaylist(0, "Novo Título", "Novo Artista", 200));
        assertEquals("Novo Título", m.getTitulo());
        assertEquals("Novo Artista", m.getArtista());
        assertEquals(200, m.getDuracaoSegundos());
    }

    @Test
    @DisplayName("PL15 - atualizarPlaylist com índice inválido lança IndexOutOfBoundsException")
    public void atualizarPlaylist_indiceInvalido_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertThrows(IndexOutOfBoundsException.class, () -> playlist.atualizarPlaylist(5, "Nova", "Artista", 200));
    }

    @Test
    @DisplayName("PL15 - atualizarPlaylist com dados inválidos lança IllegalArgumentException")
    public void atualizarPlaylist_dadosInvalidos_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertThrows(IllegalArgumentException.class, () -> playlist.atualizarPlaylist(0, "", "Artista", 200));
    }

    @Test
    @DisplayName("PL16 - getDuracaoTotalSegundos com playlist vazia retorna 0")
    public void getDuracaoTotalSegundos_playlistVazia_retornaZero() {
        assertEquals(0, playlist.getDuracaoTotalSegundos());
    }

    @Test
    @DisplayName("PL16 - getDuracaoTotalSegundos com uma música retorna a duração correta")
    public void getDuracaoTotalSegundos_umaMusica_retornaDuracao() {
        Musica m = new Musica("A", "X", 200);
        plataforma.cadastrarMusica(m);
        playlist.adicionar(m);
        assertEquals(200, playlist.getDuracaoTotalSegundos());
    }

    @Test
    @DisplayName("PL16 - getDuracaoTotalSegundos com múltiplas músicas soma as durações")
    public void getDuracaoTotalSegundos_multiplasMusicas_retornaSoma() {
        Musica m1 = new Musica("A", "X", 200);
        Musica m2 = new Musica("B", "Y", 180);
        Musica m3 = new Musica("C", "Z", 90);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        plataforma.cadastrarMusica(m3);
        playlist.adicionar(m1);
        playlist.adicionar(m2);
        playlist.adicionar(m3);
        assertEquals(470, playlist.getDuracaoTotalSegundos());
    }

    @Test
    @DisplayName("PL17 - reproduzirTudo em playlist vazia não lança erro")
    public void reproduzirTudo_playlistVazia_naoLancaErro() {
        playlist.reproduzirTudo();
        assertEquals(0, playlist.getQuantidade());
    }

    @Test
    @DisplayName("PL17 - reproduzirTudo incrementa reprodução de todas as músicas")
    public void reproduzirTudo_playlistComMusicas_incrementaReproducao() {
        Musica m1 = new Musica("A", "X", 100);
        Musica m2 = new Musica("B", "Y", 110);
        Musica m3 = new Musica("C", "Z", 120);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        plataforma.cadastrarMusica(m3);
        playlist.adicionar(m1);
        playlist.adicionar(m2);
        playlist.adicionar(m3);

        playlist.reproduzirTudo();
        assertEquals(1, m1.getReproducoes());
        assertEquals(1, m2.getReproducoes());
        assertEquals(1, m3.getReproducoes());
    }

    @Test
    @DisplayName("PL17 - reproduzirTudo chamado duas vezes acumula reprodução nas músicas")
    public void reproduzirTudo_chamadoDuasVezes_acumulaReproducao() {
        Musica m1 = new Musica("A", "X", 100);
        Musica m2 = new Musica("B", "Y", 110);
        plataforma.cadastrarMusica(m1);
        plataforma.cadastrarMusica(m2);
        playlist.adicionar(m1);
        playlist.adicionar(m2);

        playlist.reproduzirTudo();
        playlist.reproduzirTudo();

        assertEquals(2, m1.getReproducoes());
        assertEquals(2, m2.getReproducoes());
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlataformaTest {
    private Plataforma plataforma;
    private Usuario usuario;
    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        plataforma = new Plataforma();
        usuario = new Usuario("Ana", "ana@mail.com");
        playlist = new Playlist("Favoritas", usuario, plataforma);
    }

    @Test
    @DisplayName("PL06 - getMusicaPorTitulo com título cadastrado exato retorna a música correspondente")
    public void getMusicaPorTitulo_tituloExato_retornaMusica() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        plataforma.cadastrarMusica(m);
        assertEquals(m, plataforma.getMusicaPorTitulo("Aquarela"));
    }

    @Test
    @DisplayName("PL06 - getMusicaPorTitulo com música cadastrada e caixa diferente retorna a música correspondente")
    public void getMusicaPorTitulo_tituloEmCaixaDiferente_retornaMusica() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        plataforma.cadastrarMusica(m);
        assertEquals(m, plataforma.getMusicaPorTitulo("aquarela"));
    }

    @Test
    @DisplayName("PL06 - getMusicaPorTitulo com título inexistente retorna null")
    public void getMusicaPorTitulo_tituloInexistente_retornaNull() {
        assertNull(plataforma.getMusicaPorTitulo("Não existe"));
    }

    @Test
    @DisplayName("PL06 - getMusicaPorTitulo com título nulo retorna null")
    public void getMusicaPorTitulo_tituloNulo_retornaNull() {
        assertNull(plataforma.getMusicaPorTitulo(null));
    }

    @Test
    @DisplayName("PL06 - getMusica com id cadastrado retorna a música correspondente")
    public void getMusica_idCadastrado_retornaMusica() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        plataforma.cadastrarMusica(m);
        assertEquals(m, plataforma.getMusica(m.getId()));
    }

    @Test
    @DisplayName("PL06 - getMusica com id inexistente lança IndexOutOfBoundsException")
    public void getMusica_idInexistente_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> plataforma.getMusica(9999));
    }

    @Test
    @DisplayName("PL18 - cadastrarMusica com valor válido retorna true")
    public void cadastrarMusica_musicaValida_retornaTrue() {
        Musica m = new Musica("Aquarela", "Toquinho", 180);
        assertTrue(plataforma.cadastrarMusica(m));
        assertEquals(1, plataforma.getTotalMusicas());
    }

    @Test
    @DisplayName("PL18 - cadastrarMusica com música nula lança IllegalArgumentException")
    public void cadastrarMusica_musicaNula_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> plataforma.cadastrarMusica(null));
    }

    @Test
    @DisplayName("PL18 - cadastrarMusica em plataforma cheia retorna false")
    public void cadastrarMusica_plataformaCheia_retornaFalse() {
        for (int i = 0; i < 500; i++) {
            plataforma.cadastrarMusica(new Musica("M " + i, "Artista", 100));
        }
        assertFalse(plataforma.cadastrarMusica(new Musica("Extra", "Artista", 100)));
    }

    @Test
    @DisplayName("PL19 - atualizarMusica com dados válidos retorna true")
    public void atualizarMusica_dadosValidos_retornaTrue() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        assertTrue(plataforma.atualizarMusica(m.getId(), "Novo", "Novo Artista", 200));
        assertEquals("Novo", m.getTitulo());
        assertEquals("Novo Artista", m.getArtista());
        assertEquals(200, m.getDuracaoSegundos());
    }

    @Test
    @DisplayName("PL19 - atualizarMusica com título nulo lança IllegalArgumentException")
    public void atualizarMusica_tituloNulo_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarMusica(m.getId(), null, "Artista", 200));
    }

    @Test
    @DisplayName("PL19 - atualizarMusica com artista nulo lança IllegalArgumentException")
    public void atualizarMusica_artistaNulo_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarMusica(m.getId(), "Titulo", null, 200));
    }

    @Test
    @DisplayName("PL19 - atualizarMusica com id inexistente lança IndexOutOfBoundsException")
    public void atualizarMusica_idInexistente_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> plataforma.atualizarMusica(9999, "Titulo", "Artista", 200));
    }

    @Test
    @DisplayName("PL19 - atualizarMusica com duração inválida lança IllegalArgumentException")
    public void atualizarMusica_duracaoInvalida_lancaException() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarMusica(m.getId(), "Titulo", "Artista", -5));
    }

    @Test
    @DisplayName("PL20 - getPlaylists sem playlists cadastradas retorna array vazio")
    public void getPlaylists_semPlaylists_retornaArrayVazio() {
        assertEquals(0, plataforma.getPlaylists().length);
    }

    @Test
    @DisplayName("PL20 - cadastrarPlaylist com playlist válida retorna true")
    public void cadastrarPlaylist_playlistValida_retornaTrue() {
        assertTrue(plataforma.cadastrarPlaylist(playlist));
        assertEquals(1, plataforma.getPlaylists().length);
    }

    @Test
    @DisplayName("PL20 - cadastrarPlaylist com playlist nula lança IllegalArgumentException")
    public void cadastrarPlaylist_playlistNula_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> plataforma.cadastrarPlaylist(null));
    }

    @Test
    @DisplayName("PL20 - cadastrarPlaylist com capacidade cheia retorna false")
    public void cadastrarPlaylist_plataformaCheia_retornaFalse() {
        for (int i = 0; i < 500; i++) {
            plataforma.cadastrarPlaylist(new Playlist("P " + i, usuario, plataforma));
        }
        assertFalse(plataforma.cadastrarPlaylist(new Playlist("Extra", usuario, plataforma)));
    }

    @Test
    @DisplayName("PL21 - removerMusica de música cadastrada retorna true")
    public void removerMusica_musicaCadastrada_retornaTrue() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        assertTrue(plataforma.removerMusica(m.getId()));
        assertEquals(0, plataforma.getTotalMusicas());
    }

    @Test
    @DisplayName("PL21 - removerMusica remove também da playlist")
    public void removerMusica_musicaEmPlaylist_removeDaPlaylist() {
        Musica m = new Musica("A", "X", 100);
        plataforma.cadastrarMusica(m);
        plataforma.cadastrarPlaylist(playlist);
        playlist.adicionar(m);

        plataforma.removerMusica(m.getId());
        assertEquals(0, playlist.getQuantidade());
    }

    @Test
    @DisplayName("PL21 - removerMusica com id inexistente lança IndexOutOfBoundsException")
    public void removerMusica_idInexistente_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> plataforma.removerMusica(9999));
    }

    @Test
    @DisplayName("PL22 - cadastrarUsuario com usuário válido retorna true")
    public void cadastrarUsuario_usuarioValido_retornaTrue() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        assertTrue(plataforma.cadastrarUsuario(u));
        assertEquals(1, plataforma.getTotalUsuarios());
    }

    @Test
    @DisplayName("PL22 - cadastrarUsuario com usuário nulo lança IllegalArgumentException")
    public void cadastrarUsuario_usuarioNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> plataforma.cadastrarUsuario(null));
    }

    @Test
    @DisplayName("PL22 - cadastrarUsuario em plataforma cheia retorna false")
    public void cadastrarUsuario_plataformaCheia_retornaFalse() {
        for (int i = 0; i < 500; i++) {
            plataforma.cadastrarUsuario(new Usuario("User " + i, "user" + i + "@mail.com"));
        }
        assertFalse(plataforma.cadastrarUsuario(new Usuario("Extra", "extra@mail.com")));
    }

    @Test
    @DisplayName("PL23 - getUsuario com id cadastrado retorna usuário correspondente")
    public void getUsuario_idCadastrado_retornaUsuario() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertEquals(u, plataforma.getUsuario(u.getId()));
    }

    @Test
    @DisplayName("PL23 - getUsuario com id inexistente lança IndexOutOfBoundsException")
    public void getUsuario_idInexistente_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> plataforma.getUsuario(9999));
    }

    @Test
    @DisplayName("PL23 - atualizarUsuario com dados válidos retorna true")
    public void atualizarUsuario_dadosValidos_retornaTrue() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertTrue(plataforma.atualizarUsuario(u.getId(), "Ana Paula", "anapaula@mail.com"));
        assertEquals("Ana Paula", u.getNome());
        assertEquals("anapaula@mail.com", u.getEmail());
    }

    @Test
    @DisplayName("PL23 - atualizarUsuario com nome nulo lança IllegalArgumentException")
    public void atualizarUsuario_nomeNulo_lancaException() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarUsuario(u.getId(), null, "ana@nova.com"));
    }

    @Test
    @DisplayName("PL23 - atualizarUsuario com email nulo lança IllegalArgumentException")
    public void atualizarUsuario_emailNulo_lancaException() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarUsuario(u.getId(), "Ana", null));
    }

    @Test
    @DisplayName("PL23 - atualizarUsuario com email inválido lança IllegalArgumentException")
    public void atualizarUsuario_emailInvalido_lancaException() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertThrows(IllegalArgumentException.class, () -> plataforma.atualizarUsuario(u.getId(), "Ana", "semarroba.com"));
    }

    @Test
    @DisplayName("PL24 - removerUsuario com usuário cadastrado retorna true")
    public void removerUsuario_usuarioCadastrado_retornaTrue() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        plataforma.cadastrarUsuario(u);
        assertTrue(plataforma.removerUsuario(u.getId()));
        assertEquals(0, plataforma.getTotalUsuarios());
    }

    @Test
    @DisplayName("PL24 - removerUsuario com id inexistente lança IndexOutOfBoundsException")
    public void removerUsuario_idInexistente_lancaException() {
        assertThrows(IndexOutOfBoundsException.class, () -> plataforma.removerUsuario(9999));
    }

    @Test
    @DisplayName("PL08 - ids de Música são sequenciais entre si")
    public void contadorIdsMusica_idsSaemSequenciais() {
        Musica a = new Musica("A", "X", 100);
        Musica b = new Musica("B", "Y", 110);
        assertEquals(a.getId() + 1, b.getId());
    }
}

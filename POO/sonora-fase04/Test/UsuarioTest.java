import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("Ana", "ana@mail.com");
    }

    @Test
    @DisplayName("PL10 - Usuário com nome vazio deve ser rejeitado")
    public void construtorUsuario_nomeVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("", "ana@mail.com"));
    }

    @Test
    @DisplayName("PL10 - Usuário com nome nulo deve ser rejeitado")
    public void construtorUsuario_nomeNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(null, "ana@mail.com"));
    }

    @Test
    @DisplayName("PL10 - Usuário com email vazio deve ser rejeitado")
    public void construtorUsuario_emailVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("Ana", ""));
    }

    @Test
    @DisplayName("PL10 - Usuário com email sem @ deve ser rejeitado")
    public void construtorUsuario_emailSemArroba_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("Ana", "anamail.com"));
    }

    @Test
    @DisplayName("PL10 - Usuário com email começando por @ deve ser rejeitado")
    public void construtorUsuario_emailComecandoComArroba_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("Ana", "@mail.com"));
    }

    @Test
    @DisplayName("PL10 - Usuário com email terminando por @ deve ser rejeitado")
    public void construtorUsuario_emailTerminandoComArroba_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario("Ana", "ana@"));
    }

    @Test
    @DisplayName("PL10 - Dados válidos criam usuário com id maior que zero")
    public void construtorUsuario_dadosValidos_criaUsuarioComIdMaiorQueZero() {
        Usuario u = new Usuario("Ana", "ana@mail.com");
        assertNotNull(u);
        assertTrue(u.getId() > 0);
    }

    @Test
    @DisplayName("PL11 - setNome com valor válido atualiza o nome")
    public void setNome_valorValido_atualizaNome() {
        usuario.setNome("Ana Paula");
        assertEquals("Ana Paula", usuario.getNome());
    }

    @Test
    @DisplayName("PL11 - setNome com valor nulo lança IllegalArgumentException")
    public void setNome_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome(null));
    }

    @Test
    @DisplayName("PL11 - setNome com valor em branco lança IllegalArgumentException")
    public void setNome_valorEmBranco_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome("   "));
    }

    @Test
    @DisplayName("PL11 - setNome com valor vazio lança IllegalArgumentException")
    public void setNome_valorVazio_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setNome(""));
    }

    @Test
    @DisplayName("PL11 - setNome com espaços nas bordas não faz trim")
    public void setNome_espacosNasBordas_naoFazTrim() {
        usuario.setNome("  Ana  ");
        assertEquals("  Ana  ", usuario.getNome());
    }

    @Test
    @DisplayName("PL11 - setEmail com valor válido atualiza o email")
    public void setEmail_valorValido_atualizaEmail() {
        usuario.setEmail("novo@mail.com");
        assertEquals("novo@mail.com", usuario.getEmail());
    }

    @Test
    @DisplayName("PL11 - setEmail com valor nulo lança IllegalArgumentException")
    public void setEmail_valorNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail(null));
    }

    @Test
    @DisplayName("PL11 - setEmail com valor em branco lança IllegalArgumentException")
    public void setEmail_valorEmBranco_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail("   "));
    }

    @Test
    @DisplayName("PL11 - setEmail com valor sem @ lança IllegalArgumentException")
    public void setEmail_valorInvalido_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setEmail("semarroba.com"));
    }

    @Test
    @DisplayName("PL08 - ids de Usuário são independentes dos ids de Música")
    public void contadorIdsUsuario_idsSaoIndependentes() {
        Musica musica1 = new Musica("A", "X", 100);
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");
        assertTrue(u1.getId() > 0);
        assertTrue(u2.getId() > 0);
        assertTrue(musica1.getId() > 0);
    }

    @Test
    @DisplayName("PL25 - seguir com usuário válido adiciona à lista")
    public void seguir_usuarioValido_adicionaNaLista() {
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");

        u1.seguir(u2);

        assertEquals(1, u1.getQuantidadeSeguindo());
        assertTrue(u1.getSeguindo().contains(u2));
    }

    @Test
    @DisplayName("PL25 - seguir usuário duplicado não adiciona duas vezes")
    public void seguir_usuarioDuplicado_naoDuplica() {
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");

        u1.seguir(u2);
        u1.seguir(u2);

        assertEquals(1, u1.getQuantidadeSeguindo());
    }

    @Test
    @DisplayName("PL25 - seguir a si mesmo lança IllegalArgumentException")
    public void seguir_mesmoUsuario_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.seguir(usuario));
    }

    @Test 
    @DisplayName("PL25 - seguir usuário nulo lança IllegalArgumentException")
    public void seguir_usuarioNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.seguir(null));
    }

    @Test
    @DisplayName("PL25 - deixarDeSeguir remove usuário da lista")
    public void deixarDeSeguir_usuarioExistente_removeDaLista() {
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");

        u1.seguir(u2);
        u1.deixarDeSeguir(u2);

        assertEquals(0, u1.getQuantidadeSeguindo());
    }

    @Test 
    @DisplayName ("PL25 - deixar de seguir usuario inexistente retorna false")
    public void deixarDeSeguir_usuarioInexistente_retornaFalse() {
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");

        boolean result = u1.deixarDeSeguir(u2);

        assertFalse(result);
    }

    @Test 
    @DisplayName("PL25 - deixarDeSeguir usuário não existente não altera a lista")
    public void deixarDeSeguir_usuarioNaoExistente_naoAlteraLista() {
        Usuario u1 = new Usuario("Ana", "ana@mail.com");
        Usuario u2 = new Usuario("Bia", "bia@mail.com");

        u1.deixarDeSeguir(u2);

        assertEquals(0, u1.getQuantidadeSeguindo());
    }

    @Test 
    @DisplayName("PL25 - deixarDeSeguir usuário nulo lança IllegalArgumentException")
    public void deixarDeSeguir_usuarioNulo_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> usuario.deixarDeSeguir(null));
    }


}

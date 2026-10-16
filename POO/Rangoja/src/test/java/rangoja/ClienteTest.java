package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClienteTest {
    private Cliente ana;
    private Cliente beto;

    @BeforeEach
    void setUp() {
        ana = new Cliente("Ana", "ana@furb.br", 20);
        beto = new Cliente("Beto", "beto@furb.br", 17, ana);
    }

    @Test
    void deveLancarExcecaoParaIdadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("Ana", "ana@furb.br", -1));
    }

    @Test
    void deveLancarExcecaoParaEmailSemArroba() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("Ana", "ana.furb.br", 20));
    }

    @Test
    void deveIndicacaoCreditarCashbackNoIndicador() {
        Cliente indicador = new Cliente("Maria", "maria@furb.br", 30);
        Cliente indicado = new Cliente("Joao", "joao@furb.br", 22, indicador);

        assertEquals(10.0, indicador.getSaldoCashback(), 0.001);
        assertEquals(0.0, indicado.getSaldoCashback(), 0.001);
        assertEquals(indicador, indicado.getIndicadoPor());
    }

    @Test
    void deveLancarExcecaoQuandoSaldoForInsuficiente() {
        Cliente cliente = new Cliente("Ana", "ana@furb.br", 20);
        cliente.creditarCashback(10.0);

        assertThrows(SaldoInsuficienteException.class, () -> cliente.usarCashback(15.0));
    }

    @Test
    void deveCompararClientesPeloEmailIgnorandoCaixa() {
        Cliente cliente1 = new Cliente("Ana", "ana@furb.br", 20);
        Cliente cliente2 = new Cliente("Ana Souza", "ANA@FURB.BR", 40);

        assertTrue(cliente1.equals(cliente2));
        assertEquals(cliente1.hashCode(), cliente2.hashCode());
    }

    @Test
    void deveLancarExcecaoQuandoIndicadorForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("Beto", "beto@furb.br", 17, null));
    }

    @Test
    void deveCadastrarClienteValido() {
        Cliente cliente = new Cliente("Ana Souza", "ana@furb.br", 25);

        assertEquals("Ana Souza", cliente.getNome());
        assertEquals("ana@furb.br", cliente.getEmail());
        assertEquals(25, cliente.getIdade());
        assertEquals(0.0, cliente.getSaldoCashback(), 0.001);
    }

    @Test
    void deveLancarExcecaoParaNomeOuEmailVazios() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("", "ana@furb.br", 20));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("Ana Souza", "", 20));
    }

    @Test
    void deveValidarIdade() {
        Cliente cliente = new Cliente("Ana Souza", "ana@furb.br", 20);
        cliente.setIdade(18);
        assertTrue(cliente.isMaiorDeIdade());

        assertThrows(IllegalArgumentException.class, () -> cliente.setIdade(-1));
        assertThrows(IllegalArgumentException.class, () -> cliente.setIdade(121));
    }

    @Test
    void deveCreditarEUsarCashback() throws SaldoInsuficienteException {
        Cliente cliente = new Cliente("Ana Souza", "ana@furb.br", 20);
        cliente.creditarCashback(15.0);
        assertEquals(15.0, cliente.getSaldoCashback(), 0.001);

        cliente.usarCashback(5.0);
        assertEquals(10.0, cliente.getSaldoCashback(), 0.001);
    }

    @Test
    void deveGerarToStringNoFormatoEsperado() {
        Cliente cliente = new Cliente("Ana Souza", "ana@furb.br", 20);
        assertEquals("Ana Souza ana@furb.br", cliente.toString());
    }

    @Test
    void deveRetornarFalseParaObjetosDiferentes() {
        Cliente cliente1 = new Cliente("Ana Souza", "ana@furb.br", 20);
        Cliente cliente2 = new Cliente("Bea", "bea@furb.br", 21);

        assertFalse(cliente1.equals(cliente2));
    }
}

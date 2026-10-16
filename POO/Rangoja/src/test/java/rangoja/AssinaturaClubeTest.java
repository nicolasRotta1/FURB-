package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssinaturaClubeTest {
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana", "ana@furb.br", 25);
    }

    @Test
    void deveCalcularValorDaAssinaturaComDescontoParaDozeMeses() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        assertEquals(202.98, assinatura.calcularValorCobrar(), 0.001);
    }

    @Test
    void deveCalcularValorDaAssinaturaSemDescontoParaSeisMeses() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 20.00, 6);

        assertEquals(120.00, assinatura.calcularValorCobrar(), 0.001);
    }

    @Test
    void deveLancarExcecaoQuandoClienteForNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(null, 19.90, 12));
    }

    @Test
    void deveLancarExcecaoQuandoValorMensalForZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(cliente, 0.0, 12));
    }

    @Test
    void deveLancarExcecaoQuandoMesesForZero() {
        assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(cliente, 19.90, 0));
    }

    @Test
    void deveRetornarClienteValorMensalEMesesDaAssinatura() {
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        assertEquals(cliente, assinatura.getCliente());
        assertEquals(19.90, assinatura.getValorMensal(), 0.001);
        assertEquals(12, assinatura.getMeses());
    }

    @Test
    void deveLancarExcecaoQuandoValorMensalForNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new AssinaturaClube(cliente, -5.0, 6));
    }
}

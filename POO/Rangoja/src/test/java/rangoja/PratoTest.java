package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PratoTest {
    private Prato prato;

    @BeforeEach
    void setUp() {
        prato = new Prato("Xis Salada", 30.0, 'M');
    }

    @Test
    void tamanhoInvalidoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Prato("Xis Salada", 30.0, 'X'));
    }

    @Test
    void pratoMedioComDesconto() {
        assertEquals(35.10, prato.calcularPrecoComDesconto(10), 0.001);
    }

    @Test
    void percentualNegativoOuMaiorQue100() {
        assertThrows(IllegalArgumentException.class, () -> prato.calcularPrecoComDesconto(120));
    }

    @Test
    void tamanhoMinusculoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Prato("Xis Salada", 30.0, 'm'));
    }

    @Test
    void toStringPrato() {
        assertEquals("Xis Salada - R$ 39,00 (tamanho M)", prato.toString());
    }
}

package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SobremesaTest {
    private Sobremesa pudim;

    @BeforeEach
    void setUp() {
        pudim = new Sobremesa("Pudim", 8.0, 150);
    }

    @Test
    void sobremesaPeso150() {
        assertEquals(12.0, pudim.calcularPreco(), 0.001);
    }

    @Test
    void sobremesaPesoZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Sobremesa("Pudim", 8.0, 0));
    }

    @Test
    void precoBaseZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Sobremesa("Pudim", 0.0, 150));
    }

    @Test
    void toStringSobremesa() {
        assertEquals("Pudim - R$ 12,00 (150 g)", pudim.toString());
    }
}

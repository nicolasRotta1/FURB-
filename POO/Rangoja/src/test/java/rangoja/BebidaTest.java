package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BebidaTest {
    private Bebida chopp;
    private Bebida refri;

    @BeforeEach
    void setUp() {
        chopp = new Bebida("Chopp", 12.0, 500, true);
        refri = new Bebida("Refri", 6.0, 350, false);
    }

    @Test
    void bebidaAlcoolica() {
        assertEquals(15.0, chopp.calcularPreco(), 0.001);
    }

    @Test
    void bebidaNaoAlcoolicaSemAcrescimo() {
        assertEquals(6.0, refri.calcularPreco(), 0.001);
    }

    @Test
    void volumeZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Bebida("Refri", 6.0, 0, false));
    }

    @Test
    void toStringBebidaNaoAlcoolica() {
        assertEquals("Refri - R$ 6,00 (350 ml)", refri.toString());
    }

    @Test
    void toStringBebidaAlcoolica() {
        assertEquals("Chopp - R$ 15,00 (500 ml) [alcoólica]", chopp.toString());
    }
}

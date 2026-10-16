package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ItemCardapioTest {
    @Test
    void deveCalcularPrecoDoPratoComTamanhoMedio() {
        Prato prato = new Prato("Xis Salada", 30.0, 'M');
        assertEquals(39.0, prato.calcularPreco(), 0.001);
        assertEquals(35.1, prato.calcularPrecoComDesconto(10), 0.001);
    }

    @Test
    void deveCalcularPrecoDaBebidaAlcoolica() {
        Bebida bebida = new Bebida("Chopp", 12.0, 500, true);
        assertEquals(15.0, bebida.calcularPreco(), 0.001);
    }

    @Test
    void deveCalcularPrecoDaSobremesa() {
        Sobremesa sobremesa = new Sobremesa("Pudim", 8.0, 150);
        assertEquals(12.0, sobremesa.calcularPreco(), 0.001);
    }

    @Test
    void deveLancarExcecaoParaNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Prato("", 20.0, 'P'));
    }

    @Test
    void deveLancarExcecaoParaPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Bebida("Refri", 0, 350, false));
    }

    @Test
    void deveLancarExcecaoParaPercentualInvalido() {
        Prato prato = new Prato("Xis", 20.0, 'P');
        assertThrows(IllegalArgumentException.class, () -> prato.calcularPrecoComDesconto(101));
    }
}

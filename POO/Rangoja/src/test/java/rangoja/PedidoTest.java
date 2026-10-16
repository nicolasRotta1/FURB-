package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PedidoTest {
    private Cliente clienteAdulto;
    private Cliente clienteMenor;
    private Prato prato;
    private Bebida bebida;
    private Sobremesa sobremesa;

    @BeforeEach
    void setUp() {
        clienteAdulto = new Cliente("Ana", "ana@furb.br", 25);
        clienteMenor = new Cliente("Joao", "joao@furb.br", 17);
        prato = new Prato("Xis Salada", 30.0, 'M');
        bebida = new Bebida("Refri", 6.0, 350, false);
        sobremesa = new Sobremesa("Pudim", 8.0, 150);
    }

    @Test
    void deveCalcularSubtotalTaxaEValorCobrarDoPedidoBase() {
        Pedido pedido = new Pedido(clienteAdulto);
        pedido.adicionarItem(prato);
        pedido.adicionarItem(bebida);
        pedido.adicionarItem(sobremesa);

        assertEquals(57.0, pedido.calcularSubtotal(), 0.001);
        assertEquals(7.0, pedido.calcularTaxaEntrega(), 0.001);
        assertEquals(64.0, pedido.calcularValorCobrar(), 0.001);
    }

    @Test
    void deveDarFreteGratisQuandoSubtotalForMaiorOuIgualA100() {
        Pedido pedido = new Pedido(clienteAdulto);
        pedido.adicionarItem(new Prato("Xis", 40.0, 'G'), 2);

        assertEquals(128.0, pedido.calcularSubtotal(), 0.001);
        assertEquals(0.0, pedido.calcularTaxaEntrega(), 0.001);
        assertEquals(128.0, pedido.calcularValorCobrar(), 0.001);
    }

    @Test
    void deveLancarExcecaoQuandoClienteMenorCompraBebidaAlcoolica() {
        Pedido pedido = new Pedido(clienteMenor);

        assertThrows(IllegalArgumentException.class,
                () -> pedido.adicionarItem(new Bebida("Chopp", 12.0, 500, true)));
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeForZero() {
        Pedido pedido = new Pedido(clienteAdulto);

        assertThrows(IllegalArgumentException.class,
                () -> pedido.adicionarItem(new Bebida("Refri", 6.0, 350, false), 0));
    }

    @Test
    void deveLancarExcecaoQuandoFinalizarPedidoSemItens() {
        Pedido pedido = new Pedido(clienteAdulto);

        assertThrows(IllegalStateException.class, pedido::finalizar);
    }

    @Test
    void deveConcederCashbackAoFinalizarPedido() {
        Pedido pedido = new Pedido(clienteAdulto);
        pedido.adicionarItem(prato);
        pedido.adicionarItem(bebida);
        pedido.adicionarItem(sobremesa);

        double saldoAntes = clienteAdulto.getSaldoCashback();
        pedido.finalizar();
        double saldoDepois = clienteAdulto.getSaldoCashback();

        assertEquals(2.85, saldoDepois - saldoAntes, 0.001);
        assertTrue(pedido.isFinalizado());
    }

    @Test
    void deveGerarNumerosConsecutivosParaPedidosCriados() {
        Pedido primeiro = new Pedido(clienteAdulto);
        Pedido segundo = new Pedido(clienteAdulto);

        assertEquals(1, segundo.getNumero() - primeiro.getNumero());
    }

    @Test
    void deveLancarExcecaoAoAdicionarItemNulo() {
        Pedido pedido = new Pedido(clienteAdulto);

        assertThrows(IllegalArgumentException.class, () -> pedido.adicionarItem(null));
    }

    @Test
    void deveLancarExcecaoAoAdicionarItemEmPedidoJaFinalizado() {
        Pedido pedido = new Pedido(clienteAdulto);
        pedido.adicionarItem(prato);
        pedido.finalizar();

        assertThrows(IllegalStateException.class,
                () -> pedido.adicionarItem(new Bebida("Agua", 4.0, 500, false)));
    }

    @Test
    void deveLancarExcecaoQuandoQuantidadeForNegativa() {
        Pedido pedido = new Pedido(clienteAdulto);

        assertThrows(IllegalArgumentException.class,
                () -> pedido.adicionarItem(prato, -2));
    }

    @Test
    void deveLancarExcecaoQuandoClienteForNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Pedido(null));
    }
}

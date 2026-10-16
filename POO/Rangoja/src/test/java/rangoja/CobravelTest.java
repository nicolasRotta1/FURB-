package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CobravelTest {
    @Test
    void deveCalcularValorCobrarPedidoEAssinatura() {
        Cliente cliente = new Cliente("Ana", "ana@furb.br", 25);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));
        pedido.adicionarItem(new Bebida("Refri", 6.0, 350, false));
        pedido.adicionarItem(new Sobremesa("Pudim", 8.0, 150));

        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        CentralCobranca central = new CentralCobranca();
        central.registrar(pedido);
        central.registrar(assinatura);

        assertEquals(64.0, pedido.calcularValorCobrar(), 0.001);
        assertEquals(202.98, assinatura.calcularValorCobrar(), 0.001);
        assertEquals(266.98, central.calcularTotalAReceber(), 0.001);
    }

    @Test
    void deveGerarComprovante() {
        Cliente cliente = new Cliente("Ana", "ana@furb.br", 25);
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));

        assertEquals("Valor a cobrar: R$ 46,00", pedido.gerarComprovante());
    }

    @Test
    void deveValidarCamposDaAssinatura() {
        Cliente cliente = new Cliente("Ana", "ana@furb.br", 25);

        assertThrows(IllegalArgumentException.class, () -> new AssinaturaClube(null, 10.0, 1));
        assertThrows(IllegalArgumentException.class, () -> new AssinaturaClube(cliente, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new AssinaturaClube(cliente, 10.0, 0));
    }

    @Test
    void deveGerarRelatorioComLinhas() {
        Cliente cliente = new Cliente("Ana", "ana@furb.br", 25);
        CentralCobranca central = new CentralCobranca();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));
        central.registrar(pedido);

        String relatorio = central.gerarRelatorio();
        assertEquals("Valor a cobrar: R$ 46,00\nTotal a receber: R$ 46,00", relatorio);
    }
}

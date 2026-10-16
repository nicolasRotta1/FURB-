package rangoja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CentralCobrancaTest {
    private Cliente cliente;
    private CentralCobranca central;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana", "ana@furb.br", 25);
        central = new CentralCobranca();
    }

    @Test
    void deveSomarPedidoEAssinaturaNoTotalAReceber() {
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));
        pedido.adicionarItem(new Bebida("Refri", 6.0, 350, false));
        pedido.adicionarItem(new Sobremesa("Pudim", 8.0, 150));

        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);
        central.registrar(pedido);
        central.registrar(assinatura);

        assertEquals(64.0, pedido.calcularValorCobrar(), 0.001);
        assertEquals(202.98, assinatura.calcularValorCobrar(), 0.001);
        assertEquals(266.98, central.calcularTotalAReceber(), 0.001);
    }

    @Test
    void deveLancarExcecaoQuandoRegistrarCobrancaNula() {
        assertThrows(IllegalArgumentException.class, () -> central.registrar(null));
    }

    @Test
    void deveGerarRelatorioComTextoFinalDeTotal() {
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));
        central.registrar(pedido);

        String relatorio = central.gerarRelatorio();

        assertTrue(relatorio.contains("Total a receber"));
    }

    @Test
    void deveContarQuantidadeDeCobrancasRegistradas() {
        Pedido pedido = new Pedido(cliente);
        AssinaturaClube assinatura = new AssinaturaClube(cliente, 19.90, 12);

        central.registrar(pedido);
        central.registrar(assinatura);

        assertEquals(2, central.getQuantidadeCobrancas());
    }

    @Test
    void deveGerarRelatorioVazioParaCentralSemCobrancas() {
        String relatorio = central.gerarRelatorio();

        assertEquals("Total a receber: R$ 0,00", relatorio);
    }
}

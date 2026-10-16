package rangoja;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Cobravel {
    public static final double TAXA_ENTREGA = 7.0;
    //Valor maximo que o pedido pode ter para que a taxa de entrega seja cobrada. Acima disso, a entrega é gratuita.
    public static final double VALOR_FRETE_GRATIS = 100.0;
    private static int proximoNumero = 1;

    private int numero;
    private boolean finalizado;
    private Cliente cliente;
    private List<ItemCardapio> itens;

    public Pedido(Cliente cliente) {
        // static: o número do pedido usa o contador compartilhado por todos os pedidos.
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente obrigatório para abrir um pedido.");
        }
        this.numero = proximoNumero++;
        this.finalizado = false;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void adicionarItem(ItemCardapio item) {
        //  valida bebida alcoólica somente quando a referência real for Bebida.
        if (item == null) {
            throw new IllegalArgumentException("Item obrigatório para adicionar ao pedido.");
        }
        if (finalizado) {
            throw new IllegalStateException("Pedido já finalizado. Não é possível adicionar itens.");
        }
        if (item instanceof Bebida) {
            Bebida bebida = (Bebida) item;
            if (bebida.isAlcoolica() && !cliente.isMaiorDeIdade()) {
                throw new IllegalArgumentException("Cliente menor de idade não pode comprar bebida alcoólica.");
            }
        }
        itens.add(item);
    }

    public void adicionarItem(ItemCardapio item, int quantidade) {
        // sobrecarga: reaproveita a lógica de um único item e evita duplicação de regra.
        if (quantidade < 1) {
            throw new IllegalArgumentException("Quantidade inválida: deve ser maior que zero.");
        }
        for (int i = 0; i < quantidade; i++) {
            adicionarItem(item);
        }
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public double calcularSubtotal() {
        // polimorfismo: cada item calcula seu próprio preço sem instanceof.
        double subtotal = 0.0;
        for (ItemCardapio item : itens) {
            subtotal += item.calcularPreco();
        }
        return subtotal;
    }

    public double calcularTaxaEntrega() {
        if (calcularSubtotal() >= VALOR_FRETE_GRATIS) {
            return 0.0;
        }
        return TAXA_ENTREGA;
    }

    @Override
    public double calcularValorCobrar() {
        return calcularSubtotal() + calcularTaxaEntrega();
    }

    public void finalizar() {
        if (itens.isEmpty() || finalizado) {
            throw new IllegalStateException("Pedido inválido para finalização.");
        }
        double subtotal = calcularSubtotal();
        cliente.creditarCashback(subtotal * 0.05);
        finalizado = true;
    }

    public static int getTotalPedidosCriados() {
        return proximoNumero - 1;
    }
}

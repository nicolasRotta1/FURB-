package rangoja;

public class AssinaturaClube implements Cobravel {
    private double valorMensal;
    private int meses;
    private Cliente cliente;

    public AssinaturaClube(Cliente cliente, double valorMensal, int meses) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente obrigatório para assinar o clube.");
        }
        if (valorMensal <= 0) {
            throw new IllegalArgumentException("Valor mensal inválido: deve ser maior que zero.");
        }
        if (meses < 1) {
            throw new IllegalArgumentException("Quantidade de meses inválida: deve ser maior ou igual a 1.");
        }
        this.cliente = cliente;
        this.valorMensal = valorMensal;
        this.meses = meses;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public int getMeses() {
        return meses;
    }

    @Override
    public double calcularValorCobrar() {
        double valor = valorMensal * meses;
        if (meses >= 12) {
            valor *= 0.85;
        }
        return valor;
    }
}

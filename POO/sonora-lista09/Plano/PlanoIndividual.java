package Plano;

public class PlanoIndividual extends PlanoPago {
    public static final double PRECO_MENSAL = 24.90;

    public PlanoIndividual() {
        super("Individual", 1, PRECO_MENSAL);
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal();
    }
}

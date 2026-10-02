package Plano;

public class PlanoFamilia extends PlanoPago {

    public static final double PRECO_MENSAL = 39.90;
    private int quantidadeMembros;



    public PlanoFamilia( int quantidadeMembros) {
        super("Familia", 6, PRECO_MENSAL);
        setQuantidadeMembros(quantidadeMembros);
    }

    public int getQuantidadeMembros() {
        return quantidadeMembros;
    }

    public void setQuantidadeMembros(int quantidadeMembros) {
        if (quantidadeMembros < 1 || quantidadeMembros > 6) {
            throw new IllegalArgumentException("Membros deve ser de 1 a 6");
        }
        this.quantidadeMembros = quantidadeMembros;
    }

    @Override
    public double calcularMensalidade() {
        return getPrecoMensal() + 4.90 * (quantidadeMembros - 1);
    }
}

package rangoja;

public class Sobremesa extends ItemCardapio {
    private int pesoGramas;

    public Sobremesa(String nome, double precoPor100g, int pesoGramas) {
        // super(...) recebe o preço por 100 g como preço base da classe pai.
        super(nome, precoPor100g);
        this.pesoGramas = validarPeso(pesoGramas);
    }

    public int getPesoGramas() {
        return pesoGramas;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * pesoGramas / 100.0;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + pesoGramas + " g)";
    }

    private int validarPeso(int pesoGramas) {
        validarPositivo(pesoGramas);
        return pesoGramas;
    }
}

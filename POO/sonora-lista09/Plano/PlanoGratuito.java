package Plano;

public final class PlanoGratuito extends Plano {
    public PlanoGratuito() {
        this("Gratuito", 1);
    }

    public PlanoGratuito(String nome, int maxDispositivos) {
        super(nome, maxDispositivos);
    }

    @Override
    public boolean temAnuncios() {
        return true;
    }

    @Override
    public double calcularMensalidade() {
        return 0.0;
    }
}

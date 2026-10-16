package rangoja;

public class Prato extends ItemCardapio {
    private char tamanho;

    public Prato(String nome, double precoBase, char tamanho) {
        super(nome, precoBase);
        setTamanho(tamanho);
    }

    public char getTamanho() {
        return tamanho;
    }

    private void setTamanho(char tamanho) {
        if (tamanho != 'P' && tamanho != 'M' && tamanho != 'G') {
            throw new IllegalArgumentException("Tamanho inválido: use P, M ou G.");
        }
        this.tamanho = tamanho;
    }

    @Override
    public double calcularPreco() {
        double multiplicador;
        if (tamanho == 'P') {
            multiplicador = 1.0;
        } else if (tamanho == 'M') {
            multiplicador = 1.3;
        } else {
            multiplicador = 1.6;
        }
        return getPrecoBase() * multiplicador;
    }

    @Override
    public String toString() {
        return super.toString() + " (tamanho " + tamanho + ")";
    }
}

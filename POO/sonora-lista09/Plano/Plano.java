package Plano;
public abstract class Plano {
    private String nome;
    private int maxDispositivos;

    public Plano(String nome, int maxDispositivos) {
        this.nome = nome;
        this.maxDispositivos = maxDispositivos;
    }

    public String getNome() {
        return nome;
    }

    public int getMaxDispositivos() {
        return maxDispositivos;
    }

    public final String resumo() {
        return getNome() + ": R$ " + calcularMensalidade()
                + " por mes, " + getMaxDispositivos() + " dispositivo(s)";
    }

    public abstract boolean temAnuncios();
    public abstract double calcularMensalidade();
}


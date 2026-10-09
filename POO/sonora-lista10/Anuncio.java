public class Anuncio implements Faturavel, Reproduzivel {
    private String anunciante;
    private String titulo;
    private int duracaoSegundos;
    private double valorPorMilImpressoes;
    private int impressoes;

    public Anuncio(String anunciante, String titulo, int duracaoSegundos, double valorPorMilImpressoes) {
        setAnunciante(anunciante);
        setTitulo(titulo);
        setDuracaoSegundos(duracaoSegundos);
        setValorPorMilImpressoes(valorPorMilImpressoes);
        this.impressoes = 0;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        if (anunciante == null || anunciante.trim().isEmpty()) {
            throw new IllegalArgumentException("Anunciante inválido: o anunciante não pode ser nulo ou vazio.");
        }
        this.anunciante = anunciante.trim();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido: o título não pode ser nulo ou vazio.");
        }
        this.titulo = titulo.trim();
    }

    @Override
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida: a duração deve ser maior que zero.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public double getValorPorMilImpressoes() {
        return valorPorMilImpressoes;
    }

    public void setValorPorMilImpressoes(double valorPorMilImpressoes) {
        if (valorPorMilImpressoes <= 0) {
            throw new IllegalArgumentException("Valor por mil impressões inválido: o valor deve ser maior que zero.");
        }
        this.valorPorMilImpressoes = valorPorMilImpressoes;
    }

    public int getImpressoes() {
        return impressoes;
    }

    @Override
    public void reproduzir() {
        impressoes++;
        System.out.println("[Anuncio] " + titulo + " - oferecimento de " + anunciante);
    }

    public double calcularValorVeiculacao() {
        return impressoes / 1000.0 * valorPorMilImpressoes;
    }

    @Override
    public double calcularMensalidade() {
        return calcularValorVeiculacao();
    }

    @Override
    public String toString() {
        return "[Anuncio] " + titulo + " (" + duracaoSegundos + "s) - " + anunciante;
    }
}

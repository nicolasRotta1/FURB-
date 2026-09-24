
public class Podcast extends Conteudo {
    private String apresentador;
    private int numeroEpisodio;

    public Podcast(String titulo, String apresentador, int duracaoSegundos, int numeroEpisodio) {
        super(titulo, duracaoSegundos);
        setApresentador(apresentador);
        setNumeroEpisodio(numeroEpisodio);
    }

    public String getApresentador() {
        return apresentador;
    }

    public void setApresentador(String apresentador) {
        if (apresentador == null || apresentador.trim().isEmpty()) {
            throw new IllegalArgumentException("Apresentador inválido: o apresentador não pode ser nulo ou vazio.");
        }
        this.apresentador = apresentador.trim();
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        if (numeroEpisodio < 1) {
            throw new IllegalArgumentException("Número do episódio inválido: o episódio deve ser maior ou igual a 1.");
        }
        this.numeroEpisodio = numeroEpisodio;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + apresentador + " (Episódio " + numeroEpisodio + ")";
    }
}

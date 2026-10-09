
public abstract class Conteudo implements Reproduzivel {
    private static int contador = 0;
    private int id;
    private String titulo;
    private int duracaoSegundos;
    private int reproducoes;

    public Conteudo(String titulo, int duracaoSegundos) {
        this.id = ++contador;
        setTitulo(titulo);
        setDuracaoSegundos(duracaoSegundos);
        this.reproducoes = 0;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
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

    @Override 
    public String getDuracaoFormatada() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida: a duração deve ser maior que zero.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public final int getReproducoes() {
        return reproducoes;
    }

    public abstract String getCreditos();

    @Override
    public final void reproduzir() {
        reproducoes++;
        System.out.println("Reproduzindo: " + titulo + " - " + getCreditos());
    }

    @Override
    public String toString() {
        return "[" + getId() + "] " + titulo + " (" + duracaoSegundos + "s)";
    }
}


public class Conteudo {
    private static int contador = 0;
    private int id;
    private String titulo;
    private int duracaoSegundos;

    public Conteudo(String titulo, int duracaoSegundos) {
        this.id = ++contador;
        setTitulo(titulo);
        setDuracaoSegundos(duracaoSegundos);
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

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida: a duração deve ser maior que zero.");
        }
        this.duracaoSegundos = duracaoSegundos;
    }

    public void reproduzir() {
        System.out.println("Reproduzindo: " + toString());
    }

    @Override
    public String toString() {
        return "[" + getId() + "] " + titulo + " (" + duracaoSegundos + "s)";
    }
}

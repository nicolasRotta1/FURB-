public class Musica {
    private static int proximoId = 1;
    private int id;
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private int reproducoes; // Quantas vezes a musica foi tocada;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        validarTitulo(titulo);
        validarArtista(artista);
        validarDuracao(duracaoSegundos);

        this.id = proximoId++;
        this.titulo = titulo.trim();
        this.artista = artista.trim();
        this.duracaoSegundos = duracaoSegundos;
        this.reproducoes = 0;
    }


    public void reproduzir() {
        this.reproducoes++;
    }


    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    
    public void validarTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido: o título da música não pode ser nulo, vazio ou composto apenas por espaços.");
        }
    }

    public void validarArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido: o nome do artista não pode ser nulo, vazio ou composto apenas por espaços.");
        }
    }

    public void setTitulo(String titulo) {
        validarTitulo(titulo);
        this.titulo = titulo;
    }


    public void setArtista(String artista) {
        validarArtista(artista);
        this.artista = artista;
    }

    public void validarDuracao(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida: " + duracaoSegundos + ". A duração deve ser maior que zero.");
        }
    }
    public void setDuracaoSegundos(int duracaoSegundos) {
        validarDuracao(duracaoSegundos);
        this.duracaoSegundos = duracaoSegundos;
    }


    public String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public String getDuracaoFormatada() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public int getReproducoes() {
        return reproducoes;
    }

    
}

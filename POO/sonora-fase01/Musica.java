public class Musica {
    private static int proximoId = 1;
    private int id;
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private  int reproducoes; // Quantas vezes a musica foi tocada;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.artista = artista;
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

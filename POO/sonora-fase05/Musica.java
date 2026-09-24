
public class Musica extends Conteudo {
    private String artista;
    private String album;
    private int reproducoes; // Quantas vezes a musica foi tocada;

    public Musica(String titulo, String artista, int duracaoSegundos) {
        super(titulo, duracaoSegundos);
        setArtista(artista);
        setAlbum("Sem álbum");
        this.reproducoes = 0;
    }

    public Musica(String titulo, int duracaoSegundos, String artista, String album) {
        super(titulo, duracaoSegundos);
        setArtista(artista);
        setAlbum(album);
        this.reproducoes = 0;
    }

    @Override
    public void reproduzir() {
        this.reproducoes++;
        super.reproduzir();
    }

    public void validarTitulo(String titulo) {
        super.setTitulo(titulo);
    }

    //Valida null e blank
    public void validarArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido: o nome do artista não pode ser nulo, vazio ou composto apenas por espaços.");
        }
    }

    public void validarDuracao(int duracaoSegundos) {
        if (duracaoSegundos <= 0) {
            throw new IllegalArgumentException("Duração inválida: " + duracaoSegundos + ". A duração deve ser maior que zero.");
        }
    }

    @Override
    public void setTitulo(String titulo) {
        super.setTitulo(titulo);
    }

    public void setArtista(String artista) {
        validarArtista(artista);
        this.artista = artista.trim();
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        if (album == null || album.trim().isEmpty()) {
            throw new IllegalArgumentException("Álbum inválido: o álbum não pode ser nulo ou vazio.");
        }
        this.album = album.trim();
    }

    public String getDuracaoFormatada() {
        int minutos = getDuracaoSegundos() / 60;
        int segundos = getDuracaoSegundos() % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    public int getReproducoes() {
        return reproducoes;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + artista + " (" + album + ")";
    }
}

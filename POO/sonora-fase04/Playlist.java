import java.util.ArrayList;

public class Playlist {
	private String nome;
	private Usuario dono;
	private ArrayList<Musica> musicas;
	private Plataforma plataforma;

	public Playlist(String nome, Usuario dono) {
		this(nome, dono, null);
	}

	public Playlist(String nome, Usuario dono, Plataforma plataforma) {
		validaNome(nome);
		validarDono(dono);
		this.nome = nome.trim();
		this.dono = dono;
		this.plataforma = plataforma;
		this.musicas = new ArrayList<>();
	}

	public boolean adicionar(Musica musica) {
		if (musica == null) {
			throw new IllegalArgumentException("Música inválida: não é possível adicionar uma música nula na playlist.");
		}

		if (plataforma != null) {
			try {
				if (plataforma.getMusica(musica.getId()) != musica) {
					return false;
				}
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		}

		for (Musica existente : musicas) {
			if (existente.getId() == musica.getId()) {
				return false;
			}
		}

		musicas.add(musica);
		return true;
	}

	public Musica getNaPosicao(int indice) {
		if (indice < 0 || indice >= musicas.size()) {
			throw new IndexOutOfBoundsException("Índice inválido: " + indice + ". A playlist possui " + musicas.size() + " música(s).");
		}

		return musicas.get(indice);
	}

	public Musica getTitulo(String titulo) {
		if (titulo == null) {
			return null;
		}

		for (Musica musica : musicas) {
			if (titulo.equalsIgnoreCase(musica.getTitulo())) {
				return musica;
			}
		}

		return null;
	}

	public boolean atualizarPlaylist(int indice, String titulo, String artista, int duracaoSegundos) {
		Musica musica = getNaPosicao(indice);

		// Valida tudo antes de alterar a música atual.
		musica.validarTitulo(titulo);
		musica.validarArtista(artista);
		musica.validarDuracao(duracaoSegundos);

		musica.setTitulo(titulo.trim());
		musica.setArtista(artista.trim());
		musica.setDuracaoSegundos(duracaoSegundos);
		return true;
	}

	public boolean removerNaPosicao(int indice) {
		getNaPosicao(indice);
		musicas.remove(indice);
		return true;
	}

	public int getDuracaoTotalSegundos() {
		int duracaoTotal = 0;
		for (Musica musica : musicas) {
			duracaoTotal += musica.getDuracaoSegundos();
		}

		return duracaoTotal;
	}

	public void reproduzirTudo() {
		for (Musica musica : musicas) {
			musica.reproduzir();
		}
	}

	public String getNome() {
		return nome;
	}

	// Valida null e blank
	public void validaNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido: o nome da playlist não pode ser nulo, vazio ou composto apenas por espaços.");
		}
	}

	public void setNome(String nome) {
		validaNome(nome);
		this.nome = nome;
	}

	// Valida null 
	public void validarDono(Usuario dono) {
		if (dono == null) {
			throw new IllegalArgumentException("Dono inválido: a playlist precisa ter um dono válido.");
		}
	}

	public void setDono(Usuario dono) {
		validarDono(dono);
		this.dono = dono;
	}

	public Usuario getDono() {
		return dono;
	}

	public int getQuantidade() {
		return musicas.size();
	}
}

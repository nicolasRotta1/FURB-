public class Plataforma {
	private static final int CAPACIDADE = 500;
	private Musica[] musicas;
	private Usuario[] usuarios;
	private Playlist[] playlists;
	private int totalMusicas;
	private int totalUsuarios;
	private int totalPlaylists;

	public Plataforma() {
		this.musicas = new Musica[CAPACIDADE];
		this.usuarios = new Usuario[CAPACIDADE];
		this.playlists = new Playlist[CAPACIDADE];
		this.totalMusicas = 0;
		this.totalUsuarios = 0;
		this.totalPlaylists = 0;
	}
	
	// Cadastrar musica na plataforma, retorna false se a musica for nula ou se a plataforma estiver cheia
	public boolean cadastrarMusica(Musica musica) {
		if (musica == null) {
			throw new IllegalArgumentException("Música inválida: a música não pode ser nula.");
		}
		if (totalMusicas == CAPACIDADE) {
			return false;
		}

		musicas[totalMusicas] = musica;
		totalMusicas++;
		return true;
	}

	public Musica getMusica(int id) {
		for (int indice = 0; indice < totalMusicas; indice++) {
			if (musicas[indice].getId() == id) {
				return musicas[indice];
			}
		}

		throw new IndexOutOfBoundsException("Música não encontrada: " + id);
	}

	public Musica getMusicaPorTitulo(String titulo) {
		if (titulo == null) {
			return null;
		}

		for (int indice = 0; indice < totalMusicas; indice++) {
			if (titulo.equalsIgnoreCase(musicas[indice].getTitulo())) {
				return musicas[indice];
			}
		}

		return null;
	}

	public boolean atualizarMusica(int id, String titulo, String artista, int duracaoSegundos) {
		Musica musica = getMusica(id);
		musica.validarTitulo(titulo);
		musica.validarArtista(artista);
		musica.validarDuracao(duracaoSegundos);
		musica.setTitulo(titulo.trim());
		musica.setArtista(artista.trim());
		musica.setDuracaoSegundos(duracaoSegundos);
		return true;
	}

	public boolean cadastrarPlaylist(Playlist playlist) {
		if (playlist == null) {
			throw new IllegalArgumentException("Playlist inválida: a playlist não pode ser nula.");
		}
		if (totalPlaylists == CAPACIDADE) {
			return false;
		}

		playlists[totalPlaylists] = playlist;
		totalPlaylists++;
		return true;
	}

	public Playlist[] getPlaylists() {
		Playlist[] copia = new Playlist[totalPlaylists];
		for (int indice = 0; indice < totalPlaylists; indice++) {
			copia[indice] = playlists[indice];
		}
		return copia;
	}

	public boolean removerMusica(int id) {
		Musica musicaParaRemover = null;
		for (int indice = 0; indice < totalMusicas; indice++) {
			if (musicas[indice].getId() == id) {
				musicaParaRemover = musicas[indice];
				break;
			}
		}

		if (musicaParaRemover == null) {
			throw new IndexOutOfBoundsException("Música não encontrada: " + id);
		}

		for (int indicePlaylist = 0; indicePlaylist < totalPlaylists; indicePlaylist++) {
			Playlist playlist = playlists[indicePlaylist];
			if (playlist == null) {
				continue;
			}

			for (int indiceMusica = 0; indiceMusica < playlist.getQuantidade(); indiceMusica++) {
				if (playlist.getNaPosicao(indiceMusica).getId() == id) {
					playlist.removerNaPosicao(indiceMusica);
					indiceMusica--;
				}
			}
		}

		for (int indice = 0; indice < totalMusicas; indice++) {
			if (musicas[indice].getId() == id) {
				for (int posicao = indice; posicao < totalMusicas - 1; posicao++) {
					musicas[posicao] = musicas[posicao + 1];
				}

				totalMusicas--;
				musicas[totalMusicas] = null;
				return true;
			}
		}

		return false;
	}

	// Cadastrar usuário na plataforma, retorna false se o usuário for nulo ou se a plataforma estiver cheia
	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Usuário inválido: o usuário não pode ser nulo.");
		}
		if (totalUsuarios == CAPACIDADE) {
			return false;
		}

		usuarios[totalUsuarios] = usuario;
		totalUsuarios++;
		return true;
	}

	public Usuario getUsuario(int id) {
		for (int indice = 0; indice < totalUsuarios; indice++) {
			if (usuarios[indice].getId() == id) {
				return usuarios[indice];
			}
		}

		throw new IndexOutOfBoundsException("Usuário não encontrado: " + id);
	}

	public boolean atualizarUsuario(int id, String nome, String email) {
		Usuario usuario = getUsuario(id);

		usuario.validarNome(nome);
		usuario.validarEmail(email);
		usuario.setNome(nome.trim());
		usuario.setEmail(email.trim());
		return true;
	}

	public boolean removerUsuario(int id) {
		for (int indice = 0; indice < totalUsuarios; indice++) {
			if (usuarios[indice].getId() == id) {
				for (int posicao = indice; posicao < totalUsuarios - 1; posicao++) {
					usuarios[posicao] = usuarios[posicao + 1];
				}

				totalUsuarios--;
				usuarios[totalUsuarios] = null;
				return true;
			}
		}

		throw new IndexOutOfBoundsException("Usuário não encontrado: " + id);
	}

	public int getTotalMusicas() {
		return totalMusicas;
	}

	public Musica[] getMusicas() {
		Musica[] copia = new Musica[totalMusicas];
		for (int indice = 0; indice < totalMusicas; indice++) {
			copia[indice] = musicas[indice];
		}
		return copia;
	}

	public int getTotalUsuarios() {
		return totalUsuarios;
	}

}

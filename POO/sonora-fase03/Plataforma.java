public class Plataforma {
	private static final int CAPACIDADE = 500;
	private Musica[] musicas;
	private Usuario[] usuarios;
	private int totalMusicas;
	private int totalUsuarios;

	public Plataforma() {
		this.musicas = new Musica[CAPACIDADE];
		this.usuarios = new Usuario[CAPACIDADE];
		this.totalMusicas = 0;
		this.totalUsuarios = 0;
	}
	
	// Cadastrar musica na plataforma, retorna false se a musica for nula ou se a plataforma estiver cheia
	public boolean cadastrarMusica(Musica musica) {
		if (musica == null || totalMusicas == CAPACIDADE) {
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

	public boolean removerMusica(int id) {
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

		throw new IndexOutOfBoundsException("Música não encontrada: " + id);
	}

	// Cadastrar usuário na plataforma, retorna false se o usuário for nulo ou se a plataforma estiver cheia
	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null || totalUsuarios == CAPACIDADE) {
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

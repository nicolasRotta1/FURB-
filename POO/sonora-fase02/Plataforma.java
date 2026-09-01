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

	// Cadastrar usuário na plataforma, retorna false se o usuário for nulo ou se a plataforma estiver cheia
	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null || totalUsuarios == CAPACIDADE) {
			return false;
		}

		usuarios[totalUsuarios] = usuario;
		totalUsuarios++;
		return true;
	}

	// Buscar música por ID, retorna null se não encontrar(usa sobrecarga de métodos)
	public Musica buscarMusicaPorId(int id) {
		for (int indice = 0; indice < totalMusicas; indice++) {
			if (musicas[indice].getId() == id) {
				return musicas[indice];
			}
		}

		return null;
	}

	// Buscar música por título, retorna null se não encontrar(usa sobrecarga de métodos)
	public Musica buscarMusica(String titulo) {
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

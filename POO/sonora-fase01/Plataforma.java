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

	public boolean cadastrarMusica(Musica musica) {
		if (musica == null || totalMusicas == CAPACIDADE) {
			return false;
		}

		musicas[totalMusicas] = musica;
		totalMusicas++;
		return true;
	}

	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null || totalUsuarios == CAPACIDADE) {
			return false;
		}

		usuarios[totalUsuarios] = usuario;
		totalUsuarios++;
		return true;
	}

	public Musica buscarMusicaPorId(int id) {
		for (int indice = 0; indice < totalMusicas; indice++) {
			if (musicas[indice].getId() == id) {
				return musicas[indice];
			}
		}

		return null;
	}

	public Musica buscarMusica(String titulo) {
		if (titulo == null) {
			return null;
		}

		for (int indice = 0; indice < totalMusicas; indice++) {
			if (titulo.equals(musicas[indice].getTitulo())) {
				return musicas[indice];
			}
		}

		return null;
	}

	public int getTotalMusicas() {
		return totalMusicas;
	}

	public int getTotalUsuarios() {
		return totalUsuarios;
	}

}

import java.util.ArrayList;

public class Plataforma {
	private ArrayList<Musica> musicas;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Playlist> playlists;

	public Plataforma() {
		this.musicas = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}

	public boolean cadastrarMusica(Musica musica) {
		if (musica == null) {
			throw new IllegalArgumentException("Música inválida: a música não pode ser nula.");
		}
		if (musicas.contains(musica)) {
			return false;
		}

		musicas.add(musica);
		return true;
	}

	public Musica getMusica(int id) {
		for (Musica musica : musicas) {
			if (musica.getId() == id) {
				return musica;
			}
		}

		throw new IndexOutOfBoundsException("Música não encontrada: " + id);
	}

	public Musica getMusicaPorTitulo(String titulo) {
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
		if (playlists.contains(playlist)) {
			return false;
		}

		playlists.add(playlist);
		return true;
	}

	public Playlist[] getPlaylists() {
		return playlists.toArray(new Playlist[0]);
	}

	public boolean removerMusica(int id) {
		Musica musicaParaRemover = null;
		for (Musica musica : musicas) {
			if (musica.getId() == id) {
				musicaParaRemover = musica;
				break;
			}
		}

		if (musicaParaRemover == null) {
			throw new IndexOutOfBoundsException("Música não encontrada: " + id);
		}

		for (Playlist playlist : playlists) {
			for (int indiceMusica = 0; indiceMusica < playlist.getQuantidade(); indiceMusica++) {
				if (playlist.getNaPosicao(indiceMusica).getId() == id) {
					playlist.removerNaPosicao(indiceMusica);
					indiceMusica--;
				}
			}
		}

		musicas.remove(musicaParaRemover);
		return true;
	}

	public boolean cadastrarUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Usuário inválido: o usuário não pode ser nulo.");
		}
		if (usuarios.contains(usuario)) {
			return false;
		}

		usuarios.add(usuario);
		return true;
	}

	public Usuario getUsuario(int id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				return usuario;
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
		Usuario usuarioParaRemover = null;
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				usuarioParaRemover = usuario;
				break;
			}
		}

		if (usuarioParaRemover == null) {
			throw new IndexOutOfBoundsException("Usuário não encontrado: " + id);
		}

		usuarios.remove(usuarioParaRemover);
		return true;
	}

	public int getTotalMusicas() {
		return musicas.size();
	}

	public Musica[] getMusicas() {
		return musicas.toArray(new Musica[0]);
	}

	public Usuario[] getUsuarios() {
		return usuarios.toArray(new Usuario[0]);
	}

	public int getTotalUsuarios() {
		return usuarios.size();
	}
}

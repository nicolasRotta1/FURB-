import java.util.ArrayList;

public class Plataforma {
	private ArrayList<Conteudo> acervo;
	private ArrayList<Anuncio> anuncios;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Playlist> playlists;

	public Plataforma() {
		this.acervo = new ArrayList<>();
		this.anuncios = new ArrayList<>();
		this.usuarios = new ArrayList<>();
		this.playlists = new ArrayList<>();
	}


	public boolean cadastrarConteudo(Conteudo conteudo) {
		if (conteudo == null) {
			throw new IllegalArgumentException("Conteúdo inválido: o conteúdo não pode ser nulo.");
		}
		if (acervo.contains(conteudo)) {
			return false;
		}

		acervo.add(conteudo);
		return true;
	}

	// Manter para compatibilidade com código anterior
	public boolean cadastrarMusica(Musica musica) {
		return cadastrarConteudo(musica);
	}

	public Conteudo getConteudo(int id) {
		for (Conteudo conteudo : acervo) {
			if (conteudo.getId() == id) {
				return conteudo;
			}
		}

		throw new IndexOutOfBoundsException("Conteúdo não encontrado: " + id);
	}

	// Manter para compatibilidade com código anterior
	public Musica getMusica(int id) {
		try {
			Conteudo conteudo = getConteudo(id);
			if (conteudo instanceof Musica) {
				return (Musica) conteudo;
			}
		} catch (IndexOutOfBoundsException e) {
			// Conteúdo não encontrado ou não é música
		}

		throw new IndexOutOfBoundsException("Música não encontrada: " + id);
	}

	public Conteudo getConteudoPorTitulo(String titulo) {
		if (titulo == null) {
			return null;
		}

		for (Conteudo conteudo : acervo) {
			if (titulo.equalsIgnoreCase(conteudo.getTitulo())) {
				return conteudo;
			}
		}

		return null;
	}

	// Manter para compatibilidade com código anterior
	public Musica getMusicaPorTitulo(String titulo) {
		if (titulo == null) {
			return null;
		}

		Conteudo conteudo = getConteudoPorTitulo(titulo);
		if (conteudo instanceof Musica) {
			return (Musica) conteudo;
		}

		return null;
	}

	public boolean atualizarMusica(int id, String titulo, String artista, int duracaoSegundos) {
		Conteudo conteudo = getConteudo(id);
		if (!(conteudo instanceof Musica)) {
			return false;
		}

		Musica musica = (Musica) conteudo;
		musica.validarTitulo(titulo);
		musica.validarArtista(artista);
		musica.validarDuracao(duracaoSegundos);
		musica.setTitulo(titulo.trim());
		musica.setArtista(artista.trim());
		musica.setDuracaoSegundos(duracaoSegundos);
		return true;
	}

	// Métodos para gerenciar anúncios
	public boolean cadastrarAnuncio(Anuncio anuncio) {
		if (anuncio == null) {
			throw new IllegalArgumentException("Anúncio inválido: o anúncio não pode ser nulo.");
		}
		if (anuncios.contains(anuncio)) {
			return false;
		}

		anuncios.add(anuncio);
		return true;
	}

	public Anuncio[] getAnuncios() {
		return anuncios.toArray(new Anuncio[0]);
	}

	public int getTotalAnuncios() {
		return anuncios.size();
	}

	// Métodos para gerenciar playlists
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

	public boolean removerConteudo(int id) {
		Conteudo conteudoParaRemover = null;
		for (Conteudo conteudo : acervo) {
			if (conteudo.getId() == id) {
				conteudoParaRemover = conteudo;
				break;
			}
		}

		if (conteudoParaRemover == null) {
			throw new IndexOutOfBoundsException("Conteúdo não encontrado: " + id);
		}

		for (Playlist playlist : playlists) {
			for (int indiceConteudo = 0; indiceConteudo < playlist.getQuantidade(); indiceConteudo++) {
				if (playlist.getNaPosicao(indiceConteudo).getId() == id) {
					playlist.removerNaPosicao(indiceConteudo);
					indiceConteudo--;
				}
			}
		}

		acervo.remove(conteudoParaRemover);
		return true;
	}

	// Manter para compatibilidade com código anterior
	public boolean removerMusica(int id) {
		return removerConteudo(id);
	}

	// Métodos para gerenciar usuários
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

	// Método para calcular receita mensal (Passo 2 e 7)
	public double calcularReceitaMensal() {
		ArrayList<Faturavel> faturaveisList = new ArrayList<>();
		
		// Adicionar os planos de todos os usuários
		for (Usuario usuario : usuarios) {
			faturaveisList.add(usuario.getPlano());
		}
		
		// Adicionar todos os anúncios
		for (Anuncio anuncio : anuncios) {
			faturaveisList.add(anuncio);
		}
		
		// Somar tudo
		double total = 0;
		for (Faturavel faturavel : faturaveisList) {
			total += faturavel.calcularMensalidade();
		}
		
		return total;
	}

	// Método para listar episódios de um apresentador (Passo 3)
	public void listarEpisodiosDe(String apresentador) {
		if (apresentador == null || apresentador.trim().isEmpty()) {
			System.out.println("Apresentador inválido.");
			return;
		}

		boolean encontrou = false;
		for (Conteudo conteudo : acervo) {
			if (conteudo instanceof Podcast) {
				Podcast podcast = (Podcast) conteudo;
				if (apresentador.equalsIgnoreCase(podcast.getApresentador())) {
					System.out.println("- " + podcast.getTitulo() + " (Episódio " + podcast.getNumeroEpisodio() + ", " + podcast.getDuracaoSegundos() + "s)");
					encontrou = true;
				}
			}
		}

		if (!encontrou) {
			System.out.println("Nenhum episódio encontrado para o apresentador: " + apresentador);
		}
	}

	// Método para tocar uma playlist para um usuário (Passo 8)
	public void tocarPlaylist(Usuario ouvinte, Playlist playlist) {
		if (ouvinte == null || playlist == null) {
			throw new IllegalArgumentException("Usuário ou playlist inválido.");
		}

		ArrayList<Reproduzivel> fila = new ArrayList<>();
		
		// Montar a fila com os itens da playlist
		for (int i = 0; i < playlist.getQuantidade(); i++) {
			fila.add((Reproduzivel) playlist.getNaPosicao(i));
		}

		// Se o plano tem anúncios e há anúncios cadastrados, intercalar anúncios
		int indiceAnuncio = 0;
		if (ouvinte.getPlano().temAnuncios() && anuncios.size() > 0) {
			ArrayList<Reproduzivel> filaComAnuncios = new ArrayList<>();
			int itemsAdicionados = 0;
			
			for (Reproduzivel item : fila) {
				filaComAnuncios.add(item);
				itemsAdicionados++;
				
				// A cada 2 itens, adicionar um anúncio
				if (itemsAdicionados % 2 == 0 && indiceAnuncio < anuncios.size()) {
					filaComAnuncios.add((Reproduzivel) anuncios.get(indiceAnuncio));
					indiceAnuncio = (indiceAnuncio + 1) % anuncios.size();
				}
			}
			
			fila = filaComAnuncios;
		}

		// Reproduzir tudo
		for (Reproduzivel item : fila) {
			System.out.println("[" + item.getDuracaoFormatada() + "]");
			item.reproduzir();
		}
	}

	public int getTotalMusicas() {
		return acervo.size();
	}

	public Musica[] getMusicas() {
		ArrayList<Musica> musicasList = new ArrayList<>();
		for (Conteudo conteudo : acervo) {
			if (conteudo instanceof Musica) {
				musicasList.add((Musica) conteudo);
			}
		}
		return musicasList.toArray(new Musica[0]);
	}

	public Usuario[] getUsuarios() {
		return usuarios.toArray(new Usuario[0]);
	}

	public int getTotalUsuarios() {
		return usuarios.size();
	}
}

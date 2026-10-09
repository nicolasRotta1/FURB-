import java.util.ArrayList;

public class Playlist {
	private String nome;
	private Usuario dono;
	private ArrayList<Conteudo> conteudos;
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
		this.conteudos = new ArrayList<>();
	}

	public boolean adicionar(Conteudo conteudo) {
		if (conteudo == null) {
			throw new IllegalArgumentException("Conteúdo inválido: não é possível adicionar um conteúdo nulo na playlist.");
		}

		if (plataforma != null) {
			try {
				if (plataforma.getConteudo(conteudo.getId()) != conteudo) {
					return false;
				}
			} catch (IndexOutOfBoundsException e) {
				return false;
			}
		}

		for (Conteudo existente : conteudos) {
			if (existente.getId() == conteudo.getId()) {
				return false;
			}
		}

		conteudos.add(conteudo);
		return true;
	}

	public Conteudo getNaPosicao(int indice) {
		if (indice < 0 || indice >= conteudos.size()) {
			throw new IndexOutOfBoundsException("Índice inválido: " + indice + ". A playlist possui " + conteudos.size() + " item(s).");
		}

		return conteudos.get(indice);
	}

	public Conteudo getTitulo(String titulo) {
		if (titulo == null) {
			return null;
		}

		for (Conteudo conteudo : conteudos) {
			if (titulo.equalsIgnoreCase(conteudo.getTitulo())) {
				return conteudo;
			}
		}

		return null;
	}

	public boolean atualizarPlaylist(int indice, String titulo, String artista, int duracaoSegundos) {
		Conteudo conteudo = getNaPosicao(indice);

		// Só pode atualizar se for uma Musica
		if (!(conteudo instanceof Musica)) {
			return false;
		}

		Musica musica = (Musica) conteudo;

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
		conteudos.remove(indice);
		return true;
	}

	public int getDuracaoTotalSegundos() {
		int duracaoTotal = 0;
		for (Conteudo conteudo : conteudos) {
			duracaoTotal += conteudo.getDuracaoSegundos();
		}

		return duracaoTotal;
	}

	public void reproduzirTudo() {
		for (Conteudo conteudo : conteudos) {
			conteudo.reproduzir();
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
		return conteudos.size();
	}
}

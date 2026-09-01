public class Playlist {
	private static final int CAPACIDADE = 100;
	private String nome;
	private Usuario dono;
	private Musica[] musicas;
	private int quantidade;

	public Playlist(String nome, Usuario dono) {
		validaNome(nome);
		validarDono(dono);
		this.nome = nome;
		this.dono = dono;
		this.musicas = new Musica[CAPACIDADE];
		this.quantidade = 0;
	}

	// Adiciona uma musica na playlist, retorna false se a musica for nula ou se a playlist estiver cheia
	public boolean adicionar(Musica musica) {
		if (quantidade == CAPACIDADE) {
			return false;
		}
		if (musica == null) {
			throw new IllegalArgumentException("Música inválida: não é possível adicionar uma música nula na playlist.");
		}

		musicas[quantidade] = musica;
		quantidade++;
		return true;
	}

	public Musica getNaPosicao(int indice) {
		if (indice < 0 || indice >= quantidade) {
			throw new IndexOutOfBoundsException("Índice inválido: " + indice + ". A playlist possui " + quantidade + " música(s).");
		}

		return musicas[indice];
	}

	// Remover musica na posição especificada, tambem reorganiza o array de musicas para que não tenha espaços vazios
	public boolean removerNaPosicao(int indice) {
		if (indice < 0 || indice >= quantidade) {
			throw new IndexOutOfBoundsException("Índice inválido: " + indice + ". A playlist possui " + quantidade + " música(s).");
		}

		for (int posicao = indice; posicao < quantidade - 1; posicao++) {
			musicas[posicao] = musicas[posicao + 1];
		}

		quantidade--;
		musicas[quantidade] = null;
		return true;
	}

	// Retorna a duração total da playlist em segundos
	public int getDuracaoTotalSegundos() {
		int duracaoTotal = 0;
		for (int indice = 0; indice < quantidade; indice++) {
			duracaoTotal += musicas[indice].getDuracaoSegundos();
		}

		return duracaoTotal;
	}

	// Reproduz todas as musicas da playlist
	public void reproduzirTudo() {
		for (int indice = 0; indice < quantidade; indice++) {
			musicas[indice].reproduzir();
		}
	}



    public String getNome() {
		return nome;
	}

	public void validaNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome inválido: o nome da playlist não pode ser nulo, vazio ou composto apenas por espaços.");
		}
	}

	public void setNome(String nome) {
		validaNome(nome);
		this.nome = nome;
	}

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
		return quantidade;
	}


}

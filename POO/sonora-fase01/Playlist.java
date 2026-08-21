public class Playlist {
	private static final int CAPACIDADE = 100;
	private String nome;
	private Usuario dono;
	private Musica[] musicas;
	private int quantidade;

	public Playlist(String nome, Usuario dono) {
		this.nome = nome;
		this.dono = dono;
		this.musicas = new Musica[CAPACIDADE];
		this.quantidade = 0;
	}

	// Adiciona uma musica na playlist, retorna false se a musica for nula ou se a playlist estiver cheia
	public boolean adicionar(Musica musica) {
		if (musica == null || quantidade == CAPACIDADE) {
			return false;
		}

		musicas[quantidade] = musica;
		quantidade++;
		return true;
	}

	public Musica getNaPosicao(int indice) {
		if (indice < 0 || indice >= quantidade) {
			return null;
		}

		return musicas[indice];
	}

	// Remover musica na posição especificada, tambem reorganiza o array de musicas para que não tenha espaços vazios
	public boolean removerNaPosicao(int indice) {
		if (indice < 0 || indice >= quantidade) {
			return false;
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

	public Usuario getDono() {
		return dono;
	}

	public int getQuantidade() {
		return quantidade;
	}


}

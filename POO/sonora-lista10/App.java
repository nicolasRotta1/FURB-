import java.util.Scanner;

// Conteudo c = new Conteudo("Generico", 120); // ERRO: Conteudo é abstract e não pode ser instanciada.
// Plano p = new Plano("Generico", 1); // ERRO: Plano é abstract e não pode ser instanciada.
// class PlanoFake extends PlanoGratuito { } // ERRO: PlanoGratuito é final e não pode ser estendida.

public class App {
	private static String lerTextoValido(Scanner sc, String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String texto = sc.nextLine();
			if (texto == null || texto.trim().isEmpty()) {
				System.out.println("Campo obrigatorio. Digite algo valido.");
				continue;
			}
			return texto.trim();
		}
	}

	private static int lerInteiroValido(Scanner sc, String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = sc.nextLine();
			try {
				return Integer.parseInt(entrada.trim());
			} catch (NumberFormatException e) {
				System.out.println("Entrada invalida. Digite um numero inteiro.");
			}
		}
	}

	private static double lerDoubleValido(Scanner sc, String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = sc.nextLine();
			try {
				return Double.parseDouble(entrada.trim());
			} catch (NumberFormatException e) {
				System.out.println("Entrada invalida. Digite um numero decimal.");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Plataforma plataforma = new Plataforma();

		Musica musica1 = new Musica("Trem Bala", 180, "Ana Vilela", "Vozes do Brasil");
		Musica musica2 = new Musica("Aquarela", 210, "Toquinho", "Toquinho");
		Podcast podcast = new Podcast("Nerdologia", "Caio", 1800, 42);

		System.out.println("=== Demonstração da hierarquia ===");
		musica1.reproduzir();
		musica1.reproduzir();
		musica2.reproduzir();
		musica2.reproduzir();
		musica2.reproduzir();
		podcast.reproduzir();
		podcast.reproduzir();
		podcast.reproduzir();
		System.out.println(musica1);
		System.out.println(musica2);
		System.out.println(podcast);
		System.out.println(musica1.getTitulo() + " - reproducoes: " + musica1.getReproducoes());
		System.out.println(musica2.getTitulo() + " - reproducoes: " + musica2.getReproducoes());
		System.out.println(podcast.getTitulo() + " - reproducoes: " + podcast.getReproducoes());

		// Demonstração Passo 3: tipo estático vs dinâmico
		System.out.println("\n=== Demonstração Passo 3: Tipos Estático vs Dinâmico ===");
		Conteudo c = new Podcast("Podcast Teste", "João", 600, 1);
		// Tipo estático: Conteudo
		// Tipo dinâmico: Podcast
		System.out.println("Tipo estático de c: Conteudo");
		System.out.println("Tipo dinâmico de c: Podcast");
		// c.getApresentador(); // ERRO: não compila porque o compilador só libera os membros de Conteudo
		System.out.println("c.getApresentador() não compila porque o tipo estático é Conteudo e esse método só existe em Podcast");
		System.out.println("getDuracaoFormatada() funciona via interface Cronometravel: " + c.getDuracaoFormatada());

		// para testes
		int quantidadeMusicas = popularAcervo(plataforma);
		

		// Demonstração: mostrar receita antes e depois de tocar playlist para usuário gratuito
		System.out.println("\n=== Demonstração: Efeito de Anúncios na Receita ===");
		demonstrarEfeitoAnuncios(plataforma);

		boolean executando = true;
		try {
			while (executando) {
				exibirMenu();
				int opcao = lerInteiroValido(sc, "");
				switch (opcao) {
					case 1:
						quantidadeMusicas = cadastrarMusica(sc, plataforma);
						break;
					case 2:
						cadastrarPodcast(sc, plataforma);
						break;
					case 3:
						cadastrarUsuario(sc, plataforma);
						break;
					case 4:
						criarPlaylist(sc, plataforma);
						break;
					case 5:
						buscarPorId(sc, plataforma);
						break;
					case 6:
						buscarPorTitulo(sc, plataforma);
						break;
					case 7:
						reproduzirConteudo(sc, plataforma);
						break;
					case 8:
						listarAcervo(plataforma.getMusicas(), plataforma.getTotalMusicas());
						break;
					case 9:
						gerenciarPlaylists(sc, plataforma);
						break;
					case 10:
						atualizarMusicaNaPlataforma(sc, plataforma);
						break;
					case 11:
						removerConteudoNaPlataforma(sc, plataforma);
						break;
					case 12:
						seguirUsuario(sc, plataforma);
						break;
					case 13:
						deixarDeSeguirUsuario(sc, plataforma);
						break;
					case 14:
						listarUsuariosSeguidos(sc, plataforma);
						break;
					case 15:
						trocarPlanoDoUsuario(sc, plataforma);
						break;
					case 16:
						exibirPlanoAtualDoUsuario(sc, plataforma);
						break;
					case 17:
						cadastrarAnuncio(sc, plataforma);
						break;
					case 18:
						tocarPlaylistParaUsuario(sc, plataforma);
						break;
					case 19:
						listarEpisodios(sc, plataforma);
						break;
					case 20:
						exibirReceitaMensal(plataforma);
						break;
					case 0:
						executando = false;
						break;
					default:
						System.out.println("Opcao invalida.");
				}
			}
		} finally {
			System.out.println("Sonora encerrada.");
			sc.close();
		}
	}

	// Demonstração do efeito de anúncios na receita
	private static void demonstrarEfeitoAnuncios(Plataforma plataforma) {
		try {
			// Criar usuário com plano gratuito
			Usuario usuarioGratuito = new Usuario("Maria Silva", "maria@example.com");
			plataforma.cadastrarUsuario(usuarioGratuito);
			usuarioGratuito.assinar(new PlanoGratuito());

			// Adicionar conteúdo
			Musica m1 = new Musica("Música 1", "Artista 1", 180);
			Musica m2 = new Musica("Música 2", "Artista 2", 200);
			plataforma.cadastrarMusica(m1);
			plataforma.cadastrarMusica(m2);

			// Criar anúncio
			Anuncio anuncio = new Anuncio("Coca Cola", "Beba Coca", 15, 10.0);
			plataforma.cadastrarAnuncio(anuncio);

			// Criar playlist
			Playlist playlist = new Playlist("Minha Playlist", usuarioGratuito, plataforma);
			playlist.adicionar(m1);
			playlist.adicionar(m2);
			plataforma.cadastrarPlaylist(playlist);

			// Exibir receita antes
			double receitaAntes = plataforma.calcularReceitaMensal();
			System.out.println("Receita mensal ANTES de tocar playlist: R$ " + String.format("%.2f", receitaAntes));
			System.out.println("Impressões do anúncio ANTES: " + anuncio.getImpressoes());

			// Tocar playlist
			System.out.println("\n>>> Tocando playlist para usuário gratuito <<<\n");
			plataforma.tocarPlaylist(usuarioGratuito, playlist);

			// Exibir receita depois
			double receitaDepois = plataforma.calcularReceitaMensal();
			System.out.println("\nReceita mensal DEPOIS de tocar playlist: R$ " + String.format("%.2f", receitaDepois));
			System.out.println("Impressões do anúncio DEPOIS: " + anuncio.getImpressoes());
			System.out.println("Aumento na receita: R$ " + String.format("%.2f", receitaDepois - receitaAntes));

		} catch (IllegalArgumentException e) {
			System.out.println("Erro na demonstração: " + e.getMessage());
		}
	}

	// metodo de testes
	private static int popularAcervo(Plataforma plataforma) {
		cadastrarMusicaNoAcervo(plataforma, new Musica("Trem Bala", "Ana Vilela", 180));
		cadastrarMusicaNoAcervo(plataforma, new Musica("Aquarela", "Toquinho", 210));
		cadastrarMusicaNoAcervo(plataforma, new Musica("Evidencias", "Chitaozinho e Xororo", 278));
		return plataforma.getTotalMusicas();
	}


	// cadastra musicas manualmente, retorna a quantidade de musicas cadastradas
	private static int cadastrarMusica(Scanner sc, Plataforma plataforma) {
		String titulo = lerTextoValido(sc, "Titulo: ");
		String artista = lerTextoValido(sc, "Artista: ");
		int duracao = lerInteiroValido(sc, "Duracao em segundos: ");

		try {
			Musica musica = new Musica(titulo, artista, duracao);
			if (plataforma.cadastrarMusica(musica)) {
				System.out.println("Musica cadastrada com ID " + musica.getId() + ".");
				return plataforma.getTotalMusicas();
			}
			System.out.println("Nao foi possivel cadastrar a musica.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao cadastrar musica: " + e.getMessage());
		}

		return plataforma.getTotalMusicas();
	}

	// Cadastra podcast manualmente
	private static void cadastrarPodcast(Scanner sc, Plataforma plataforma) {
		String titulo = lerTextoValido(sc, "Titulo: ");
		String apresentador = lerTextoValido(sc, "Apresentador: ");
		int duracao = lerInteiroValido(sc, "Duracao em segundos: ");
		int numeroEpisodio = lerInteiroValido(sc, "Numero do episodio: ");

		try {
			Podcast podcast = new Podcast(titulo, apresentador, duracao, numeroEpisodio);
			if (plataforma.cadastrarConteudo(podcast)) {
				System.out.println("Podcast cadastrado com ID " + podcast.getId() + ".");
			} else {
				System.out.println("Nao foi possivel cadastrar o podcast.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao cadastrar podcast: " + e.getMessage());
		}
	}

	// Cadastra anúncio
	private static void cadastrarAnuncio(Scanner sc, Plataforma plataforma) {
		String anunciante = lerTextoValido(sc, "Anunciante: ");
		String titulo = lerTextoValido(sc, "Título do anúncio: ");
		int duracao = lerInteiroValido(sc, "Duração em segundos: ");
		double valorPorMilImpressoes = lerDoubleValido(sc, "Valor por mil impressões (R$): ");

		try {
			Anuncio anuncio = new Anuncio(anunciante, titulo, duracao, valorPorMilImpressoes);
			if (plataforma.cadastrarAnuncio(anuncio)) {
				System.out.println("Anúncio cadastrado com sucesso.");
			} else {
				System.out.println("Não foi possível cadastrar o anúncio.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao cadastrar anúncio: " + e.getMessage());
		}
	}

	// metodo para testes, cadastra musicas automaticamente sem o usuario ter que digitar nada
	private static int cadastrarMusicaNoAcervo(Plataforma plataforma, Musica musica) {
		try {
			if (plataforma.cadastrarMusica(musica)) {
				return plataforma.getTotalMusicas();
			}
		} catch (IllegalArgumentException | IllegalStateException e) {
			System.out.println("Erro ao cadastrar musica: " + e.getMessage());
		}
		return plataforma.getTotalMusicas();
	}

	private static Usuario escolherUsuario(Scanner sc, Plataforma plataforma) {
		if (plataforma.getTotalUsuarios() == 0) {
			System.out.println("Nenhum usuario cadastrado.");
			return null;
		}
		System.out.println("Usuarios cadastrados:");
		for (Usuario usuario : plataforma.getUsuarios()) {
			System.out.println(usuario.getId() + " - " + usuario.getNome());
		}
		int id = lerInteiroValido(sc, "Digite o ID do usuario: ");
		try {
			return plataforma.getUsuario(id);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Usuario inexistente.");
			return null;
		}
	}

	private static void trocarPlanoDoUsuario(Scanner sc, Plataforma plataforma) {
		Usuario usuario = escolherUsuario(sc, plataforma);
		if (usuario == null) {
			return;
		}

		System.out.println("Escolha o plano:");
		System.out.println("1 - Gratuito");
		System.out.println("2 - Individual");
		System.out.println("3 - Familia");
		int opcao = lerInteiroValido(sc, "Opcao: ");

		try {
			switch (opcao) {
				case 1:
					usuario.assinar(new PlanoGratuito());
					break;
				case 2:
					usuario.assinar(new PlanoIndividual());
					break;
				case 3:
					int membros = lerInteiroValido(sc, "Quantidade de membros (1 a 6): ");
					usuario.assinar(new PlanoFamilia(membros));
					break;
				default:
					System.out.println("Opcao invalida.");
					return;
			}
			System.out.println("Plano atualizado com sucesso.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao trocar plano: " + e.getMessage());
		}
	}

	private static void exibirPlanoAtualDoUsuario(Scanner sc, Plataforma plataforma) {
		Usuario usuario = escolherUsuario(sc, plataforma);
		if (usuario == null) {
			return;
		}
		System.out.println(usuario.getPlano().resumo());
	}

	private static void cadastrarUsuario(Scanner sc, Plataforma plataforma) {
		String nome = lerTextoValido(sc, "Nome: ");
		String email = lerTextoValido(sc, "E-mail: ");

		try {
			Usuario usuario = new Usuario(nome, email);
			if (plataforma.cadastrarUsuario(usuario)) {
				System.out.println("Usuario cadastrado.");
				return;
			}
			System.out.println("Nao foi possivel cadastrar o usuario.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
		}
	}

	private static void criarPlaylist(Scanner sc, Plataforma plataforma) {
		String nome = lerTextoValido(sc, "Nome da playlist: ");
		String nomeDono = lerTextoValido(sc, "Nome do dono: ");
		String emailDono = lerTextoValido(sc, "E-mail do dono: ");

		try {
			Usuario dono = new Usuario(nomeDono, emailDono);
			if (!plataforma.cadastrarUsuario(dono)) {
				System.out.println("Nao foi possivel cadastrar o dono.");
				return;
			}

			Playlist playlist = new Playlist(nome, dono, plataforma);
			plataforma.cadastrarPlaylist(playlist);
			System.out.println("Informe os IDs dos conteúdos (músicas ou podcasts). Digite 0 para terminar.");
			while (true) {
				int id = lerInteiroValido(sc, "ID do conteúdo: ");
				if (id == 0) {
					break;
				}

				try {
					Conteudo conteudo = plataforma.getConteudo(id);
					if (playlist.adicionar(conteudo)) {
						System.out.println("Conteúdo adicionado.");
					} else {
						System.out.println("Conteúdo inexistente, ja adicionado ou playlist cheia.");
					}
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Conteúdo inexistente na plataforma.");
				}
			}

			System.out.println("Playlist criada com " + playlist.getQuantidade() + " item(s).");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao criar playlist: " + e.getMessage());
		}
	}

	private static void gerenciarPlaylists(Scanner sc, Plataforma plataforma) {
		Playlist[] playlists = plataforma.getPlaylists();
		if (playlists.length == 0) {
			System.out.println("Nenhuma playlist cadastrada.");
			return;
		}

		System.out.println("\n=== Playlists ===");
		for (int indice = 0; indice < playlists.length; indice++) {
			System.out.println((indice + 1) + " - " + playlists[indice].getNome() + " (" + playlists[indice].getQuantidade() + " item(s))");
		}

		int indiceEscolhido = lerInteiroValido(sc, "Escolha a playlist: ");
		if (indiceEscolhido < 1 || indiceEscolhido > playlists.length) {
			System.out.println("Playlist invalida.");
			return;
		}

		abrirMenuPlaylist(sc, playlists[indiceEscolhido - 1], plataforma);
	}

	private static void abrirMenuPlaylist(Scanner sc, Playlist playlist, Plataforma plataforma) {
		boolean executando = true;
		while (executando) {
			System.out.println("\n=== Playlist: " + playlist.getNome() + " ===");
			System.out.println("1 - Adicionar conteudo");
			System.out.println("2 - Editar musica");
			System.out.println("3 - Remover conteudo");
			System.out.println("4 - Listar conteudos");
			System.out.println("0 - Voltar");
			int opcao = lerInteiroValido(sc, "");

			switch (opcao) {
				case 1:
					adicionarConteudoNaPlaylist(sc, plataforma, playlist);
					break;
				case 2:
					editarMusicaDaPlaylist(sc, playlist);
					break;
				case 3:
					removerConteudoDaPlaylist(sc, playlist);
					break;
				case 4:
					listarPlaylist(playlist);
					break;
				case 0:
					executando = false;
					break;
				default:
					System.out.println("Opcao invalida.");
			}
		}
	}

	private static void adicionarConteudoNaPlaylist(Scanner sc, Plataforma plataforma, Playlist playlist) {
		if (plataforma.getTotalMusicas() == 0) {
			System.out.println("Nao ha conteudos cadastrados na plataforma.");
			return;
		}

		listarAcervo(plataforma.getMusicas(), plataforma.getTotalMusicas());
		int id = lerInteiroValido(sc, "ID do conteudo: ");
		if (id == 0) {
			return;
		}

		try {
			Conteudo conteudo = plataforma.getConteudo(id);
			if (playlist.adicionar(conteudo)) {
				System.out.println("Conteúdo adicionado na playlist.");
			} else {
				System.out.println("Conteúdo inexistente, ja existe na playlist ou a playlist esta cheia.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Conteúdo inexistente na plataforma.");
		}
	}

	private static void editarMusicaDaPlaylist(Scanner sc, Playlist playlist) {
		if (playlist.getQuantidade() == 0) {
			System.out.println("Playlist vazia.");
			return;
		}

		listarPlaylist(playlist);
		int posicao = lerInteiroValido(sc, "Numero do conteudo na playlist: ");
		if (posicao < 1 || posicao > playlist.getQuantidade()) {
			System.out.println("Posicao invalida.");
			return;
		}

		Conteudo conteudo = playlist.getNaPosicao(posicao - 1);
		
		// Só permitir editar se for uma Musica
		if (!(conteudo instanceof Musica)) {
			System.out.println("Só é possível editar músicas. Este item é um " + conteudo.getClass().getSimpleName() + ".");
			return;
		}

		Musica musica = (Musica) conteudo;
		String novoTitulo = lerTextoValido(sc, "Novo titulo: ");
		String novoArtista = lerTextoValido(sc, "Novo artista: ");
		int novaDuracao = lerInteiroValido(sc, "Nova duracao em segundos: ");

		try {
			musica.setTitulo(novoTitulo);
			musica.setArtista(novoArtista);
			musica.setDuracaoSegundos(novaDuracao);
			System.out.println("Musica atualizada na playlist e na plataforma.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao editar musica: " + e.getMessage());
		}
	}

	private static void removerConteudoDaPlaylist(Scanner sc, Playlist playlist) {
		if (playlist.getQuantidade() == 0) {
			System.out.println("Playlist vazia.");
			return;
		}

		listarPlaylist(playlist);
		int posicao = lerInteiroValido(sc, "Numero do conteudo para remover: ");
		if (posicao < 1 || posicao > playlist.getQuantidade()) {
			System.out.println("Posicao invalida.");
			return;
		}

		if (playlist.removerNaPosicao(posicao - 1)) {
			System.out.println("Conteúdo removido da playlist.");
		} else {
			System.out.println("Nao foi possivel remover o conteúdo.");
		}
	}

	private static void listarPlaylist(Playlist playlist) {
		if (playlist.getQuantidade() == 0) {
			System.out.println("Playlist vazia.");
			return;
		}

		for (int indice = 0; indice < playlist.getQuantidade(); indice++) {
			Conteudo conteudo = playlist.getNaPosicao(indice);
			if (conteudo instanceof Musica) {
				Musica musica = (Musica) conteudo;
				System.out.println((indice + 1) + " - " + musica.getId() + " - [Música] " + musica.getTitulo() + " - "
						+ musica.getArtista() + " (" + musica.getDuracaoFormatada() + ")");
			} else if (conteudo instanceof Podcast) {
				Podcast podcast = (Podcast) conteudo;
				System.out.println((indice + 1) + " - " + podcast.getId() + " - [Podcast] " + podcast.getTitulo() + " - "
						+ podcast.getApresentador() + " Ep " + podcast.getNumeroEpisodio() + " (" + podcast.getDuracaoFormatada() + ")");
			}
		}
	}

	private static void buscarPorId(Scanner sc, Plataforma plataforma) {
		int id = lerInteiroValido(sc, "ID: ");
		try {
			exibirConteudo(plataforma.getConteudo(id));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Conteudo inexistente na plataforma.");
		}
	}

	private static void buscarPorTitulo(Scanner sc, Plataforma plataforma) {
		String titulo = lerTextoValido(sc, "Titulo: ");
		exibirConteudo(plataforma.getConteudoPorTitulo(titulo));
	}

	private static void reproduzirConteudo(Scanner sc, Plataforma plataforma) {
		int id = lerInteiroValido(sc, "ID: ");
		if (id == 0) {
			return;
		}

		try {
			Conteudo conteudo = plataforma.getConteudo(id);
			conteudo.reproduzir();
			System.out.println("Conteúdo reproduzido.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Conteudo inexistente na plataforma.");
		}
	}

	private static void atualizarMusicaNaPlataforma(Scanner sc, Plataforma plataforma) {
		if (plataforma.getTotalMusicas() == 0) {
			System.out.println("Acervo vazio.");
			return;
		}

		listarAcervo(plataforma.getMusicas(), plataforma.getTotalMusicas());
		int id = lerInteiroValido(sc, "ID da musica para atualizar: ");
		if (id == 0) {
			return;
		}

		try {
			plataforma.getMusica(id);
			String novoTitulo = lerTextoValido(sc, "Novo titulo: ");
			String novoArtista = lerTextoValido(sc, "Novo artista: ");
			int novaDuracao = lerInteiroValido(sc, "Nova duracao em segundos: ");

			if (plataforma.atualizarMusica(id, novoTitulo, novoArtista, novaDuracao)) {
				System.out.println("Musica atualizada na plataforma e nas playlists vinculadas.");
			} else {
				System.out.println("Nao foi possivel atualizar a musica.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Musica inexistente na plataforma.");
		}
	}

	private static void removerConteudoNaPlataforma(Scanner sc, Plataforma plataforma) {
		if (plataforma.getTotalMusicas() == 0) {
			System.out.println("Acervo vazio.");
			return;
		}

		listarAcervo(plataforma.getMusicas(), plataforma.getTotalMusicas());
		int id = lerInteiroValido(sc, "ID do conteudo para remover: ");
		if (id == 0) {
			return;
		}

		try {
			if (plataforma.removerConteudo(id)) {
				System.out.println("Conteudo removido da plataforma e de todas as playlists.");
			} else {
				System.out.println("Nao foi possivel remover o conteudo.");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Conteudo inexistente na plataforma.");
		}
	}

	// Toca uma playlist para um usuário
	private static void tocarPlaylistParaUsuario(Scanner sc, Plataforma plataforma) {
		if (plataforma.getTotalUsuarios() == 0) {
			System.out.println("Nenhum usuario cadastrado.");
			return;
		}

		Playlist[] playlists = plataforma.getPlaylists();
		if (playlists.length == 0) {
			System.out.println("Nenhuma playlist cadastrada.");
			return;
		}

		Usuario usuario = escolherUsuario(sc, plataforma);
		if (usuario == null) {
			return;
		}

		System.out.println("\n=== Playlists ===");
		for (int i = 0; i < playlists.length; i++) {
			System.out.println((i + 1) + " - " + playlists[i].getNome());
		}

		int indicePlaylist = lerInteiroValido(sc, "Escolha a playlist: ");
		if (indicePlaylist < 1 || indicePlaylist > playlists.length) {
			System.out.println("Playlist invalida.");
			return;
		}

		try {
			plataforma.tocarPlaylist(usuario, playlists[indicePlaylist - 1]);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao tocar playlist: " + e.getMessage());
		}
	}

	// Lista episódios de um apresentador
	private static void listarEpisodios(Scanner sc, Plataforma plataforma) {
		String apresentador = lerTextoValido(sc, "Nome do apresentador: ");
		System.out.println("\nEpisódios de " + apresentador + ":");
		plataforma.listarEpisodiosDe(apresentador);
	}

	// Exibe a receita mensal da plataforma
	private static void exibirReceitaMensal(Plataforma plataforma) {
		double receita = plataforma.calcularReceitaMensal();
		System.out.println("Receita mensal da plataforma: R$ " + String.format("%.2f", receita));
	}

	private static void seguirUsuario(Scanner sc, Plataforma plataforma) {
		Usuario[] usuarios = plataforma.getUsuarios();
		if (usuarios.length < 2) {
			System.out.println("Cadastre pelo menos dois usuarios para seguir.");
			return;
		}

		for (int indice = 0; indice < usuarios.length; indice++) {
			System.out.println((indice + 1) + " - " + usuarios[indice].getNome() + " (ID " + usuarios[indice].getId() + ")");
		}

		int idSeguidor = lerInteiroValido(sc, "ID do usuario que vai seguir: ");
		int idSeguido = lerInteiroValido(sc, "ID do usuario a seguir: ");
		if (idSeguidor == 0 || idSeguido == 0) {
			return;
		}

		try {
			Usuario seguidor = plataforma.getUsuario(idSeguidor);
			Usuario seguido = plataforma.getUsuario(idSeguido);
			if (seguidor.seguir(seguido)) {
				System.out.println(seguidor.getNome() + " agora segue " + seguido.getNome() + ".");
			} else {
				System.out.println(seguidor.getNome() + " ja segue " + seguido.getNome() + ".");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Usuário inexistente na plataforma.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao seguir usuário: " + e.getMessage());
		}
	}

	private static void deixarDeSeguirUsuario(Scanner sc, Plataforma plataforma) {
		Usuario[] usuarios = plataforma.getUsuarios();
		if (usuarios.length == 0) {
			System.out.println("Nenhum usuario cadastrado.");
			return;
		}

		for (int indice = 0; indice < usuarios.length; indice++) {
			System.out.println((indice + 1) + " - " + usuarios[indice].getNome() + " (ID " + usuarios[indice].getId() + ")");
		}

		int idSeguidor = lerInteiroValido(sc, "ID do usuario que vai deixar de seguir: ");
		int idSeguido = lerInteiroValido(sc, "ID do usuario a parar de seguir: ");
		if (idSeguidor == 0 || idSeguido == 0) {
			return;
		}

		try {
			Usuario seguidor = plataforma.getUsuario(idSeguidor);
			Usuario seguido = plataforma.getUsuario(idSeguido);
			if (seguidor.deixarDeSeguir(seguido)) {
				System.out.println(seguidor.getNome() + " deixou de seguir " + seguido.getNome() + ".");
			} else {
				System.out.println(seguidor.getNome() + " nao segue " + seguido.getNome() + ".");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Usuário inexistente na plataforma.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao deixar de seguir usuário: " + e.getMessage());
		}
	}

	private static void listarUsuariosSeguidos(Scanner sc, Plataforma plataforma) {
		Usuario[] usuarios = plataforma.getUsuarios();
		if (usuarios.length == 0) {
			System.out.println("Nenhum usuario cadastrado.");
			return;
		}

		for (int indice = 0; indice < usuarios.length; indice++) {
			System.out.println((indice + 1) + " - " + usuarios[indice].getNome() + " (ID " + usuarios[indice].getId() + ")");
		}

		int idUsuario = lerInteiroValido(sc, "ID do usuario: ");
		if (idUsuario == 0) {
			return;
		}

		try {
			Usuario usuario = plataforma.getUsuario(idUsuario);
			java.util.ArrayList<Usuario> seguidos = usuario.getSeguindo();
			if (seguidos.isEmpty()) {
				System.out.println(usuario.getNome() + " nao segue nenhum usuario.");
				return;
			}
			System.out.println("Usuarios seguidos por " + usuario.getNome() + ":");
			for (Usuario seguido : seguidos) {
				System.out.println("- " + seguido.getNome() + " (ID " + seguido.getId() + ")");
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Usuário inexistente na plataforma.");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao listar usuários seguidos: " + e.getMessage());
		}
	}

	// Lista todo o acervo de musicas, caso nao haja musicas cadastradas, informa que o acervo esta vazio
	
	private static void listarAcervo(Musica[] musicas, int quantidade) {
		if (quantidade == 0) {
			System.out.println("Acervo vazio.");
			return;
		}

		for (int indice = 0; indice < quantidade; indice++) {
			Musica musica = musicas[indice];
			System.out.println(musica.getId() + " - " + musica.getTitulo() + " - "
					+ musica.getArtista() + " (" + musica.getDuracaoFormatada() + ")");
		}
	}

	//Padroniza o formato de exibição de um conteudo, para não repetir código em vários lugares
	private static void exibirConteudo(Conteudo conteudo) {
		if (conteudo == null) {
			System.out.println("Conteudo nao encontrado.");
			return;
		}

		if (conteudo instanceof Musica) {
			Musica musica = (Musica) conteudo;
			System.out.println(musica.getId() + " - [Música] " + musica.getTitulo() + " - "
					+ musica.getArtista() + " (" + musica.getDuracaoFormatada() + ")");
		} else if (conteudo instanceof Podcast) {
			Podcast podcast = (Podcast) conteudo;
			System.out.println(podcast.getId() + " - [Podcast] " + podcast.getTitulo() + " - "
					+ podcast.getApresentador() + " Ep " + podcast.getNumeroEpisodio() + " (" + podcast.getDuracaoFormatada() + ")");
		}
	}


	private static void exibirMenu() {
		System.out.println("\n=== Sonora ===");
		System.out.println("1 - Cadastrar musica manualmente");
		System.out.println("2 - Cadastrar podcast");
		System.out.println("3 - Cadastrar usuario");
		System.out.println("4 - Criar playlist e adicionar conteudo");
		System.out.println("5 - Buscar conteudo por id");
		System.out.println("6 - Buscar conteudo por titulo");
		System.out.println("7 - Reproduzir um conteudo");
		System.out.println("8 - Listar acervo");
		System.out.println("9 - Gerenciar playlists");
		System.out.println("10 - Atualizar musica da plataforma");
		System.out.println("11 - Remover conteudo da plataforma");
		System.out.println("12 - Seguir usuario");
		System.out.println("13 - Deixar de seguir usuario");
		System.out.println("14 - Listar usuarios seguidos");
		System.out.println("15 - Trocar plano do usuario");
		System.out.println("16 - Exibir plano atual do usuario");
		System.out.println("17 - Cadastrar anuncio");
		System.out.println("18 - Tocar playlist para usuario");
		System.out.println("19 - Listar episodios de um apresentador");
		System.out.println("20 - Exibir receita mensal");
		System.out.println("0 - Sair");
		System.out.print("Opcao: ");
	}

}

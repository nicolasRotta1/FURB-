import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Plataforma plataforma = new Plataforma();

		// para testes
		int quantidadeMusicas = popularAcervo(plataforma);
		rodarDemo(plataforma);

		boolean executando = true;
		while (executando) {
			exibirMenu();
			Integer opcao = lerInteiro(scanner);
			if (opcao == null) {
				break;
			}

			switch (opcao) {
				case 1:
					quantidadeMusicas = cadastrarMusica(scanner, plataforma);
					break;
				case 2:
					cadastrarUsuario(scanner, plataforma);
					break;
				case 3:
					criarPlaylist(scanner, plataforma);
					break;
				case 4:
					buscarPorId(scanner, plataforma);
					break;
				case 5:
					buscarPorTitulo(scanner, plataforma);
					break;
				case 6:
					reproduzirMusica(scanner, plataforma);
					break;
				case 7:
					listarAcervo(plataforma.getMusicas(), plataforma.getTotalMusicas());
					break;
				case 0:
					executando = false;
					break;
				default:
					System.out.println("Opcao invalida.");
			}
		}

		scanner.close();
	}

	// metodo de testes
	private static int popularAcervo(Plataforma plataforma) {
		cadastrarMusicaNoAcervo(plataforma, new Musica("Trem Bala", "Ana Vilela", 180));
		cadastrarMusicaNoAcervo(plataforma, new Musica("Aquarela", "Toquinho", 210));
		cadastrarMusicaNoAcervo(plataforma, new Musica("Evidencias", "Chitaozinho e Xororo", 278));
		return plataforma.getTotalMusicas();
	}


	// cadastra musicas manualmente, retorna a quantidade de musicas cadastradas
	private static int cadastrarMusica(Scanner scanner, Plataforma plataforma) {
		System.out.print("Titulo: ");
		String titulo = scanner.nextLine();
		System.out.print("Artista: ");
		String artista = scanner.nextLine();
		System.out.print("Duracao em segundos: ");
		Integer duracao = lerInteiro(scanner);

		if (duracao == null || duracao < 0) {
			System.out.println("Duracao invalida.");
			return plataforma.getTotalMusicas();
		}

		Musica musica = new Musica(titulo, artista, duracao);
		if (plataforma.cadastrarMusica(musica)) {
			System.out.println("Musica cadastrada com ID " + musica.getId() + ".");
			return plataforma.getTotalMusicas();
		}

		System.out.println("Nao foi possivel cadastrar a musica.");
		return plataforma.getTotalMusicas();
	}


	// metodo para testes, cadastra musicas automaticamente sem o usuario ter que digitar nada
	private static int cadastrarMusicaNoAcervo(Plataforma plataforma, Musica musica) {
		if (plataforma.cadastrarMusica(musica)) {
			return plataforma.getTotalMusicas();
		}
		return plataforma.getTotalMusicas();
	}

	private static void cadastrarUsuario(Scanner scanner, Plataforma plataforma) {
		System.out.print("Nome: ");
		String nome = scanner.nextLine();
		System.out.print("E-mail: ");
		String email = scanner.nextLine();
		Usuario usuario = new Usuario(nome, email);

		if (plataforma.cadastrarUsuario(usuario)) {
			System.out.println("Usuario cadastrado.");
		} else {
			System.out.println("Nao foi possivel cadastrar o usuario.");
		}
	}

	private static void criarPlaylist(Scanner scanner, Plataforma plataforma) {
		System.out.print("Nome da playlist: ");
		String nome = scanner.nextLine();
		System.out.print("Nome do dono: ");
		String nomeDono = scanner.nextLine();
		System.out.print("E-mail do dono: ");
		String emailDono = scanner.nextLine();

		Usuario dono = new Usuario(nomeDono, emailDono);

		if (!plataforma.cadastrarUsuario(dono)) {
			System.out.println("Nao foi possivel cadastrar o dono.");
			return;
		}

		Playlist playlist = new Playlist(nome, dono);
		System.out.println("Informe os IDs das musicas. Digite 0 para terminar.");
		while (true) {
			Integer id = lerInteiro(scanner);
			if (id == null || id == 0) {
				break;
			}

			Musica musica = plataforma.buscarMusicaPorId(id);
			if (playlist.adicionar(musica)) {
				System.out.println("Musica adicionada.");
			} else {
				System.out.println("Musica inexistente ou playlist cheia.");
			}
		}

		System.out.println("Playlist criada com " + playlist.getQuantidade() + " musica(s).");
	}

	private static void buscarPorId(Scanner scanner, Plataforma plataforma) {
		System.out.print("ID: ");
		Integer id = lerInteiro(scanner);
		if (id == null) {
			return;
		}
		exibirMusica(plataforma.buscarMusicaPorId(id));
	}

	private static void buscarPorTitulo(Scanner scanner, Plataforma plataforma) {
		System.out.print("Titulo: ");
		exibirMusica(plataforma.buscarMusica(scanner.nextLine()));
	}

	private static void reproduzirMusica(Scanner scanner, Plataforma plataforma) {
		System.out.print("ID: ");
		Integer id = lerInteiro(scanner);
		if (id == null) {
			return;
		}

		Musica musica = plataforma.buscarMusicaPorId(id);
		if (musica == null) {
			System.out.println("Musica nao encontrada.");
			return;
		}

		musica.reproduzir();
		System.out.println("Musica reproduzida.");
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

	//Padroniza o formato de exibição de uma música, para não repetir código em vários lugares
	private static void exibirMusica(Musica musica) {
		if (musica == null) {
			System.out.println("Musica nao encontrada.");
			return;
		}

		System.out.println(musica.getId() + " - " + musica.getTitulo() + " - "
				+ musica.getArtista() + " (" + musica.getDuracaoFormatada() + ")");
	}


	// Para demonstracao automatica das funcionalidades
	private static void rodarDemo(Plataforma plataforma) {
		System.out.println("\n=== DEMONSTRACAO AUTOMATICA ===");
		Musica[] acervo = plataforma.getMusicas();
		int quantidadeMusicas = plataforma.getTotalMusicas();

		System.out.println("\n1) Listar acervo de musicas cadastradas (esperado ids 1,2,3):");
		listarAcervo(acervo, quantidadeMusicas);

		System.out.println("\n2) Reproduzir musica ID 1 tres vezes e mostrar reproducoes:");
		Musica m1 = plataforma.buscarMusicaPorId(1);
		if (m1 != null) {
			m1.reproduzir();
			m1.reproduzir();
			m1.reproduzir();
			System.out.println("Musica " + m1.getTitulo() + " reproducoes = " + m1.getReproducoes());
		} else {
			System.out.println("Musica ID nao encontrada.");
		}

		System.out.println("\n3) Duracoes formatadas:");
		Musica d1 = new Musica("Tmp354", "Autor", 354);
		Musica d2 = new Musica("Tmp65", "Autor", 65);
		Musica d3 = new Musica("Tmp600", "Autor", 600);
		System.out.println("354 -> " + d1.getDuracaoFormatada());
		System.out.println("65  -> " + d2.getDuracaoFormatada());
		System.out.println("600 -> " + d3.getDuracaoFormatada());

		System.out.println("\n4) Criar usuario, playlist e adicionar musicas:");
		Usuario usuarioTeste = new Usuario("Teste", "teste@teste.com");
		plataforma.cadastrarUsuario(usuarioTeste);
		Playlist plDemo = new Playlist("DemoPlaylist", usuarioTeste);
		if (quantidadeMusicas >= 2) {
			plDemo.adicionar(acervo[0]);
			plDemo.adicionar(acervo[1]);
		}
		System.out.println("Quantidade na playlist = " + plDemo.getQuantidade());
		System.out.println("Duracao total (segundos) = " + plDemo.getDuracaoTotalSegundos());

		System.out.println("\n5) Preencher playlist ate 100 e tentar adicionar a 101a:");
		Playlist lotacao = new Playlist("Lotacao", usuarioTeste);
		Musica musicaParaRep = acervo.length > 0 ? acervo[0] : null;
		for (int i = 0; i < 100; i++) {
			lotacao.adicionar(musicaParaRep);
		}
		boolean adicionou101 = lotacao.adicionar(musicaParaRep);
		System.out.println("Tentativa adicionar 101a retornou: " + adicionou101);
		System.out.println("Quantidade apos tentativa = " + lotacao.getQuantidade());

		System.out.println("\n6) Remover do meio e mostrar que posicoes andaram:");
		int idx = quantidadeMusicas;
		Musica extra1 = new Musica("Extra1", "A", 100);
		Musica extra2 = new Musica("Extra2", "B", 110);
		if (plataforma.cadastrarMusica(extra1)) {
			idx++;
		}
		if (plataforma.cadastrarMusica(extra2)) {
			idx++;
		}
		acervo = plataforma.getMusicas();
		Playlist pequena = new Playlist("Pequena", usuarioTeste);
		for (int i = 0; i < 5; i++) {
			Musica m = acervo[i % idx];
			pequena.adicionar(m);
		}
		System.out.println("Antes da remocao: ");
		for (int i = 0; i < pequena.getQuantidade(); i++) {
			System.out.println(i + ": " + pequena.getNaPosicao(i).getTitulo());
		}
		pequena.removerNaPosicao(2);
		System.out.println("Depois da remocao (indice 2 removido): ");
		for (int i = 0; i < pequena.getQuantidade(); i++) {
			System.out.println(i + ": " + pequena.getNaPosicao(i).getTitulo());
		}

		System.out.println("\n7) Buscar por id existente e inexistente:");
		Musica existe = plataforma.buscarMusicaPorId(1);
		Musica naoExiste = plataforma.buscarMusicaPorId(99999);
		System.out.println("Busca id 1 -> " + (existe != null ? existe.getTitulo() : "null"));
		System.out.println("Busca id 99999 -> " + (naoExiste != null ? naoExiste.getTitulo() : "null"));

		System.out.println("\n8) Buscar por titulo 'Aquarela':");
		Musica porTitulo = plataforma.buscarMusica("Aquarela");
		System.out.println(porTitulo != null ? porTitulo.getId() + " - " + porTitulo.getTitulo() : "null");

		System.out.println("\n9) Reproduzir tudo em playlist de exemplo e mostrar reproducoes:");
		for (int i = 0; i < pequena.getQuantidade(); i++) {
			System.out.println("Antes: " + pequena.getNaPosicao(i).getTitulo() + " -> " + pequena.getNaPosicao(i).getReproducoes());
		}
		pequena.reproduzirTudo();
		for (int i = 0; i < pequena.getQuantidade(); i++) {
			System.out.println("Depois: " + pequena.getNaPosicao(i).getTitulo() + " -> " + pequena.getNaPosicao(i).getReproducoes());
		}

		System.out.println("\n=== FIM DA DEMONSTRACAO ===\n");
	}

	private static void exibirMenu() {
		System.out.println("\n=== Sonora ===");
		System.out.println("1 - Cadastrar musica manualmente");
		System.out.println("2 - Cadastrar usuario");
		System.out.println("3 - Criar playlist e adicionar musicas");
		System.out.println("4 - Buscar musica por id");
		System.out.println("5 - Buscar musica por titulo");
		System.out.println("6 - Reproduzir uma musica");
		System.out.println("7 - Listar acervo");
		System.out.println("0 - Sair");
		System.out.print("Opcao: ");
	}


	// Para controle de erros
	private static Integer lerInteiro(Scanner scanner) {
		while (scanner.hasNextLine()) {
			String entrada = scanner.nextLine();
			Scanner linha = new Scanner(entrada);
			if (linha.hasNextInt()) {
				int valor = linha.nextInt();
				linha.close();
				return valor;
			}
			linha.close();
			System.out.println("Digite um numero valido.");
		}
		return null;
	}

}

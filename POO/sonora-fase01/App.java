import java.util.Scanner;

public class App {
	private static final int CAPACIDADE_MUSICAS_TESTE = 500;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Plataforma plataforma = new Plataforma();
		Musica[] musicasCadastradas = new Musica[CAPACIDADE_MUSICAS_TESTE];
		int quantidadeMusicas = popularAcervo(plataforma, musicasCadastradas);

		boolean executando = true;
		while (executando) {
			exibirMenu();
			Integer opcao = lerInteiro(scanner);
			if (opcao == null) {
				break;
			}

			switch (opcao) {
				case 1:
					quantidadeMusicas = cadastrarMusica(scanner, plataforma, musicasCadastradas, quantidadeMusicas);
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
					listarAcervo(musicasCadastradas, quantidadeMusicas);
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

	private static int popularAcervo(Plataforma plataforma, Musica[] musicas) {
		int quantidade = 0;
		quantidade = cadastrarMusicaNoAcervo(plataforma, musicas, quantidade,
				new Musica("Trem Bala", "Ana Vilela", 180));
		quantidade = cadastrarMusicaNoAcervo(plataforma, musicas, quantidade,
				new Musica("Aquarela", "Toquinho", 210));
		quantidade = cadastrarMusicaNoAcervo(plataforma, musicas, quantidade,
				new Musica("Evidencias", "Chitaozinho e Xororo", 278));
		return quantidade;
	}

	private static int cadastrarMusica(Scanner scanner, Plataforma plataforma,
			Musica[] musicas, int quantidade) {
		System.out.print("Titulo: ");
		String titulo = scanner.nextLine();
		System.out.print("Artista: ");
		String artista = scanner.nextLine();
		System.out.print("Duracao em segundos: ");
		Integer duracao = lerInteiro(scanner);

		if (duracao == null || duracao < 0) {
			System.out.println("Duracao invalida.");
			return quantidade;
		}

		Musica musica = new Musica(titulo, artista, duracao);
		if (plataforma.cadastrarMusica(musica)) {
			musicas[quantidade] = musica;
			System.out.println("Musica cadastrada com ID " + musica.getId() + ".");
			return quantidade + 1;
		}

		System.out.println("Nao foi possivel cadastrar a musica.");
		return quantidade;
	}

	private static int cadastrarMusicaNoAcervo(Plataforma plataforma, Musica[] musicas,
			int quantidade, Musica musica) {
		if (plataforma.cadastrarMusica(musica)) {
			musicas[quantidade] = musica;
			return quantidade + 1;
		}
		return quantidade;
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

	private static void exibirMusica(Musica musica) {
		if (musica == null) {
			System.out.println("Musica nao encontrada.");
			return;
		}

		System.out.println(musica.getId() + " - " + musica.getTitulo() + " - "
				+ musica.getArtista() + " (" + musica.getDuracaoFormatada() + ")");
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

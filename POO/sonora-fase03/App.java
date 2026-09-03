import java.util.Scanner;

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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Plataforma plataforma = new Plataforma();

		// para testes
		int quantidadeMusicas = popularAcervo(plataforma);
		

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
						cadastrarUsuario(sc, plataforma);
						break;
					case 3:
						criarPlaylist(sc, plataforma);
						break;
					case 4:
						buscarPorId(sc, plataforma);
						break;
					case 5:
						buscarPorTitulo(sc, plataforma);
						break;
					case 6:
						reproduzirMusica(sc, plataforma);
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
		} finally {
			System.out.println("Sonora encerrada.");
			sc.close();
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

			Playlist playlist = new Playlist(nome, dono);
			System.out.println("Informe os IDs das musicas. Digite 0 para terminar.");
			while (true) {
				int id = lerInteiroValido(sc, "ID da musica: ");
				if (id == 0) {
					break;
				}

				Musica musica = plataforma.getMusica(id);
				if (playlist.adicionar(musica)) {
					System.out.println("Musica adicionada.");
				} else {
					System.out.println("Musica inexistente ou playlist cheia.");
				}
			}

			System.out.println("Playlist criada com " + playlist.getQuantidade() + " musica(s).");
		} catch (IllegalArgumentException e) {
			System.out.println("Erro ao criar playlist: " + e.getMessage());
		}
	}

	private static void buscarPorId(Scanner sc, Plataforma plataforma) {
		int id = lerInteiroValido(sc, "ID: ");
		exibirMusica(plataforma.getMusica(id));
	}

	private static Musica buscarMusicaPorTitulo(Plataforma plataforma, String titulo) {
		if (titulo == null) {
			return null;
		}

		for (Musica musica : plataforma.getMusicas()) {
			if (titulo.equalsIgnoreCase(musica.getTitulo())) {
				return musica;
			}
		}

		return null;
	}

	private static void buscarPorTitulo(Scanner sc, Plataforma plataforma) {
		String titulo = lerTextoValido(sc, "Titulo: ");
		exibirMusica(buscarMusicaPorTitulo(plataforma, titulo));
	}

	private static void reproduzirMusica(Scanner sc, Plataforma plataforma) {
		int id = lerInteiroValido(sc, "ID: ");
		if (id == 0) {
			return;
		}

		Musica musica = plataforma.getMusica(id);

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

}

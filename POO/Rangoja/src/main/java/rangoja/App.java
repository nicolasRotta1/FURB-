package rangoja;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            List<ItemCardapio> cardapio = montarCardapio();
            mostrarCardapio(cardapio);

            Cliente ana = new Cliente("Ana Souza", "ana@furb.br", 20);
            Cliente beto = new Cliente("Beto Silva", "beto@furb.br", 17, ana);
            System.out.println("\nCadastro:");
            System.out.println("Clientes: " + ana + " e " + beto);
            System.out.println("Cashback da Ana: R$ " + formatarMoeda(ana.getSaldoCashback()));

            Pedido pedidoBeto = new Pedido(beto);
            ItemCardapio xisSalada = new Prato("Xis Salada", 30.0, 'M');
            int quantidadeXis = lerQuantidadePedido(scanner, pedidoBeto, xisSalada);
            System.out.println("Beto pediu " + quantidadeXis + " Xis Salada.");

            System.out.println("\nTentativa de compra de bebida alcoólica:");
            ItemCardapio chopp = new Bebida("Chopp", 12.0, 500, true);
            try {
                pedidoBeto.adicionarItem(chopp);
            } catch (IllegalArgumentException e) {
                System.out.println("Bloqueado: " + e.getMessage());
            }

            System.out.println("\nPedido da Ana:");
            Pedido pedidoAna = new Pedido(ana);
            try {
                pedidoAna.adicionarItem(new Prato("Xis Salada", 30.0, 'M'));
                pedidoAna.adicionarItem(new Bebida("Refri", 6.0, 350, false));
                pedidoAna.adicionarItem(new Sobremesa("Pudim", 8.0, 150), 2);
                pedidoAna.finalizar();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Pedido não foi concluído: " + e.getMessage());
            }

            try {
                pedidoAna.finalizar();
            } catch (IllegalStateException e) {
                System.out.println("Pedido da Ana já estava finalizado.");
            }

            System.out.println("\nUso de cashback:");
            try {
                ana.usarCashback(ana.getSaldoCashback() + 10.0);
            } catch (SaldoInsuficienteException e) {
                System.out.println("Não foi possível usar o cashback: " + e.getMessage());
            } finally {
                System.out.println("Saldo atual: R$ " + formatarMoeda(ana.getSaldoCashback()));
            }

            System.out.println("\nCobrança:");
            AssinaturaClube assinaturaAna = new AssinaturaClube(ana, 19.90, 12);
            CentralCobranca central = new CentralCobranca();
            central.registrar(pedidoAna);
            central.registrar(assinaturaAna);
            System.out.println(central.gerarRelatorio());

        } finally {
            scanner.close();
        }
    }

    private static List<ItemCardapio> montarCardapio() {
        List<ItemCardapio> cardapio = new ArrayList<>();
        cardapio.add(new Prato("Xis Salada", 30.0, 'M'));
        cardapio.add(new Bebida("Refri", 6.0, 350, false));
        cardapio.add(new Bebida("Chopp", 12.0, 500, true));
        cardapio.add(new Sobremesa("Pudim", 8.0, 150));
        return cardapio;
    }

    private static void mostrarCardapio(List<ItemCardapio> cardapio) {
        System.out.println("Cardápio:");
        for (ItemCardapio item : cardapio) {
            System.out.println(item);
        }
    }

    private static int lerQuantidadePedido(Scanner scanner, Pedido pedido, ItemCardapio item) {
        while (true) {
            try {
                System.out.print("Digite a quantidade do prato Xis Salada: ");
                String entrada = scanner.nextLine();
                int quantidade = Integer.parseInt(entrada.trim());
                pedido.adicionarItem(item, quantidade);
                return quantidade;
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números inteiros.");
            } catch (IllegalArgumentException e) {
                System.out.println("Quantidade inválida: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Entrada não informada. Usando 1.");
                pedido.adicionarItem(item, 1);
                return 1;
            }
        }
    }

    private static String formatarMoeda(double valor) {
        return String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valor);
    }
}

import java.util.Scanner;

public class questao2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] espacos = new int[10];

        String digitado;
        do  {
            System.out.println("Digite INICIAR para começar o programa: ");
            digitado = sc.next().toLowerCase();

            
        }while(!digitado.equals("iniciar"));


        questao2mochila mochila = new questao2mochila();


        do {
        System.out.println("Escolha uma Opção: \n a - Adicionar peso na mochila \n b - Remover peso da mochila \n c - Listar pesos da mochila \n d - Ordenar mochila em ordem decrescente\n e - Verificar se peso um existe na mochila \n f - Sair");
        System.out.println("Sua escolha: ");
        String escolha = sc.next().toLowerCase();
        
        if (escolha.equals("f")) {
            System.out.println("Encerrando programa ....");
            break;
        }
        switch (escolha) {
            case "a":
                mochila.adicionar(espacos);
                break;
            
            case "b":
                mochila.remover(espacos);
                break;

            case "c":
                mochila.listar(espacos);
                break;

            case "d":
                mochila.ordenar(espacos);
                break;

            case "e":
                mochila.verificar(espacos);
                break;

            default:
                System.out.println("Opção inválida!!");
                break;
        }
        } while (true);
            

        sc.close();
        
    }
}

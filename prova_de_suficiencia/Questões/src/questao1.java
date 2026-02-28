import java.util.Scanner;

public class questao1 {
    public static void main(String[] args) throws Exception {

        ///Fluxograma :
        // Entrada -> dinheiro >= 100? Sim então notas 100 + 1, e dinheiro - 100
        //                        Não então, dinheiro >= 50? Sim, notas 50 + 1 e dinheiro - 50
        //                                                  Não, então, dinheiro >= 20? Sim, notas 20 + 1 e dinheiro - 20
        //                                                                              Não, então valor restante = dinheiro

        Scanner sc = new Scanner(System.in);

        int notas100 = 0;
        int notas50 = 0;
        int notas20 = 0;

        System.out.println("Insira um valor inteiro:");
        int dinheiro = sc.nextInt();

        while (dinheiro >= 100) {
            notas100++;
            dinheiro = dinheiro - 100;
        }


        
        while (dinheiro >= 50) {
            notas50++;
            dinheiro = dinheiro - 50;
        }

        while (dinheiro >= 20) {
            notas20++;
            dinheiro = dinheiro - 20;
        }

        System.out.println("Notas de 100: "+ notas100);
        System.out.println("Notas de 50: "+ notas50);
        System.out.println("Notas de 20: "+notas20);
        System.out.println("Valor Restante: "+ dinheiro);

        System.out.println(3 % 2);
    }

    
}

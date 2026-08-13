package Aula02.Projetinho;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //testes
        ContaBancaria conta1 = new ContaBancaria();
        conta1.setTitular("Nicolau");

        ContaBancaria conta2 = new ContaBancaria();
        conta2.setTitular("Bob");

        conta1.depositar(1000.00);
        conta1.depositar(700.00);

        conta2.depositar(5000.00);

        conta2.sacar(3000.00);

        conta2.transferir(1800.00, conta1);

        conta1.depositar(-500.00);
        conta1.sacar(-100.00);
        conta1.sacar(999999.00);
        conta2.transferir(-200.00, conta1);
        conta2.transferir(999999.00, conta1);



        menu();

    }

    public static void menu(){
        ArrayList<ContaBancaria> contaBancarias = new ArrayList<>();
        ContaBancaria contaAtual;

        while(true){
            System.out.println("------------ Nico Bank ------------");

            System.out.println("(1) - Contas Cadastradas.");
            System.out.println("(2) - Criar Conta");
            System.out.println("(3) - Sair");

            System.out.println("Opção:  ");
            int opc = App.sc.nextInt();

            if(opc == 3){
                System.out.println("Encerrando programa . . .");
                break;
            }

            switch (opc) {
                case 1:
                    if (contaBancarias.isEmpty()) {
                        System.out.println("Nenhuma conta cadastrada ainda!");
                        break;
                    }

                    mostrarContas(contaBancarias);

                    System.out.println("Digite o número da conta que deseja acessar:            digite -1 para voltar.");
                    int indice = App.sc.nextInt();

                    if (indice == -1) {
                        break;
                    }

                    if (indice < 0 || indice >= contaBancarias.size()) {
                        System.out.println("Conta inválida!");
                        break;
                    }

                    contaAtual = contaBancarias.get(indice);
                    contaAtual.menuConta(contaBancarias);
                    break;
                case 2:
                    contaBancarias.add(ContaBancaria.cadastrarContaBancaria());
                    contaAtual = contaBancarias.getLast();
                    contaAtual.menuConta(contaBancarias);
                    break;

                default:
                    break;
            }

        }
    }

    public static void mostrarContas(ArrayList<ContaBancaria> contaBancarias){
        int contador = 0;
        for (ContaBancaria contaBancaria : contaBancarias) {
            System.out.println("("+contador+") - Titular = "+contaBancaria.getTitular());
            System.out.println("    - Numero Conta: "+contaBancaria.getNumeroConta());
            contador++;
        }

    }


}
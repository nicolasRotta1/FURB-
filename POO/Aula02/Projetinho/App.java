package Aula02.Projetinho;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

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
                    
                    break;
                case 2:
                    contaBancarias.add(ContaBancaria.cadastrarContaBancaria());
                    contaAtual = contaBancarias.getLast();
                    contaAtual.menuConta();
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
            System.out.println("               - Numero Conta: "+contaBancaria.getNumeroConta());
        }
        
    }

    
}

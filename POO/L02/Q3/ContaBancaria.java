package Aula02.Projetinho;

import java.util.ArrayList;

public class ContaBancaria {

    private String titular;
    private int numeroConta;
    private double saldo;
    private static int qtdContas;


    public ContaBancaria() {
        this("Titular não informado");
    }

    public ContaBancaria(String titular) {
        qtdContas++;
        this.titular = titular;
        this.numeroConta = qtdContas + 1000;
        this.saldo = 0;
    }


    public static ContaBancaria cadastrarContaBancaria(){
        System.out.println("-- Para Criar sua conta Precisamos de alguns dados -- ");

        System.out.print("Nome de quem vai utilizar esta conta: ");
        String titular = App.sc.next();

        ContaBancaria conta = new ContaBancaria(titular);

        return conta;

    }


    public void menuConta(ArrayList<ContaBancaria> contas){

        while (true) {
            System.out.println("-----------Menu----------");

            mostrarDados();

            System.out.println("");
            System.out.println("");
            System.out.println("Escolha uma opção: ");
            System.out.println("(1) - Depositar -");
            System.out.println("(2) - Sacar - ");
            System.out.println("(3) - Transferir - ");
            System.out.println("(4) - Listar todas as contas - ");
            System.out.println("(5) - Sair - ");
            System.out.println("Opção: ");
            int opc = App.sc.nextInt();

            if (opc == 5) {
                System.out.println("Saindo da conta . . .");
                break;
            }

            switch (opc) {
                case 1:
                    System.out.println("Digite o valor que deseja depositar:                    digite 0 para sair.");
                    double valor = App.sc.nextDouble();
                    if (valor == 0) {
                        break;
                    }

                    depositar(valor);
                    break;

                case 2:
                    System.out.println("Digite o valor que deseja sacar:                    digite 0 para sair.");
                    valor = App.sc.nextDouble();
                    if (valor == 0) {
                        break;
                    }

                    sacar(valor);
                    break;

                case 3:
                    System.out.println("Digite o número da conta destino:                    digite 0 para sair.");
                    int numeroDestino = App.sc.nextInt();
                    if (numeroDestino == 0) {
                        break;
                    }

                    ContaBancaria contaDestino = buscarContaPorNumero(numeroDestino, contas);

                    if (contaDestino == null) {
                        System.out.println("Conta destino não encontrada!");
                        break;
                    }

                    if (contaDestino == this) {
                        System.out.println("Você não pode transferir para a própria conta!");
                        break;
                    }

                    System.out.println("Digite o valor que deseja transferir:                    digite 0 para sair.");
                    valor = App.sc.nextDouble();
                    if (valor == 0) {
                        break;
                    }

                    transferir(valor, contaDestino);
                    break;

                case 4:
                    listarContas(contas);
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }

    public static ContaBancaria buscarContaPorNumero(int numeroConta, ArrayList<ContaBancaria> contas){
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

    public void listarContas(ArrayList<ContaBancaria> contas){
        System.out.println("-----------Todas as contas----------");

        for (ContaBancaria conta : contas) {
            if (conta == this) {
                continue;
            }
            conta.mostrarDados();
            System.out.println("");
        }
    }

    public void mostrarDados(){
        System.out.println("Titular da conta: "+ this.titular);
        System.out.println("Número da conta: "+ this.numeroConta);
        System.out.println("Saldo da conta: "+ this.saldo);
    }

    public void depositar(double valor){
        if (valor < 0){
            System.out.println("Valor de deposito invalido!. Você não pode depositar um valor negativo.");
            return;
        }
        setSaldo(this.saldo + valor);
        System.out.println("Deposito efetuado com sucesso;");
        System.out.println("Saldo atual R$"+this.saldo);


    }

    public void sacar(double valor){
        if (valor < 0){
            System.out.println("Valor de saque invalido!. Você não pode sacar um valor negativo.");
            return;
        }

        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente!");
            return;
        }

        setSaldo(this.saldo - valor);
        System.out.println("Saque efetuado com sucesso");
        System.out.println("Saldo atual R$"+this.saldo);
    }

    public void transferir(double valor, ContaBancaria contaDestino){
        if (valor < 0){
            System.out.println("Valor de transferência inválido!. Você não pode transferir um valor negativo.");
            return;
        }

        if (valor > this.saldo) {
            System.out.println("Saldo insuficiente!");
            return;
        }

        setSaldo(this.saldo - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);

        System.out.println("Transferência efetuada com sucesso!");
        System.out.println("Saldo atual R$"+this.saldo);
    }



    public String getTitular() {
        return titular;
    }



    public void setTitular(String titular) {
        this.titular = titular;
    }



    public int getNumeroConta() {
        return numeroConta;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public static int getQtdContas() {
        return qtdContas;
    }


}
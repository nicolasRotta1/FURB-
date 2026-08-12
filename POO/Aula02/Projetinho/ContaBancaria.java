package Aula02.Projetinho;

public class ContaBancaria {
 
    private String titular;
    private int numeroConta;
    private double saldo;
    private static int qtdContas;


    public ContaBancaria() {
        qtdContas++;
    }


    public ContaBancaria(String titular) {
        qtdContas++;
        this.titular = titular;
        this.numeroConta = (int) (Math.random() * 10000);
        this.saldo = 0;
    }


    public static ContaBancaria cadastrarContaBancaria(){
        System.out.println("-- Para Criar sua conta Precisamos de alguns dados -- ");

        System.out.print("Nome de quem vai utilizar esta conta: ");
        String titular = App.sc.next();

        ContaBancaria conta = new ContaBancaria(titular);

        return conta;
        
    }


    public void menuConta(){

        while (true) {
        System.out.println("-----------Menu----------");

        mostrarDados();

        System.out.println("");
        System.out.println("");
        System.out.println("Escolha uma opção: ");
        System.out.println("(1) - Depositar -");
        System.out.println("(2) - Sacar - ");
        System.out.println("(3) - Sair - ");
        System.out.println("Opção: ");
        int opc = App.sc.nextInt();

        if (opc == 3) {
            System.out.println("Saindo da conta . . .");
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
            default:
                break;
        }

        
        }


    }


    public void mostrarDados(){
        System.out.println("Titular da conta: "+getTitular());
        System.out.println("Número da conta: "+ getNumeroConta());
        System.out.println("Saldo da conta: "+ getSaldo());
    }

    public void depositar(double valor){
        if (valor < 0){
            System.out.println("Valor de deposito invalido!. Você não pode depositar um valor negativo.");
            return;
        }
            setSaldo(this.saldo + valor);
            System.out.println("Deposito efetuado com sucesso;");
            System.out.println("Saldo atual R$"+this.getSaldo());

        
    }

    public void sacar(double valor){
        if (valor > this.getSaldo()) {  
            System.out.println("Saldo insuficiente!");
            return;
        }

        setSaldo(this.saldo - valor);
        System.out.println("Saque efetuado com sucesso");
        System.out.println("Saldo atual R$"+this.getSaldo());
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



    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
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

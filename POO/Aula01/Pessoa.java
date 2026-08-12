import java.util.Scanner;

public class Pessoa {
    String nome;
    double altura;
    double peso;

    
    public Pessoa(String nome, double altura, double peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
    }

    public Pessoa() {
    }

    public  double calcularImc(){
        return this.peso / (this.altura * this.altura);
    }

    public static Pessoa cadastrarPessoa(){
        Scanner sc = new Scanner(System.in);

        System.out.println("");

        System.out.print("Digite seu nome: ");
        String nome = sc.next();

        System.out.print("Digite Sua altura: ");
        double altura = sc.nextDouble();

        System.out.print("Digite seu peso: ");
        double peso = sc.nextDouble();

        System.out.println("");


        Pessoa pessoa = new Pessoa(nome,altura,peso);

        return pessoa;

        
    }

    public void mostrarDados(){
        
        System.out.println("Nome: "+this.nome);
        System.out.println("Altura: "+this.altura);
        System.out.println("Peso: "+this.peso);
        System.out.println("IMC: "+calcularImc());

    }
}

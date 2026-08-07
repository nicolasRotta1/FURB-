import java.util.ArrayList;
import java.util.Scanner;

public class App {
public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    System.out.println("----- CALCULADORA DE IMC ------");

    System.out.print("Deseja calcular o IMC de quantas pessoas?: ");
    int numero = sc.nextInt();

    ArrayList <Pessoa> pessoas = new ArrayList<>();

    for(int i=0; i<numero; i++){
       
        System.out.println("-----------------------------------------------------------");
        pessoas.add(cadastrarPessoa());
    }
    
    System.out.println("<------------------------Resultados----------------------->");


    for(int i=1; i<= numero; i++){
        System.out.println("");
        
        System.out.println("Pessoa "+i+": ");
        System.out.println("Nome: "+pessoas.get(i-1).nome);
        System.out.println("Altura: "+pessoas.get(i-1).altura);
        System.out.println("Peso: "+pessoas.get(i-1).peso);
        System.out.println("IMC: "+pessoas.get(i-1).calcularImc());
        System.out.println("");

        System.out.println("-----------------------------------------------------------");
    }




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
}

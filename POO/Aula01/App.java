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
        
        System.out.println("------- Pessoa "+(i+1)+" --------");
        pessoas.add(cadastrarPessoa());
    }
    
    System.out.println("<------------------------Resultados----------------------->");

    
    for(int i=pessoas.size(); i > 0; i--){
        System.out.println("");
        
        System.out.println("------- Pessoa "+(i)+" --------");

        pessoas.get(i-1).mostrarDados();
        
        System.out.println("");

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

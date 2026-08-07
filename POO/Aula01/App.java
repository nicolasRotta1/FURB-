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
        pessoas.add(Pessoa.cadastrarPessoa());
    }
    
    System.out.println("<------------------------Resultados----------------------->");

    
    for(int i=pessoas.size(); i > 0; i--){
        System.out.println("");
        
        System.out.println("------- Pessoa "+(i)+" --------");

        pessoas.get(i-1).mostrarDados();

        System.out.println("");

    }

    



}
}

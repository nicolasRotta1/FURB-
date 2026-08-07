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

    public void mostrarDados(){
        
        System.out.println("Nome: "+this.nome);
        System.out.println("Altura: "+this.altura);
        System.out.println("Peso: "+this.peso);
        System.out.println("IMC: "+calcularImc());
        
    }
}

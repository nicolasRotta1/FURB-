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
        return peso / (altura * altura);
    }

}

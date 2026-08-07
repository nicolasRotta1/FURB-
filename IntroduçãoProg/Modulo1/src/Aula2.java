import java.util.Scanner;

public class Aula2 {
 public static void main(String[] args) {
    try{
    Scanner sc = new Scanner(System.in);
    System.out.println("CALCULADORA SUPER MEGA POTENTE");
    System.out.println("QUE OPERAÇÂO DESEJA FAZER SENHOR?\n(1) - Somar\n(2) - Subtrair\n(3) - Multiplicação\n(4) - Divisão");
    int operacao = sc.nextInt();

    System.out.println("Digite um número para a operação: ");
    double num1 = sc.nextDouble();

    System.out.println("Digite outro número: ");
    double num2 = sc.nextDouble();

    Calculadora calculator = new Calculadora();
    
    System.out.println(calculator.calcular(num1, num2, operacao));
    sc.close();
    }catch(Exception e){
        System.out.println("moio, bulo" + e);
    }
    

 }

 public static class Calculadora{

    public Calculadora(){
        
    }
    
    public double calcular(double num, double num2, int operacao){
        
        double resultado = 0;
        switch(operacao){
            case 1:
                resultado = num + num2;
                break;
            case 2:
                resultado = num - num2;  
                break;
            case 3:
                resultado = num * num2;
                break;
            case 4:
                resultado = num / num2;
                break;
            default: 
                System.out.println("moio");
        }

        return resultado;
    }
 }
}



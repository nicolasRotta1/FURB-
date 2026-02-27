import java.util.InputMismatchException;
import java.util.Scanner;

public class MediaPonderada {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_VERMELHO = "\u001B[31m";
    public static final String ANSI_VERDE = "\u001B[32m";
    public static final String ANSI_AMARELO = "\u001B[33m";


    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    float prova1 = 0;
    float prova2 = 0;
    float trabalhoFinal = 0;

    float trabalhoUnidade1 = 0;
    float trabalhoUnidade2 = 0;
    float trabalhoUnidade3 = 0;
    float trabalhoUnidade4 = 0;
    float trabalhoUnidade5 = 0;
    float trabalhoUnidade6 = 0;
    float trabalhoUnidade7 = 0;

    try{
    System.out.println("Digite a nota da prova 1: ");
    prova1 = sc.nextFloat();

    System.out.println("Digite a nota da prova 2: ");
    prova2 = sc.nextFloat();

    System.out.println("Digite a nota do trabalho final: ");
    trabalhoFinal = sc.nextFloat();

    System.out.println("Digite a nota do trabalho da Unidade 1");
    trabalhoUnidade1 = sc.nextFloat();
    
    System.out.println("Digite a nota do trabalho da Unidade 2");
    trabalhoUnidade2 = sc.nextFloat();
    
    System.out.println("Digite a nota do trabalho da Unidade 3");
    trabalhoUnidade3 = sc.nextFloat();
    
    System.out.println("Digite a nota do trabalho da Unidade 4");
    trabalhoUnidade4 = sc.nextFloat();

    System.out.println("Digite a nota do trabalho da Unidade 5");
    trabalhoUnidade5 = sc.nextFloat();

    System.out.println("Digite a nota do trabalho da Unidade 6");
    trabalhoUnidade6 = sc.nextFloat();

    System.out.println("Digite a nota do trabalho da Unidade 7");
    trabalhoUnidade7 = sc.nextFloat();

    float mediaDosTrabalhos = ((trabalhoUnidade1 + trabalhoUnidade2 + trabalhoUnidade3 + trabalhoUnidade4 + trabalhoUnidade5 + trabalhoUnidade6 + trabalhoUnidade7) / 7);

    float mediaFinalPonderada = (((prova1 * 0.3f) + (prova2 * 0.2f) + (trabalhoFinal * 0.3f) + (mediaDosTrabalhos * 0.2f))) ;

    if (mediaFinalPonderada > 6) {
        System.out.println(ANSI_VERDE + "Você foi Aprovado sua média é " + mediaFinalPonderada + ANSI_RESET);   
    }else{
        System.out.println(ANSI_VERMELHO + "Você foi reprovado sua média é " + mediaFinalPonderada + ANSI_RESET); 
    }

    }catch(InputMismatchException e){
        System.out.println("Seu bobalhao: "+ e);
    }    
    
    sc.close();

    }

}

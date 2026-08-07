import java.util.Scanner;

public class questao2mochila {
    Scanner sc = new Scanner(System.in);
    public questao2mochila(){

    }


    public void adicionar(int[] espacos){
        if (!tacheio(espacos)) {
            for (int i = 0; i < espacos.length; i++) {
                if (espacos[i] == 0) {
                    System.out.println("Digite um número inteiro para adicionar na mochila: ");
                    espacos[i] = sc.nextInt();
                    break;
                }
            }
        }else{
            System.out.println("Sua mochila esta cheia!!");
        }

    }

    public void remover(int[] espacos){
        System.out.println("Digite um valor para remover: ");
        int valor = sc.nextInt();
        for (int i = 0; i < espacos.length; i++) {
            if (espacos[i] == valor) {
                espacos[i] = 0;
                System.out.println("Removido com sucesso do espaço "+i);

                for (int j = 0; j < espacos.length; j++) {

                    if (j == 9) {
                        break;
                    }
                    if (espacos[j] == 0) {
                        espacos[j] = espacos[j+1];
                        espacos[j+1] = 0;
                    }
                }
                break;
            }else if (i == 9) {
                System.out.println("Nenhuma ocorrencia deste número encontrada!!");
            }
        }
    }

    public void listar(int[] espacos){
        for (int i = 0; i < espacos.length; i++) {
            System.out.println("Espaço "+ i +": " + espacos[i]);
        }

    }

    

    public void ordenar(int[] espacos){
        for (int i = 0; i < espacos.length;) {

            if (i == 9) {
                break;
            }
            if (espacos[i]< espacos[i+1]) {
                int troca = espacos[i];
                espacos[i] = espacos[i+1];
                espacos[i+1] = troca;

                i = 0;
            }
            i++;
        }
        System.out.println("Sua nova Lista é: ");
        listar(espacos);
    }
    

    public void verificar(int[] espacos){
        System.out.println("Digite o valor que deseja verificar: ");
        int valor = sc.nextInt();

        for (int i = 0; i < espacos.length; i++) {
            if (espacos[i] == valor) {
                System.out.println("Este valor existe na sua mochila");
                break;
            }
            if (i == 9) {
                System.out.println("Este valor não existe na sua mochila!");
            }
        }
    }

    public boolean tacheio(int[] espacos){
            for (int i = 0; i < espacos.length; i++) {
                if (espacos[i] == 0) {
                    return false;
                }   
            }

        return true;
    }

}

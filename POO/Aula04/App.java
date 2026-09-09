import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        Professor andre = new Professor();
        andre.setNome("Andre");

        Professor joao = new Professor();
        joao.setNome("Joao");

        andre.setCoordenador(joao);

        joao.adicionarSubordinado(andre);

        for(Professor prof : joao.getSubordinados()) {
            System.out.println(prof.getNome());
        }

        System.out.println(andre.getCoordenador().getNome());
    }
}

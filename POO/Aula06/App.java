import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        Bateria bateria = new Bateria();
        Cachorro cachorro = new Cachorro();
        Gato gato = new Gato();
        Elefante elefante = new Elefante();
        Baixo baixo = new Baixo();
        Violao violao = new Violao();

        ArrayList<EmitirSom> faibarui = new ArrayList<>();

        faibarui.add(bateria);
        faibarui.add(cachorro);
        faibarui.add(gato);
        faibarui.add(elefante);
        faibarui.add(baixo);
        faibarui.add(violao);

        for (EmitirSom obj : faibarui) {
            System.out.println(obj.emitirSom());
        }
    }
}
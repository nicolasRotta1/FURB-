public class Gato implements Animal, TemSentimento {

    @Override
    public String emitirSom() {
        return "miau miau";
    }

    @Override
    public String getTipoSentimento() {
        return "curiosidade";
    }


    @Override
    public int getQuantidadePatas() {
        return 4;
    }

}

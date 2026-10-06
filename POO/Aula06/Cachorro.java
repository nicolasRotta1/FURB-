public class Cachorro implements Animal, TemSentimento {

    @Override
    public String emitirSom() {
        return "au au";
    }

    @Override
    public int getQuantidadePatas() {
        return 4;
    }

    @Override
    public String getTipoSentimento() {
        return "alegria";
    }
}



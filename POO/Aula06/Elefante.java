public class Elefante implements Animal, TemSentimento {

    @Override
    public String emitirSom() {
        return "pum pum";
    }

    @Override
    public int getQuantidadePatas() {
        return 4;
    }

    @Override
    public String getTipoSentimento() {
        return "tristeza";
    }
}


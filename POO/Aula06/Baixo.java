public class Baixo extends InstrumentosDeCorda implements Eletronico {

    public Baixo() {
        super(4);
    }

    @Override
    public String emitirSom() {
        return "bum bum";
    }

    @Override
    public boolean isEletronico() {
        return true;
    }

}

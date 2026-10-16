package rangoja;


public class Bebida extends ItemCardapio {
    private int volumeMl;
    private boolean alcoolica;

    public Bebida(String nome, double precoBase, int volumeMl, boolean alcoolica) {
        // super(...) chama o construtor da classe abstrata e valida o preço base.
        super(nome, precoBase);
        this.volumeMl = validarVolume(volumeMl);
        this.alcoolica = alcoolica;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }

    @Override
    public double calcularPreco() {
        if (alcoolica) {
            return getPrecoBase() + 3.0;
        }
        return getPrecoBase();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" (").append(volumeMl).append(" ml)");
        if (alcoolica) {
            sb.append(" [alcoólica]");
        }
        return sb.toString();
    }

    private int validarVolume(int volumeMl) {
        validarPositivo(volumeMl);
        return volumeMl;
    }
}

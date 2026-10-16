package rangoja;

import java.util.Locale;

public abstract class ItemCardapio {
    private String nome;
    private double precoBase;

    public ItemCardapio(String nome, double precoBase) {
        this.nome = validarNome(nome);
        setPrecoBase(precoBase);
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        validarPositivo(precoBase);
        this.precoBase = precoBase;
    }

    protected void validarPositivo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido: deve ser maior que zero.");
        }
    }

    public abstract double calcularPreco();

    public final double calcularPrecoComDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual inválido: informe um valor entre 0 e 100.");
        }
        return calcularPreco() * (1 - percentual / 100.0);
    }

    @Override
    public String toString() {
        return nome + " - R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", calcularPreco());
    }

    private String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do item do cardápio é obrigatório.");
        }
        return nome;
    }
}

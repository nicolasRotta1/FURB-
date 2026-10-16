package rangoja;

import java.util.Locale;
import java.util.Objects;

public class Cliente {
    private String nome;
    private String email;
    private int idade;
    private double saldoCashback;
    private Cliente indicadoPor;

    public Cliente(String nome, String email, int idade) {
        // encapsulamento: a validação da idade fica centralizada em setIdade.
        this.nome = validarTextoObrigatorio(nome, "nome");
        this.email = validarTextoObrigatorio(email, "email");
        this.idade = 0;
        this.saldoCashback = 0.0;
        this.indicadoPor = null;
        setIdade(idade);
    }

    public Cliente(String nome, String email, int idade, Cliente indicadoPor) {
        // this(...) reaproveita a lógica base do primeiro construtor.
        this(nome, email, idade);
        this.indicadoPor = validarIndicador(indicadoPor);
        if (indicadoPor != null) {
            indicadoPor.creditarCashback(10.0);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade inválida: informe um valor entre 0 e 120.");
        }
        this.idade = idade;
    }

    public double getSaldoCashback() {
        return saldoCashback;
    }

    public Cliente getIndicadoPor() {
        return indicadoPor;
    }

    public boolean isMaiorDeIdade() {
        return idade >= 18;
    }

    public void creditarCashback(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de cashback inválido: deve ser maior que zero.");
        }
        saldoCashback += valor;
    }

    public void usarCashback(double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor para uso de cashback inválido: deve ser maior que zero.");
        }
        if (valor > saldoCashback) {
            throw new SaldoInsuficienteException(saldoCashback, valor);
        }
        saldoCashback -= valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente outro = (Cliente) obj;
        return Objects.equals(this.email, outro.email)
                || (this.email != null && this.email.equalsIgnoreCase(outro.email));
    }
    
    // hashCode deve ser consistente com equals, usando email em caixa baixa para garantir consistência.
    @Override
    public int hashCode() {
        return Objects.hash(email != null ? email.toLowerCase(Locale.forLanguageTag("pt-BR")) : null);
    }

    @Override
    public String toString() {
        return nome + " " + email;
    }

    private static String validarTextoObrigatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " obrigatório.");
        }
        if ("email".equals(campo) && !valor.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido: deve conter '@'.");
        }
        return valor;
    }

    private Cliente validarIndicador(Cliente indicadoPor) {
        if (indicadoPor == null) {
            throw new IllegalArgumentException("Cliente indicador obrigatório.");
        }
        return indicadoPor;
    }
}

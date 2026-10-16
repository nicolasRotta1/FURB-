package rangoja;

import java.util.Locale;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(double saldo, double valorSolicitado) {
        super("Saldo insuficiente: disponível R$ "
                + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", saldo)
                + ", solicitado R$ "
                + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", valorSolicitado));
    }
}

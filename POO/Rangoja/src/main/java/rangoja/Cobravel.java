package rangoja;

import java.util.Locale;

public interface Cobravel {
    double calcularValorCobrar();

    default String gerarComprovante() {
        // Locale para formatar o valor monetário no padrão brasileiro.(fica mais fácil de ler e entender)
        return "Valor a cobrar: R$ " + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", calcularValorCobrar());
    }
}

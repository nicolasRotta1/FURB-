package rangoja;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CentralCobranca {
    private List<Cobravel> cobrancas;

    public CentralCobranca() {
        // associação: a central guarda qualquer objeto que implemente Cobravel.
        this.cobrancas = new ArrayList<>();
    }

    public void registrar(Cobravel cobranca) {
        if (cobranca == null) {
            throw new IllegalArgumentException("Cobrança obrigatória.");
        }
        cobrancas.add(cobranca);
    }

    public double calcularTotalAReceber() {
        double total = 0.0;
        for (Cobravel cobranca : cobrancas) {
            total += cobranca.calcularValorCobrar();
        }
        return total;
    }

    public int getQuantidadeCobrancas() {
        return cobrancas.size();
    }

    public String gerarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        for (Cobravel cobranca : cobrancas) {
            relatorio.append(cobranca.gerarComprovante()).append("\n");
        }
        relatorio.append("Total a receber: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", calcularTotalAReceber()));
        return relatorio.toString();
    }
}

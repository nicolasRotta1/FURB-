import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlanoFamiliaTest {

    @Test
    @DisplayName("PF01 - o preço por membro diminui conforme aumenta a quantidade de membros")
    public void precoPorMembro_diminuirComMaisMembros() {
        PlanoFamilia plano2 = new PlanoFamilia(2);
        PlanoFamilia plano4 = new PlanoFamilia(4);
        PlanoFamilia plano6 = new PlanoFamilia(6);

        assertTrue(plano2.getPrecoPorMembro() > plano4.getPrecoPorMembro());
        assertTrue(plano4.getPrecoPorMembro() > plano6.getPrecoPorMembro());
    }
}

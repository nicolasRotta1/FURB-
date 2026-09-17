import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

/**
 * Classe de teste da avaliação prática de POO - Turma 004.
 */
class ProdutoTest {

    // ===== EXEMPLO FORNECIDO PELO PROFESSOR =====
    @Test
    void deveAdicionarQuantidadeValida() {
        Produto p = new Produto(1, "Caneta");
        p.adicionar(50);
        assertEquals(50, p.getQuantidade());
    }

    // ===== IMPLEMENTE SEUS TESTES A PARTIR DAQUI (Questão 5) =====


    // ================== Adicionar =======================

    @DisplayName("PL1 - Caso 1 - Lança illegalArgumentException quando o parametro for 0")
    @Test 
    void adicionarDeveLancarIllegalArgumentQuando_For0(){
        Produto p = new Produto(1001, "sofa");
        
        assertThrows(IllegalArgumentException.class, () -> p.adicionar(0));
    }

    @DisplayName("PL1 - Caso 2 - Lança illegalArgumentException quando o parametro for negativo")
    @Test 
    void adicionarDeveLancarIllegalArgumentQuando_ForNegativo(){
        Produto p = new Produto(1001, "tv");
        
        assertThrows(IllegalArgumentException.class, () -> p.adicionar(-5));
    }

    @DisplayName("PL1 - Caso 3 - Verifica se a quantidade foi adicionada corretamente")
    @Test 
    void adicionarDeveAdicionarCorretamente(){
        Produto p = new Produto(1001, "poltrona");
        p.adicionar(10);
        assertEquals(10, p.getQuantidade());
    }


    // ================== Remover =================

    @DisplayName("PL2 - Caso 1 - Lança illegalStateException quando parametro for maior que o estoque")
    @Test 
    void removerDeveLancarIllegalStateQuando_qtdForMaiorQueEstoque(){
        Produto p = new Produto(1001, "armario", 9);
        
        assertThrows(IllegalStateException.class , () -> p.remover(10));
    }

    @DisplayName("PL2 - Caso 2 - Lança illegalArgumentException quando o parametro for negativo")
    @Test 
    void removerDeveLancarIllegalArgumentQuando_forNegativo(){
        Produto p = new Produto(1001, "cama");
        
        assertThrows(IllegalArgumentException.class , () -> p.remover(-5));
    }


    @DisplayName("PL2 - Caso 3 - Lança illegalArgumentException quando o parametro for 0")
    @Test 
    void removerDeveLancarIllegalArgumentQuando_for0(){
        Produto p = new Produto(1001, "foice");
        
        assertThrows(IllegalArgumentException.class , () -> p.remover(0));
    }


}

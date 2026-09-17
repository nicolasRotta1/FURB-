public class Produto {

    private int codigo;
    private String nome;
    private int quantidade;
    private static int totalProdutos;

    public Produto(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
        this.quantidade = 0;
        totalProdutos++;
    }

    public Produto(int codigo, String nome, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        validaQuantidade(quantidade);
        this.quantidade = quantidade;
        totalProdutos++;
    }


    public void adicionar(int qtd){
        if (qtd <= 0 ) {
            throw new IllegalArgumentException("Quantidade de entrada inválida!");
        }
        this.quantidade =+ qtd;
    }

    public void remover(int qtd){
        if (qtd <= 0 ) {
            throw new IllegalArgumentException("Quantidade de saida inválida!");
        }
        if (qtd > quantidade){
            throw new IllegalStateException("Estoque insuficiente");
        }

        this.quantidade =- qtd;

    }


    public void validaQuantidade(int quant){
        if(quant < 0){
            throw new IllegalArgumentException("Quantidade inicial inválida!");
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public static int getTotalProdutos() {
        return totalProdutos;
    }


    
    
}

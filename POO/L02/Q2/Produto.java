public class Produto {

    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto() {
    }


    public void vender(int quantidade){
        if(quantidade <= 0){
            System.out.println("Valor inválido!");
        }

        if (quantidade > this.estoque){
            System.out.println("Estoque insuficiente!");
            return;
        }

        this.estoque = this.estoque - quantidade;
        System.out.println(quantidade+" "+this.nome+" vendidos com sucesso.");
        System.out.println("Estoque atual: "+this.estoque);
    }

    public void repor(int quantidade){
        if(quantidade <= 0){
            System.out.println("Valor inválido!");
        }
        this.estoque = this.estoque + quantidade;
        System.out.println(quantidade+" "+this.nome+" repostos com sucesso.");
        System.out.println("Estoque atual: "+this.estoque);
    }


    public String getNome() {        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0){
            this.preco = preco;
        }else{
            System.out.println("O preço não pode ser negativo!");
        }

    }

    public int getEstoque() {
        return estoque;
    }

}

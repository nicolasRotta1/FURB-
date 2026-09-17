public class App {
    public static void main(String[] args) throws Exception {
        Produto p1 = new Produto(1000, "Almofada");
        Produto p2 = new Produto(1001, "cama");
        Produto p3 = new Produto(1003, "bola", 10);
        Produto p4 = new Produto(1004, "mesa", 5);

        p1.adicionar(5);
        p2.adicionar(3);
        p3.remover(2);
        p4.remover(3);

        try{
            p1.remover(-2);
        }catch(IllegalStateException | IllegalArgumentException e){
            System.out.println("Erro: "+e.getMessage());
        }

        System.out.println("Total Produtos: "+Produto.getTotalProdutos());
    }
}

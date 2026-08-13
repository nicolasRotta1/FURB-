

public class App {
public static void main(String[] args) {

    Produto produto1 = new Produto();
    produto1.setNome("Ténis");
    produto1.setPreco(149.99);
    produto1.repor(20);
    System.out.println(produto1.getEstoque());

    produto1.vender(19);
    produto1.setPreco(-1);
    produto1.vender(2);
    }
}

public class Estacionamento {
 
    public static void main(String[] args) {

        Carro[] carros = new Carro[15];
        try{
        Carro carro1 = new Carro("Fusca", "ABC-1234", 10, 30);
        Carro carro2 = new Carro("Civic", "XYZ-5678", 12, 45);
        Carro carro3 = new Carro(null, "DEF-9012", 9, 5);

        carros[0] = carro1;
        carros[1] = carro2;
        carros[2] = carro3;
        }catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar o carro: " + e.getMessage());
        }
        for (int i = 0; i < carros.length; i++) {
        try{
            
            System.out.println("Modelo: " + carros[i].getModelo() + ", Placa: " + carros[i].getPlaca()
            + ", Minuto: " + carros[i].getMinuto() + ", Segundo: " + carros[i].getSegundo());
        } catch (NullPointerException e) {
            System.out.println("Vaga "+i+" Está vazia");
    }
}

    }
}

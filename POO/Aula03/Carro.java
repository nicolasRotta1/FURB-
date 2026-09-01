public class Carro {

    private String modelo;
    private String placa;
    private int minuto;
    private int segundo;

    public Carro(String modelo, String placa, int minuto, int segundo) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("O modelo do carro não pode ser nulo ou vazio.");
        }
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa do carro não pode ser nula ou vazia.");
        }
        
        this.modelo = modelo;
        this.placa = placa;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getSegundo() {
        return segundo;
    }

    public void setSegundo(int segundo) {
        this.segundo = segundo;
    }

    
}

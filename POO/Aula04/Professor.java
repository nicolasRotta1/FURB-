import java.util.ArrayList;

public class Professor {

    private String nome;
    private Professor coordenador;
    private ArrayList<Professor> subordinados = new ArrayList<>();

    public Professor() {

    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Professor getCoordenador() {
        return coordenador;
    }
    public void setCoordenador(Professor coordenador) {
        this.coordenador = coordenador;
    }

    public ArrayList<Professor> getSubordinados() {
        return subordinados;
    }


    public void adicionarSubordinado(Professor subordinado) {
        this.subordinados.add(subordinado);
        subordinado.setCoordenador(this);
    }

    
}

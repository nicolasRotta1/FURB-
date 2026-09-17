import java.util.ArrayList;

public class Usuario {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String email;
    private ArrayList<Usuario> seguindo;

    public Usuario(String nome, String email) {
        validarNome(nome);
        validarEmail(email);

        this.id = proximoId++;
        this.nome = nome.trim();
        this.email = email.trim();
        this.seguindo = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Valida nome null e blank
    public void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido: o nome do usuário não pode ser nulo, vazio ou composto apenas por espaços.");
        }
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

    // Verifica campo null, campo blank(so espacos)
    // e verifica se email e escrito de forma valida
    public void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email inválido: o email do usuário não pode ser nulo ou vazio.");
        }

        if (!email.contains("@") || email.startsWith("@") || email.endsWith("@")) {
            throw new IllegalArgumentException("Email inválido: o email deve conter '@' e não pode começar ou terminar com '@'.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validarEmail(email);
        this.email = email;
    }

    public boolean seguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido: o usuário a seguir não pode ser nulo.");
        }

        if (outro == this) {
            throw new IllegalArgumentException("Usuário inválido: um usuário não pode seguir a si mesmo.");
        }

        if (seguindo.contains(outro)) {
            return false;
        }

        seguindo.add(outro);
        return true;
    }

    public boolean deixarDeSeguir(Usuario outro) {
        if (outro == null) {
            throw new IllegalArgumentException("Usuário inválido: o usuário informado não pode ser nulo.");
        }

        return seguindo.remove(outro);
    }

    public ArrayList<Usuario> getSeguindo() {
        return new ArrayList<>(seguindo);
    }

    public int getQuantidadeSeguindo() {
        return seguindo.size();
    }
}

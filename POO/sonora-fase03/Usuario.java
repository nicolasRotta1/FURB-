public class Usuario {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private String email;

    public Usuario(String nome, String email) {
        validarNome(nome);
        validarEmail(email);

        this.id = proximoId++;
        this.nome = nome.trim();
        this.email = email.trim();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido: o nome do usuário não pode ser nulo, vazio ou composto apenas por espaços.");
        }
    }

    public void setNome(String nome) {
        validarNome(nome);
        this.nome = nome;
    }

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


}

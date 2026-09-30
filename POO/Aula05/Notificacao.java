public abstract class Notificacao {

    private String titulo;
    private String destinatario;

    public Notificacao(String titulo, String destinatario) {
        this.titulo = titulo;
        this.destinatario = destinatario;
    }

    public abstract void dispararNotificacao();

    public String getTitulo() {
        return titulo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    
}

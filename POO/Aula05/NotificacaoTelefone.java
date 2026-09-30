public abstract class NotificacaoTelefone extends Notificacao {
    private String numeroTelefone;

    public NotificacaoTelefone(String titulo, String destinatario, String numeroTelefone) {
        super(titulo, destinatario);
        this.numeroTelefone = numeroTelefone;
    }
    
    

}

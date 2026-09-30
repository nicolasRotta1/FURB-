public class NotificacaoWPP extends NotificacaoTelefone {

    private String usuario;

    public NotificacaoWPP(String titulo, String destinatario, String numeroTelefone, String usuario) {
        super(titulo, destinatario, numeroTelefone);
        this.usuario = usuario;
    }




    @Override
    public void dispararNotificacao() {
        System.out.println("Envio de notificacao via WhatsApp");
    }

}

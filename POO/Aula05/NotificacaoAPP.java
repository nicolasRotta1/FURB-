public class NotificacaoAPP extends Notificacao {

    

    public NotificacaoAPP(String titulo, String destinatario) {
        super(titulo, destinatario);
    }

    @Override
    public void dispararNotificacao() {
        System.out.println("Envio de notificacao via APP");
    }

}

public class NotificacaoSms extends NotificacaoTelefone {

    
    public NotificacaoSms(String titulo, String destinatario, String numeroTelefone) {
        super(titulo, destinatario, numeroTelefone);
    }

    @Override
    public void dispararNotificacao() {
        System.out.println("Envio de notificacao via SMS");
    }
}

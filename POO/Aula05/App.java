public class App {
    public static void main(String[] args) {

        // Classe anônima para instanciar a classe abstrata Notificacao
        // não esta instanciada diretamente, mas sim através de uma classe anônima que implementa o método abstrato dispararNotificacao()
        // basicamente cria uma classe sem nome que estende Notificacao e implementa o método dispararNotificacao()
        Notificacao not = new Notificacao("um", "dois") {
            @Override
            public void dispararNotificacao() {
                System.out.println("Envio de notificacao via Notificacao");
            }
        };
        NotificacaoEmail email = new NotificacaoEmail("Título", "Destinatário", "email@example.com", "Assunto", "Remetente");
        NotificacaoSms sms = new NotificacaoSms("Título", "Destinatário", "123456789");
        NotificacaoWPP wpp = new NotificacaoWPP("Título", "Destinatário", "123456789", "Usuário");

        email.dispararNotificacao();
        sms.dispararNotificacao();
        wpp.dispararNotificacao();
        not.dispararNotificacao();
    }
}

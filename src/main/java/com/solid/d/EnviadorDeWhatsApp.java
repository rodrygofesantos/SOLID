package com.solid.d;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO D — Inversão de Dependência                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE é mais um módulo de BAIXO NÍVEL.
 * Ela sabe COMO enviar uma mensagem pelo WhatsApp.
 *
 * ► O que foi preciso fazer para adicionar este novo canal?
 * ---------------------------------------------------------
 *   1. Criar este arquivo (EnviadorDeWhatsApp.java).    ← único passo!
 *   2. Usá-lo no ponto de montagem (Main.java).
 *
 *   NÃO foi preciso:
 *     ✗ Abrir ServicoDeNotificacao.java
 *     ✗ Abrir EnviadorDeMensagem.java
 *     ✗ Adicionar "if (canal == WHATSAPP)" em qualquer lugar
 *     ✗ Modificar EnviadorDeEmail.java ou EnviadorDeSms.java
 *
 *   ServicoDeNotificacao continua 100% intacto e funcionando.
 *   Isso é a INVERSÃO DE DEPENDÊNCIA em ação!
 *
 * ► Relembrando o diagrama do princípio D:
 * -----------------------------------------
 *
 *   ServicoDeNotificacao ──depende de──► EnviadorDeMensagem (interface)
 *                                               ▲
 *                              ┌────────────────┼────────────────┐
 *                         implementa       implementa       implementa
 *                              │                │                │
 *                     EnviadorDeEmail   EnviadorDeSms   EnviadorDeWhatsApp
 *
 *   O alto nível (Serviço) aponta para o centro (interface).
 *   Os detalhes (Email, SMS, WhatsApp) também apontam para o centro.
 *   Ninguém depende diretamente de ninguém — dependem da abstração.
 *   Isso é a "inversão": normalmente o alto nível dependeria do baixo nível.
 *
 * ► Por que "implements EnviadorDeMensagem"?
 * ------------------------------------------
 * Esta classe assina o contrato da interface EnviadorDeMensagem.
 * Isso garante que ela tem os métodos:
 *   - enviar(String destinatario, String mensagem)
 *   - nomeDoCanal()
 *
 * Ao assinar o contrato, o Java permite usar EnviadorDeWhatsApp em
 * qualquer lugar que espera um EnviadorDeMensagem — incluindo o
 * construtor de ServicoDeNotificacao — sem nenhuma alteração lá.
 *
 * ► Exemplo de uso (no Main.java ou em qualquer ponto de montagem):
 *
 *   ServicoDeNotificacao porWhatsApp =
 *       new ServicoDeNotificacao(new EnviadorDeWhatsApp("+55 31 99999-1234"));
 *
 *   porWhatsApp.enviarConfirmacaoDePedido("+55 31 99999-1234", "003");
 *   // ServicoDeNotificacao não sabe que é WhatsApp — só sabe chamar enviar()
 */
public class EnviadorDeWhatsApp implements EnviadorDeMensagem {

    /*
     * Número do remetente (simulado): identifica de qual conta WhatsApp
     * as mensagens serão disparadas.
     *
     * ► Por que armazenar o remetente?
     * ---------------------------------
     * Em um sistema real, a API do WhatsApp Business exige saber qual
     * número está enviando. Armazenamos aqui para simular esse detalhe
     * técnico — algo que o ServicoDeNotificacao não precisa conhecer.
     * (Isso reforça o princípio D: detalhes ficam no baixo nível.)
     */
    private final String numeroRemetente;

    /**
     * Cria o enviador de WhatsApp com o número de origem das mensagens.
     *
     * @param numeroRemetente número do WhatsApp Business remetente
     *                        (ex: "+55 31 98000-0000")
     */
    public EnviadorDeWhatsApp(String numeroRemetente) {
        this.numeroRemetente = numeroRemetente;
    }

    /**
     * Simula o envio de uma mensagem via WhatsApp.
     *
     * Em um sistema real, aqui estaria a chamada à API do
     * WhatsApp Business (ex: Twilio, Meta Cloud API).
     * Para o tutorial, apenas imprimimos o que seria enviado.
     *
     * @param destinatario número do WhatsApp do destinatário
     * @param mensagem     texto da mensagem
     */
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.printf("  [WhatsApp] De:   %-30s%n", numeroRemetente);
        System.out.printf("  [WhatsApp] Para: %-30s%n", destinatario);
        System.out.printf("  [WhatsApp] 💬 %s%n", mensagem);
    }

    /**
     * Nome do canal — usado por ServicoDeNotificacao para exibir logs.
     *
     * ServicoDeNotificacao chama enviador.nomeDoCanal() sem saber qual
     * implementação está por trás. Aqui retornamos "WhatsApp".
     */
    @Override
    public String nomeDoCanal() {
        return "WhatsApp";
    }
}

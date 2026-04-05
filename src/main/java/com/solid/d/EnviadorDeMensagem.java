package com.solid.d;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO D — Inversão de Dependência (Dependency Inversion)      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ► O QUE É O PRINCÍPIO DA INVERSÃO DE DEPENDÊNCIA?
 * ---------------------------------------------------
 * Duas regras:
 *   1. Módulos de ALTO NÍVEL não devem depender de módulos de BAIXO NÍVEL.
 *      Ambos devem depender de ABSTRAÇÕES.
 *   2. Abstrações não devem depender de detalhes.
 *      Detalhes devem depender de abstrações.
 *
 * ► O que são módulos de alto e baixo nível?
 * -------------------------------------------
 * ALTO NÍVEL = lógica de negócio (o "o quê fazer")
 *   Ex: ServicoDeNotificacao — decide QUANDO enviar uma notificação.
 *
 * BAIXO NÍVEL = detalhes técnicos (o "como fazer")
 *   Ex: EnviadorDeEmail — sabe COMO enviar um email.
 *   Ex: EnviadorDeSms  — sabe COMO enviar um SMS.
 *
 * ► O problema sem este princípio:
 * ----------------------------------
 *   class ServicoDeNotificacao {
 *       private EnviadorDeEmail email = new EnviadorDeEmail(); // ← hard-coded!
 *       // Agora é IMPOSSÍVEL usar SMS sem modificar esta classe.
 *       // E para testar, você é obrigado a ter um servidor de email.
 *   }
 *
 * ► A solução com este princípio:
 * --------------------------------
 *   class ServicoDeNotificacao {
 *       private EnviadorDeMensagem enviador; // ← depende da abstração!
 *       // Pode receber Email, SMS, WhatsApp, Push... sem mudar uma linha.
 *   }
 *
 * ESTA INTERFACE é a ABSTRAÇÃO que une alto e baixo nível.
 */
public interface EnviadorDeMensagem {

    /**
     * Envia uma mensagem para o destinatário.
     *
     * @param destinatario endereço/número do destinatário
     *                     (email: "joao@exemplo.com" | SMS: "+55 11 9xxxx-xxxx")
     * @param mensagem     conteúdo da mensagem
     */
    void enviar(String destinatario, String mensagem);

    /**
     * Retorna o nome do canal de comunicação (para exibição em logs).
     * Ex: "E-mail", "SMS", "WhatsApp"
     */
    String nomeDoCanal();
}

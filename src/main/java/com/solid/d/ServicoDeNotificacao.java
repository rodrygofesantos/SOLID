package com.solid.d;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO D — Inversão de Dependência                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE é o módulo de ALTO NÍVEL.
 * Ela contém a lógica de negócio: quando enviar e o quê enviar.
 * Ela NÃO SABE (e NÃO QUER SABER) se é por e-mail, SMS ou WhatsApp.
 *
 * ► O que é INJEÇÃO DE DEPENDÊNCIA pelo construtor?
 * ---------------------------------------------------
 * A dependência (enviador) não é criada aqui dentro.
 * Ela é INJETADA (passada de fora) pelo construtor:
 *
 *   ServicoDeNotificacao s = new ServicoDeNotificacao(new EnviadorDeEmail());
 *
 * Vantagens:
 *   1. Fácil de trocar o canal (email → sms → push) sem tocar nesta classe
 *   2. Fácil de testar: passe um enviador falso (mock) nos testes
 *   3. Segue o Princípio D: depende da abstração, não do concreto
 *
 * ► Resumo do princípio D:
 * -------------------------
 *   EnviadorDeMensagem = ABSTRAÇÃO (interface)
 *   EnviadorDeEmail    = DETALHE que implementa a abstração
 *   EnviadorDeSms      = DETALHE que implementa a abstração
 *   ServicoDeNotificacao = ALTO NÍVEL que usa a abstração
 *
 *   Alto nível ──depende de──► Abstração ◄──implementa── Detalhes
 *   (nunca: Alto nível ──depende de──► Detalhes)
 */
public class ServicoDeNotificacao {

    // Dependência da ABSTRAÇÃO — nunca de EnviadorDeEmail ou EnviadorDeSms diretamente
    private final EnviadorDeMensagem enviador;

    /**
     * Injeção de dependência pelo construtor.
     * Quem cria o ServicoDeNotificacao decide qual canal usar.
     */
    public ServicoDeNotificacao(EnviadorDeMensagem enviador) {
        this.enviador = enviador;
    }

    /**
     * Envia uma confirmação de pedido ao cliente.
     *
     * @param destinatario e-mail ou telefone do cliente
     * @param numeroPedido número do pedido confirmado
     */
    public void enviarConfirmacaoDePedido(String destinatario, String numeroPedido) {
        String mensagem = "Seu pedido nº " + numeroPedido + " foi confirmado! Obrigado pela compra.";
        System.out.printf("  [Notificação] Enviando via %s...%n", enviador.nomeDoCanal());
        enviador.enviar(destinatario, mensagem);
    }

    /**
     * Envia uma atualização de envio ao cliente.
     *
     * @param destinatario  e-mail ou telefone do cliente
     * @param numeroPedido  número do pedido
     * @param codigoRastreio código de rastreamento dos Correios
     */
    public void enviarAtualizacaoDeEnvio(String destinatario, String numeroPedido, String codigoRastreio) {
        String mensagem = "Pedido nº " + numeroPedido + " enviado! Rastreie: " + codigoRastreio;
        System.out.printf("  [Notificação] Enviando via %s...%n", enviador.nomeDoCanal());
        enviador.enviar(destinatario, mensagem);
    }
}

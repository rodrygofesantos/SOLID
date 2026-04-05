package com.solid.d;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO D — Inversão de Dependência                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE é outro módulo de BAIXO NÍVEL.
 * Ela sabe COMO enviar um SMS.
 *
 * ► A beleza do princípio D:
 * ---------------------------
 * Para trocar de e-mail para SMS no sistema de notificações,
 * NÃO PRECISAMOS modificar o ServicoDeNotificacao.
 *
 * Basta, no Main.java (quem "monta" o sistema), trocar:
 *   new ServicoDeNotificacao(new EnviadorDeEmail())
 * por:
 *   new ServicoDeNotificacao(new EnviadorDeSms())
 *
 * Uma linha mudada no ponto de montagem — e o sistema inteiro
 * passa a usar SMS. Zero risco de quebrar a lógica de negócio.
 *
 * ► Isso também facilita os TESTES:
 *   Para testar o ServicoDeNotificacao sem enviar SMS de verdade,
 *   podemos criar um "EnviadorFalso" que só grava o que seria enviado.
 *   Isso é chamado de MOCK — muito usado em testes automatizados.
 */
public class EnviadorDeSms implements EnviadorDeMensagem {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.printf("  [SMS]     Para: %-30s%n", destinatario);
        System.out.printf("  [SMS]     Texto: %s%n", mensagem);
    }

    @Override
    public String nomeDoCanal() {
        return "SMS";
    }
}

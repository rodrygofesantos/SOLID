package com.solid.d;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO D — Inversão de Dependência                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE é um módulo de BAIXO NÍVEL (detalhe técnico).
 * Ela sabe COMO enviar um e-mail.
 *
 * ► Como ela se relaciona com o princípio D?
 * -------------------------------------------
 * EnviadorDeEmail DEPENDE DA ABSTRAÇÃO (implements EnviadorDeMensagem).
 * Ou seja: o detalhe (e-mail) depende da abstração — não o contrário.
 * Isso é a "inversão" de que o princípio fala.
 *
 * Fluxo tradicional (ruim):
 *   ServicoDeNotificacao → depende de → EnviadorDeEmail
 *   (alto nível dependendo de baixo nível — acoplamento rígido)
 *
 * Fluxo com inversão (correto):
 *   ServicoDeNotificacao → depende de → EnviadorDeMensagem (abstração)
 *   EnviadorDeEmail      → depende de → EnviadorDeMensagem (abstração)
 *   (ambos dependem da abstração — o acoplamento é flexível)
 *
 * ► ServicoDeNotificacao nunca importa esta classe pelo nome.
 *   Nunca há "import com.solid.d.EnviadorDeEmail" lá.
 *   Isso é o desacoplamento em ação.
 */
public class EnviadorDeEmail implements EnviadorDeMensagem {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.printf("  [E-mail]  Para: %-30s%n", destinatario);
        System.out.printf("  [E-mail]  Assunto: Notificação do Sistema%n");
        System.out.printf("  [E-mail]  Corpo: %s%n", mensagem);
    }

    @Override
    public String nomeDoCanal() {
        return "E-mail";
    }
}

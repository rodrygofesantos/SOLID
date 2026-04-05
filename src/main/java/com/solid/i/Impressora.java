package com.solid.i;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO I — Segregação de Interface                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa uma impressora simples de escritório.
 *
 * ► O que ela implementa?
 * ------------------------
 * Apenas "Imprimivel" — porque ela SÓ IMPRIME.
 * Ela não sabe salvar arquivos. Não tem scanner.
 * O código é honesto sobre o que ela faz.
 *
 * ► Sem o princípio I (comparação):
 * ------------------------------------
 *   class Impressora implements MaquinaDeEscritorio {
 *       void imprimir(String c) { System.out.println(c); }    // OK
 *       void salvar(...)        { throw new RuntimeException("Não tenho essa função!"); }
 *       void escanear(...)      { throw new RuntimeException("Não tenho essa função!"); }
 *   }
 *   // A Impressora "mentia" para quem a usava via interface.
 *
 * ► Com o princípio I (este código):
 *   class Impressora implements Imprimivel {
 *       void imprimir(String c) { ... } // só isso — perfeito!
 *   }
 *   // Quem recebe uma Imprimivel sabe exatamente o que ela pode fazer.
 */
public class Impressora implements Imprimivel {

    private final String nomeImpressora;

    public Impressora(String nomeImpressora) {
        this.nomeImpressora = nomeImpressora;
    }

    @Override
    public void imprimir(String conteudo) {
        System.out.printf("  [%s]  Imprimindo: \"%s\"%n", nomeImpressora, conteudo);
    }
}

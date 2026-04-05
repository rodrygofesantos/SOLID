package com.solid.i;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO I — Segregação de Interface (Interface Segregation)     ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ► O QUE É O PRINCÍPIO DA SEGREGAÇÃO DE INTERFACE?
 * ---------------------------------------------------
 * "Nenhuma classe deve ser forçada a implementar métodos que ela
 * não vai usar."
 *
 * ► Problema sem este princípio:
 * --------------------------------
 * Imagine uma interface gigante:
 *
 *   interface MaquinaDeEscritorio {
 *       void imprimir(String conteudo);
 *       void salvar(String conteudo, String destino);
 *       void escanear(String documento);
 *   }
 *
 * Uma impressora simples (que só imprime) seria FORÇADA a implementar
 * salvar() e escanear(), mesmo sem ter essas funções:
 *
 *   class Impressora implements MaquinaDeEscritorio {
 *       void imprimir(String c) { ... } // OK!
 *       void salvar(...) { throw new UnsupportedOperationException(); } // Mentira!
 *       void escanear(...) { throw new UnsupportedOperationException(); } // Mentira!
 *   }
 *
 * Isso é enganoso: a interface promete funcionalidades que a classe
 * não tem. Se alguém chamar impressora.escanear(), recebe uma exceção
 * em tempo de execução — um bug difícil de encontrar!
 *
 * ► Solução: dividir em interfaces pequenas e focadas.
 *   Imprimivel → só quem imprime implementa
 *   Salvavel   → só quem salva implementa
 *   Escaneavel → só quem escaneia implementa
 *
 * Uma impressora simples implementa APENAS Imprimivel — honesto e limpo.
 * Um dispositivo multifuncional implementa as três — também honesto.
 *
 * ESTA INTERFACE define o contrato de impressão.
 */
public interface Imprimivel {

    /**
     * Imprime o conteúdo fornecido.
     *
     * @param conteudo o texto ou documento a ser impresso
     */
    void imprimir(String conteudo);
}

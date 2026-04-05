package com.solid.i;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO I — Segregação de Interface                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA INTERFACE define o contrato de salvamento/cópia.
 *
 * Por ser uma interface separada, APENAS dispositivos que realmente
 * suportam salvar precisam implementá-la.
 *
 * Uma impressora simples nunca precisará saber que esta interface existe.
 */
public interface Salvavel {

    /**
     * Salva (copia) o conteúdo em um destino específico.
     *
     * @param conteudo  o texto ou documento a ser salvo
     * @param destino   onde salvar (ex: "/arquivos/notas/", "nuvem://pasta/")
     */
    void salvar(String conteudo, String destino);
}

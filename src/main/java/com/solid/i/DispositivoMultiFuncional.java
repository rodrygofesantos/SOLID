package com.solid.i;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO I — Segregação de Interface                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa uma impressora multifuncional (imprime + salva + escaneia).
 *
 * ► Por que implementa as três interfaces?
 * -----------------------------------------
 * Porque ela GENUINAMENTE suporta as três operações.
 * Não é uma mentira — é uma escolha honesta.
 *
 * ► Uma classe pode implementar várias interfaces!
 * --------------------------------------------------
 * Em Java, uma classe pode herdar apenas UMA superclasse (extends),
 * mas pode implementar MÚLTIPLAS interfaces (implements A, B, C).
 *
 * Isso é diferente de herança múltipla — cada interface define um
 * "papel" que a classe desempenha.
 *
 * ► Polimorfismo com interfaces segregadas:
 * ------------------------------------------
 * DispositivoMultiFuncional mfd = new DispositivoMultiFuncional("Canon");
 *
 * // Visto como Imprimivel: só expõe imprimir()
 * Imprimivel i = mfd;
 * i.imprimir("Relatório");   // OK
 * // i.salvar(...)           ← não existe nesta visão — menos acoplamento!
 *
 * // Visto como Escaneavel: só expõe escanear()
 * Escaneavel e = mfd;
 * e.escanear("contrato.pdf");
 *
 * O mesmo objeto, visto por lentes diferentes — isso é polimorfismo!
 */
public class DispositivoMultiFuncional implements Imprimivel, Salvavel, Escaneavel {

    private final String nomeDispositivo;

    public DispositivoMultiFuncional(String nomeDispositivo) {
        this.nomeDispositivo = nomeDispositivo;
    }

    @Override
    public void imprimir(String conteudo) {
        System.out.printf("  [%s]  Imprimindo : \"%s\"%n", nomeDispositivo, conteudo);
    }

    @Override
    public void salvar(String conteudo, String destino) {
        System.out.printf("  [%s]  Salvando   : \"%s\"  →  %s%n",
                nomeDispositivo, conteudo, destino);
    }

    @Override
    public String escanear(String nomeDocumento) {
        String resultado = "ESCANEADO[" + nomeDocumento + "]";
        System.out.printf("  [%s]  Escaneando : \"%s\"  →  \"%s\"%n",
                nomeDispositivo, nomeDocumento, resultado);
        return resultado;
    }
}

package com.solid.i;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO I — Segregação de Interface                             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA INTERFACE define o contrato de escaneamento.
 *
 * Apenas dispositivos que possuem scanner físico precisam implementar
 * esta interface. Interfaces pequenas e focadas = código mais honesto
 * e mais fácil de manter.
 *
 * ► Vantagem de interfaces separadas para o cliente:
 * ---------------------------------------------------
 * Um método que só precisa escanear documentos pode receber
 * um "Escaneavel" como parâmetro — não precisa conhecer nem
 * imprimir nem salvar. Acoplamento mínimo!
 *
 *   void processarDocumento(Escaneavel scanner) {
 *       String conteudo = scanner.escanear("contrato.pdf");
 *       // ...
 *   }
 */
public interface Escaneavel {

    /**
     * Escaneia o documento indicado e retorna seu conteúdo como texto.
     *
     * @param nomeDocumento nome ou caminho do documento a escanear
     * @return conteúdo escaneado em formato de texto
     */
    String escanear(String nomeDocumento);
}

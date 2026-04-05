package com.solid.s;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO S — Responsabilidade Única                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE tem responsabilidade: FORMATAR e EMITIR a nota fiscal.
 *
 * ► Por que separar a impressão do resto?
 * ----------------------------------------
 * Imagine que o layout da nota fiscal muda: o cliente quer adicionar
 * um logo, mudar o formato do valor, incluir o CNPJ da empresa...
 *
 * Com o Princípio da Responsabilidade Única, APENAS esta classe muda.
 * A classe Pedido (dados), o ServicoDePedido (regras) e o
 * RepositorioDePedido (banco) continuam intocados.
 *
 * ► Resumo das 4 classes do princípio S:
 * ----------------------------------------
 *   Pedido              → "Eu guardo os dados do pedido."
 *   RepositorioDePedido → "Eu salvo e busco pedidos."
 *   ServicoDePedido     → "Eu valido e processo pedidos."
 *   EmissorDeNota       → "Eu formato e imprimo a nota fiscal."
 *
 * Cada classe tem UM motivo para mudar. Esse é o princípio S do SOLID.
 */
public class EmissorDeNota {

    /**
     * Formata e imprime a nota fiscal de um pedido no console.
     *
     * System.out.printf = imprime texto formatado.
     *   %s = substitui por uma String (texto)
     *   %d = substitui por um inteiro
     *   %.2f = substitui por um decimal com 2 casas
     *   %n = quebra de linha (equivalente ao "\n", mas multiplataforma)
     */
    public void emitir(Pedido pedido) {
        System.out.println("  [Emissor]     ┌─────────────── NOTA FISCAL ───────────────┐");
        System.out.printf ("  [Emissor]     │ Pedido Nº  : %-30s│%n", pedido.getNumeroPedido());
        System.out.printf ("  [Emissor]     │ Cliente    : %-30s│%n", pedido.getNomeCliente());
        System.out.printf ("  [Emissor]     │ Produto    : %-30s│%n", pedido.getProduto());
        System.out.printf ("  [Emissor]     │ Quantidade : %-30d│%n", pedido.getQuantidade());
        System.out.printf ("  [Emissor]     │ Preço Unit : R$ %-27.2f│%n", pedido.getPrecoUnitario());
        System.out.printf ("  [Emissor]     │ TOTAL      : R$ %-27.2f│%n", pedido.getTotalBruto());
        System.out.println("  [Emissor]     └───────────────────────────────────────────┘");
    }
}

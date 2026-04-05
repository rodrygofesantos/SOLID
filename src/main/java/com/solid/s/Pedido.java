package com.solid.s;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO S — Responsabilidade Única (Single Responsibility)      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ► O QUE É UMA CLASSE? (conceito básico de POO)
 * -----------------------------------------------
 * Em Programação Orientada a Objetos (POO), uma CLASSE é como uma
 * "ficha cadastral" que descreve um tipo de coisa do mundo real.
 *
 * Pense num pedido de loja: ele tem número, nome do cliente, produto...
 * A classe Pedido é exatamente isso: a ficha que descreve um pedido.
 *
 * Um OBJETO é uma ficha preenchida. Por exemplo:
 *   Pedido p = new Pedido("001", "João", "Notebook", 2, 2999.90);
 *   ↑ Aqui criamos UM pedido real (objeto) a partir da ficha (classe).
 *
 * ► O QUE É O PRINCÍPIO DA RESPONSABILIDADE ÚNICA?
 * --------------------------------------------------
 * Imagine que você contrata um funcionário para ser CAIXA da loja.
 * Depois resolve que ele também deve ser ESTOQUISTA, ENTREGADOR e
 * GERENTE ao mesmo tempo. O resultado? Confusão total.
 *
 * Em código é igual: cada classe deve ter UMA ÚNICA responsabilidade.
 *
 * ► ERRADO (sem o princípio):
 *   class Pedido {
 *       // dados do pedido...
 *       void salvarNoBancoDeDados() { ... }  // responsabilidade extra!
 *       void imprimirNotaFiscal()   { ... }  // responsabilidade extra!
 *       void validarRegras()        { ... }  // responsabilidade extra!
 *   }
 *   Problema: se a impressora muda, eu preciso mexer na classe Pedido.
 *   Se o banco de dados muda, idem. A classe vira um "canivete suíço".
 *
 * ► CERTO (com o princípio):
 *   Pedido             → guarda os DADOS          (só muda se os dados mudarem)
 *   RepositorioDePedido→ SALVA no banco            (só muda se o banco mudar)
 *   ServicoDePedido    → aplica REGRAS de negócio  (só muda se as regras mudarem)
 *   EmissorDeNota      → IMPRIME a nota            (só muda se o layout mudar)
 *
 * ESTA CLASSE tem responsabilidade: guardar os DADOS de um pedido.
 */
public class Pedido {

    // ── Atributos ────────────────────────────────────────────────────────────
    //
    // Atributos = características do objeto (como colunas numa tabela).
    // "private" = acesso restrito a esta classe (ENCAPSULAMENTO).
    //    → Ninguém de fora pode alterar esses dados diretamente.
    //    → Para ler, usamos os métodos "get" mais abaixo.
    // "final" = valor definido no construtor e nunca mais muda (imutável).

    private final String numeroPedido;    // ex: "001"
    private final String nomeCliente;     // ex: "Ana Lima"
    private final String produto;         // ex: "Notebook Dell"
    private final int    quantidade;      // ex: 2
    private final double precoUnitario;   // ex: 2999.90

    // ── Construtor ────────────────────────────────────────────────────────────
    //
    // CONSTRUTOR = método especial chamado com "new" para criar um objeto.
    // O nome deve ser idêntico ao nome da classe.
    // "this.x = x" significa: "guarde o valor recebido no atributo da classe".

    public Pedido(String numeroPedido, String nomeCliente, String produto,
                  int quantidade, double precoUnitario) {
        this.numeroPedido  = numeroPedido;
        this.nomeCliente   = nomeCliente;
        this.produto       = produto;
        this.quantidade    = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // ── Getters (métodos de leitura) ─────────────────────────────────────────
    //
    // Como os atributos são "private", criamos métodos públicos "get"
    // para permitir que outras classes leiam os dados com segurança.
    // Isso é o ENCAPSULAMENTO — um dos pilares da POO.

    public String getNumeroPedido()  { return numeroPedido; }
    public String getNomeCliente()   { return nomeCliente; }
    public String getProduto()       { return produto; }
    public int    getQuantidade()    { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }

    /** Calcula o valor total bruto (quantidade × preço unitário). */
    public double getTotalBruto() {
        return quantidade * precoUnitario;
    }

    // ── toString ──────────────────────────────────────────────────────────────
    //
    // Toda classe Java herda de "Object" um método toString().
    // Por padrão ele retorna algo feio como "com.solid.s.Pedido@1a2b3c".
    // Com @Override, substituímos por uma versão legível.
    // Agora System.out.println(pedido) mostra algo útil.

    @Override
    public String toString() {
        return String.format(
            "Pedido[nro=%s | cliente=%s | produto=%s | qtd=%d | preço=R$%.2f]",
            numeroPedido, nomeCliente, produto, quantidade, precoUnitario);
    }
}

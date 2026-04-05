package com.solid.o;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO O — Aberto/Fechado                                      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa um desconto por fidelidade (Bronze, Prata, Ouro).
 *
 * ► O que é um ENUM?
 * -------------------
 * ENUM (enumeração) é uma forma de definir um conjunto FIXO de valores.
 * Pense como uma lista de opções pré-definidas.
 *
 * Em vez de usar Strings soltas ("bronze", "prata", "ouro") — que podem
 * ter erros de digitação — usamos um enum:
 *   Categoria.BRONZE  ← o compilador garante que só existem esses valores
 *   Categoria.PRATA
 *   Categoria.OURO
 *
 * ► O que é "switch"?
 * --------------------
 * switch é uma forma de tomar decisões com base num valor.
 * O switch moderno com "->" (Java 14+) é mais conciso e seguro:
 *
 *   return switch (categoria) {
 *       case BRONZE -> 0.05;   // se for BRONZE, retorna 0.05
 *       case PRATA  -> 0.10;   // se for PRATA,  retorna 0.10
 *       case OURO   -> 0.20;   // se for OURO,   retorna 0.20
 *   };
 *
 * ► Novamente: CalculadoraDePedido não foi tocada para adicionar este desconto.
 *   Isso demonstra que o sistema está "Fechado para Modificação".
 */
public class DescontoFidelidade implements Desconto {

    // ENUM dentro da classe — categorias de fidelidade disponíveis
    public enum Categoria { BRONZE, PRATA, OURO }

    private final Categoria categoria;

    public DescontoFidelidade(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula o desconto baseado na categoria do cliente.
     *   Bronze → 5% de desconto
     *   Prata  → 10% de desconto
     *   Ouro   → 20% de desconto
     */
    @Override
    public double aplicar(double precoOriginal) {
        double percentual = switch (categoria) {
            case BRONZE -> 0.05;
            case PRATA  -> 0.10;
            case OURO   -> 0.20;
        };
        return precoOriginal * (1.0 - percentual);
    }

    @Override
    public String descrever() {
        return switch (categoria) {
            case BRONZE -> "Fidelidade Bronze — 5% de desconto";
            case PRATA  -> "Fidelidade Prata — 10% de desconto";
            case OURO   -> "Fidelidade Ouro — 20% de desconto";
        };
    }
}

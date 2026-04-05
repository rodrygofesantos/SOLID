package com.solid.o;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO O — Aberto/Fechado                                      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE está FECHADA PARA MODIFICAÇÃO.
 *
 * ► O que isso significa na prática?
 * ------------------------------------
 * Esta classe foi escrita UMA VEZ e nunca mais precisou ser modificada,
 * mesmo quando adicionamos DescontoSazonal, DescontoFidelidade, e
 * qualquer outro desconto futuro.
 *
 * ► Como isso é possível?
 * ------------------------
 * Porque ela depende da ABSTRAÇÃO (interface Desconto), não de
 * implementações concretas (DescontoSazonal, DescontoFidelidade...).
 *
 * É como um tomador de corrente que aceita qualquer aparelho,
 * sem precisar ser modificado para cada novo eletrodoméstico.
 *
 * ► Compare as duas abordagens:
 *
 *   SEM o princípio O (ruim):
 *   ─────────────────────────
 *   double calcular(double preco, String tipo) {
 *       if (tipo.equals("sazonal"))    preco *= 0.75;  // ← mexe aqui
 *       if (tipo.equals("fidelidade")) preco *= 0.80;  // ← mexe aqui
 *       if (tipo.equals("cupom"))      preco *= 0.90;  // ← mexe aqui
 *       // PROBLEMA: cada novo desconto exige modificar esta classe!
 *       //           Risco de quebrar os descontos que já funcionavam.
 *       return preco;
 *   }
 *
 *   COM o princípio O (correto — este código):
 *   ────────────────────────────────────────────
 *   private final Desconto desconto; // só conhece a interface
 *
 *   double calcularPrecoFinal(double preco) {
 *       return desconto.aplicar(preco); // funciona com QUALQUER desconto
 *       // Nunca precisa mudar!
 *   }
 */
public class CalculadoraDePedido {

    // Depende da ABSTRAÇÃO (interface), não de uma classe concreta.
    // Isso é o coração do princípio O.
    private final Desconto desconto;

    /**
     * Recebe qualquer desconto pelo construtor.
     * Pode ser DescontoSazonal, DescontoFidelidade, ou qualquer outro
     * que venha a ser criado no futuro — sem modificar esta classe.
     */
    public CalculadoraDePedido(Desconto desconto) {
        this.desconto = desconto;
    }

    /**
     * Calcula o preço final aplicando o desconto recebido.
     *
     * Este método NUNCA muda — independente de qual desconto for injetado.
     * Isso é "Fechado para Modificação".
     *
     * @param precoOriginal preço antes do desconto
     * @return preço após o desconto
     */
    public double calcularPrecoFinal(double precoOriginal) {
        double precoFinal = desconto.aplicar(precoOriginal);
        System.out.printf("  [Calculadora] Desconto: %s%n", desconto.descrever());
        System.out.printf("  [Calculadora] Original: R$ %.2f  →  Final: R$ %.2f%n",
                precoOriginal, precoFinal);
        return precoFinal;
    }
}

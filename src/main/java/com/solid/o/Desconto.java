package com.solid.o;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO O — Aberto/Fechado (Open/Closed Principle)             ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ► O QUE É UMA INTERFACE? (conceito básico de POO)
 * --------------------------------------------------
 * Uma INTERFACE é um "contrato". Ela diz o QUE deve ser feito,
 * mas não COMO.
 *
 * Pense numa tomada elétrica: ela define um "contrato" (formato dos pinos,
 * voltagem). Qualquer aparelho que siga esse contrato pode ser plugado,
 * seja um celular, um ventilador ou um computador.
 *
 * A interface Desconto diz: "Todo desconto DEVE ter um método aplicar()
 * e um método descrever()." Cada tipo de desconto implementa do seu jeito.
 *
 * ► O QUE É O PRINCÍPIO ABERTO/FECHADO?
 * ---------------------------------------
 * "Aberto para EXTENSÃO, fechado para MODIFICAÇÃO."
 *
 * Traduzindo:
 *   → Você pode ADICIONAR novos comportamentos ao sistema...
 *   → ...SEM precisar modificar código que já funciona.
 *
 * ► ERRADO (sem o princípio):
 *   class CalculadoraDePedido {
 *       double calcular(double preco, String tipoDesconto) {
 *           if (tipoDesconto.equals("sazonal"))    return preco * 0.75;
 *           if (tipoDesconto.equals("fidelidade")) return preco * 0.80;
 *           if (tipoDesconto.equals("cupom"))      return preco * 0.90;
 *           // Para cada NOVO desconto, você tem que vir aqui e modificar!
 *           // Risco de quebrar o que já funcionava.
 *       }
 *   }
 *
 * ► CERTO (com o princípio):
 *   Criamos a interface Desconto. Para um novo tipo de desconto,
 *   basta criar uma nova classe que implemente Desconto.
 *   A CalculadoraDePedido NUNCA precisa ser modificada.
 *
 * Esta interface É a abstração que torna isso possível.
 */
public interface Desconto {

    /**
     * Aplica o desconto sobre o preço original e retorna o preço final.
     *
     * @param precoOriginal o preço antes do desconto
     * @return o preço após aplicar o desconto
     *
     * Exemplo: se o desconto for 20% e o preço for R$100,
     *          este método retorna R$80.
     */
    double aplicar(double precoOriginal);

    /**
     * Retorna uma descrição legível do desconto (para exibir ao usuário).
     *
     * Exemplo: "Desconto Black Friday (25% de desconto)"
     */
    String descrever();
}

package com.solid.o;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO O — Aberto/Fechado                                      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa um desconto sazonal (ex: Black Friday, Natal...).
 *
 * ► O que é "implements Desconto"?
 * ---------------------------------
 * Significa que esta classe ASSINA O CONTRATO da interface Desconto.
 * Ela promete implementar todos os métodos que a interface exige:
 *   - aplicar(double precoOriginal)
 *   - descrever()
 *
 * Se não implementar todos, o Java acusa erro de compilação.
 *
 * ► Por que isso é "Aberto para Extensão"?
 * -----------------------------------------
 * Para adicionar o desconto de Natal, bastou CRIAR ESTA NOVA CLASSE.
 * A CalculadoraDePedido não precisou ser tocada nem uma linha.
 * Isso é extensão sem modificação.
 *
 * ► @Override — o que significa?
 * --------------------------------
 * @Override avisa ao compilador: "Estou substituindo um método
 * definido na interface (ou superclasse)."
 * Se você errar o nome do método, o compilador te avisa.
 * É uma boa prática sempre usar @Override ao implementar interfaces.
 */
public class DescontoSazonal implements Desconto {

    private final String nomeTemporada;  // ex: "Black Friday", "Natal"
    private final double percentual;     // ex: 0.25 representa 25% de desconto

    /**
     * @param nomeTemporada nome da temporada (ex: "Black Friday")
     * @param percentual    percentual de desconto de 0.0 a 1.0 (ex: 0.25 = 25%)
     */
    public DescontoSazonal(String nomeTemporada, double percentual) {
        this.nomeTemporada = nomeTemporada;
        this.percentual    = percentual;
    }

    /**
     * Aplica o desconto.
     * Fórmula: preço - (preço × percentual) = preço × (1 - percentual)
     *
     * Exemplo com 25%: 100 × (1 - 0.25) = 100 × 0.75 = R$75
     */
    @Override
    public double aplicar(double precoOriginal) {
        return precoOriginal * (1.0 - percentual);
    }

    @Override
    public String descrever() {
        return String.format("%s — %.0f%% de desconto", nomeTemporada, percentual * 100);
    }
}

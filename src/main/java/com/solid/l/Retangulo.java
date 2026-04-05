package com.solid.l;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO L — Substituição de Liskov                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa um retângulo.
 *
 * ► "extends Forma" — o que acontece?
 * -------------------------------------
 * Retangulo herda tudo de Forma:
 *   - O método toString() (já implementado em Forma)
 *   - A obrigação de implementar area() e nome()
 *
 * Se não implementar area() e nome(), o Java acusa erro.
 * (porque são "abstract" em Forma)
 *
 * ► Liskov na prática:
 * ----------------------
 * Forma f = new Retangulo(5.0, 3.0);
 * f.area(); // retorna 15.0 — correto!
 *
 * Qualquer código que usa "Forma" funciona com "Retangulo" no lugar.
 * Nenhuma surpresa, nenhuma quebra — isso é Liskov!
 */
public class Retangulo extends Forma {

    private final double largura;
    private final double altura;

    public Retangulo(double largura, double altura) {
        this.largura = largura;
        this.altura  = altura;
    }

    /**
     * Área do retângulo = largura × altura
     * Nunca é negativa (cumpre o contrato de Forma).
     */
    @Override
    public double area() {
        return largura * altura;
    }

    @Override
    public String nome() {
        return String.format("Retângulo(%.1f × %.1f)", largura, altura);
    }
}

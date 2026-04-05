package com.solid.l;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO L — Substituição de Liskov                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa um círculo.
 *
 * ► Math.PI e Math.pow
 * ----------------------
 * Java tem uma classe chamada "Math" cheia de constantes e funções
 * matemáticas prontas para usar:
 *   Math.PI   = 3.14159265... (π)
 *   Math.pow(x, 2) = x² (x elevado ao quadrado)
 *   Math.sqrt(x)   = √x (raiz quadrada de x)
 *
 * Área do círculo = π × r²
 *
 * ► Por que Circulo pode substituir Forma?
 * -----------------------------------------
 * CalculadoraDeArea trabalha com List<Forma>.
 * Se colocarmos um Circulo nessa lista, ela vai chamar circulo.area()
 * e obterá π × r² — correto, sem surpresas.
 *
 * Isso é a substituição de Liskov funcionando: o código que espera
 * uma Forma funciona igualmente bem com um Circulo.
 */
public class Circulo extends Forma {

    private final double raio;

    public Circulo(double raio) {
        this.raio = raio;
    }

    /**
     * Área do círculo = π × raio²
     * Nunca é negativa (cumpre o contrato de Forma).
     */
    @Override
    public double area() {
        return Math.PI * raio * raio;
    }

    @Override
    public String nome() {
        return String.format("Círculo(raio=%.1f)", raio);
    }
}

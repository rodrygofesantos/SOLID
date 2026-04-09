package com.solid.l;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO L — Substituição de Liskov                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE representa um triângulo e foi adicionada SEM modificar
 * CalculadoraDeArea — demonstrando o princípio de Liskov na prática.
 *
 * ► O que foi preciso fazer para adicionar esta forma?
 * -----------------------------------------------------
 *   1. Criar este arquivo (Triangulo.java).          ← único passo!
 *   2. Adicioná-la à lista em Main.java.
 *
 *   NÃO foi preciso:
 *     ✗ Abrir CalculadoraDeArea.java
 *     ✗ Adicionar "if (forma instanceof Triangulo)" em algum lugar
 *     ✗ Modificar Retangulo.java ou Circulo.java
 *
 *   Isso é Liskov + Aberto/Fechado trabalhando juntos!
 *
 * ► Por que "extends Forma"?
 * ---------------------------
 * "extends" = herança. Significa: "Triangulo É UMA Forma."
 * Herdar de Forma obriga esta classe a implementar:
 *   - area()  → obrigatório (abstract em Forma)
 *   - nome()  → obrigatório (abstract em Forma)
 *
 * Em troca, recebe de graça:
 *   - toString() → já implementado em Forma, usa area() e nome()
 *
 * ► Liskov na prática:
 * ---------------------
 * CalculadoraDeArea trabalha com List<Forma>.
 * Ao colocar um Triangulo nessa lista, ela chama triangulo.area()
 * e obtém (base × altura) / 2 — correto, sem surpresas.
 *
 * O código que esperava Forma funciona igualmente bem com Triangulo.
 * NENHUMA linha de CalculadoraDeArea precisou mudar. Isso é Liskov!
 *
 * ► VIOLAÇÃO de Liskov que NÃO fizemos (exemplo didático):
 * ---------------------------------------------------------
 * Seria uma violação se area() retornasse um valor negativo,
 * lançasse uma exceção, ou se nome() retornasse null.
 * Essas situações "quebrariam o contrato" que Forma estabelece,
 * e o código de CalculadoraDeArea poderia falhar de forma inesperada.
 * Aqui, honramos o contrato: area() ≥ 0, nome() nunca é null.
 */
public class Triangulo extends Forma {

    /*
     * Base e altura do triângulo.
     *
     * ► Por que "private final"?
     * ---------------------------
     * private → só esta classe acessa esses valores
     * final   → imutáveis após o construtor (o triângulo não muda de tamanho)
     *
     * Imutabilidade é importante para Liskov: se a subclasse pudesse
     * alterar seus atributos depois de criada, poderia causar resultados
     * inconsistentes ao chamar area() duas vezes.
     */
    private final double base;
    private final double altura;

    /**
     * Cria um triângulo com base e altura definidas.
     *
     * @param base   comprimento da base do triângulo (em unidades)
     * @param altura altura perpendicular à base (em unidades)
     *
     * Exemplo:
     *   Triangulo t = new Triangulo(6.0, 4.0);
     *   t.area(); // → 12.0  (6 × 4 / 2)
     */
    public Triangulo(double base, double altura) {
        this.base   = base;
        this.altura = altura;
    }

    /**
     * Calcula a área do triângulo.
     *
     * ► Fórmula:
     *   área = (base × altura) / 2
     *
     * Por que dividir por 2?
     * Um triângulo é exatamente metade de um paralelogramo (ou retângulo)
     * de mesma base e altura.
     *
     * Exemplo: base=6, altura=4 → área = (6 × 4) / 2 = 12
     *
     * ► O resultado é SEMPRE ≥ 0 (base e altura são positivos).
     *   Isso honra o contrato de Forma — nenhuma quebra de Liskov!
     *
     * ► @Override: estamos fornecendo a implementação do método "area()"
     *   que estava marcado como "abstract" em Forma. Sem este método,
     *   o Java acusaria erro de compilação.
     */
    @Override
    public double area() {
        return (base * altura) / 2.0;
    }

    /**
     * Retorna o nome descritivo do triângulo para exibição.
     *
     * ► String.format("Triângulo(base=%.1f, altura=%.1f)", base, altura)
     *   %.1f → número com 1 casa decimal
     *   Exemplo: Triângulo(base=6,0, altura=4,0)
     */
    @Override
    public String nome() {
        return String.format("Triângulo(base=%.1f, altura=%.1f)", base, altura);
    }
}

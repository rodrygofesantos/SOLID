package com.solid.l;

import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO L — Substituição de Liskov                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE calcula a área total de uma lista de formas.
 *
 * ► A mágica do Liskov em ação:
 * ------------------------------
 * Este método recebe "List<Forma>" — uma lista de formas genéricas.
 * Não importa se dentro tem Retangulo, Circulo, ou qualquer forma futura.
 * O código funciona para TODAS elas sem modificação.
 *
 * ► Sem o princípio de Liskov, o código seria assim (RUIM):
 * ----------------------------------------------------------
 *   for (Forma f : formas) {
 *       if (f instanceof Retangulo) {
 *           Retangulo r = (Retangulo) f;
 *           total += r.getLargura() * r.getAltura(); // conhece detalhes internos!
 *       } else if (f instanceof Circulo) {
 *           Circulo c = (Circulo) f;
 *           total += Math.PI * c.getRaio() * c.getRaio(); // idem
 *       }
 *       // E se aparecer um Triângulo? Tem que vir aqui e mexer...
 *   }
 *
 * ► Com o princípio de Liskov (este código — CORRETO):
 * -----------------------------------------------------
 *   for (Forma f : formas) {
 *       total += f.area(); // simplesmente confia no contrato!
 *       // Funciona para Retangulo, Circulo, e qualquer forma futura.
 *   }
 *
 * ► "List<Forma>" — o que é isso?
 * ---------------------------------
 * List<Forma> = uma lista que aceita objetos do tipo Forma
 * (ou qualquer subclasse de Forma — polimorfismo!).
 * POLIMORFISMO = "muitas formas": o mesmo método (area()) se comporta
 * diferente conforme o tipo real do objeto.
 */
public class CalculadoraDeArea {

    /**
     * Calcula e exibe a área de cada forma, depois retorna a soma total.
     *
     * @param formas lista de formas (pode misturar Retangulo, Circulo, etc.)
     * @return soma das áreas de todas as formas
     */
    public double areaTotal(List<Forma> formas) {
        double total = 0;

        for (Forma forma : formas) {
            double a = forma.area();
            // "%-35s" = alinha o texto à esquerda, completando até 35 caracteres
            System.out.printf("  [Calculadora] %-35s → área = %.4f%n", forma.nome(), a);
            total += a;
        }

        System.out.printf("  [Calculadora] Área total de %d formas: %.4f%n",
                formas.size(), total);
        return total;
    }
}

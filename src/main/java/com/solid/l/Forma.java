package com.solid.l;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO L — Substituição de Liskov (Liskov Substitution)        ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ► O QUE É UMA CLASSE ABSTRATA? (conceito de POO)
 * --------------------------------------------------
 * Uma CLASSE ABSTRATA é um "modelo incompleto". Ela define a estrutura
 * comum, mas deixa alguns métodos para as subclasses completarem.
 *
 * Você NÃO pode criar um objeto diretamente de uma classe abstrata:
 *   Forma f = new Forma(); // ERRO! Forma é abstrata.
 *
 * Mas pode criar subclasses:
 *   Forma f = new Retangulo(5, 3);  // OK! Retangulo extends Forma
 *   Forma f = new Circulo(4);       // OK! Circulo extends Forma
 *
 * ► O QUE É HERANÇA? (conceito de POO)
 * --------------------------------------
 * HERANÇA = uma classe filha herda atributos e métodos da classe mãe.
 * "extends" = palavra-chave para herdar.
 *
 * Analogia: um Retangulo É UMA Forma. Um Circulo É UMA Forma.
 * ("É UM" = relação de herança — use herança só quando fizer sentido!)
 *
 * ► O QUE É O PRINCÍPIO DE LISKOV?
 * ----------------------------------
 * "Se S é subclasse de T, então objetos de T podem ser substituídos
 * por objetos de S sem quebrar o programa."
 *
 * Em português simples:
 * → Se o código espera uma Forma, você pode passar um Retangulo
 *   ou um Circulo — e tudo continua funcionando corretamente.
 *
 * ► VIOLAÇÃO CLÁSSICA (para entender o problema):
 *   Imagine Quadrado extends Retangulo.
 *   Retangulo tem setLargura() e setAltura() independentes.
 *   Mas um Quadrado EXIGE que largura == altura sempre.
 *   Se você chamar retangulo.setLargura(5), espera area = 5 × altura.
 *   Mas se "retangulo" for na verdade um Quadrado, setLargura(5)
 *   também muda a altura → o contrato foi quebrado → violação de Liskov!
 *
 * ► SOLUÇÃO USADA AQUI:
 *   Forma é abstrata e imutável (atributos definidos no construtor).
 *   Retangulo e Circulo NUNCA quebram o contrato de area().
 *
 * ESTA CLASSE define o CONTRATO: toda Forma deve saber calcular sua área.
 */
public abstract class Forma {

    /**
     * Retorna a área desta forma.
     *
     * CONTRATO: area() deve retornar um valor não-negativo.
     * Toda subclasse DEVE honrar este contrato (isso é Liskov!).
     *
     * "abstract" = sem implementação aqui; cada subclasse implementa do seu jeito.
     */
    public abstract double area();

    /**
     * Retorna o nome descritivo da forma (para exibição).
     */
    public abstract String nome();

    /**
     * Herdado por todas as subclasses automaticamente.
     * System.out.println(umaForma) vai chamar este método.
     */
    @Override
    public String toString() {
        return String.format("%s com área = %.4f", nome(), area());
    }
}

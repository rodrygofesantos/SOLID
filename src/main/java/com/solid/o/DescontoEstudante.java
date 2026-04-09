package com.solid.o;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO O — Aberto/Fechado                                      ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE foi adicionada SEM modificar nenhuma classe existente.
 * Isso demonstra exatamente o princípio Aberto/Fechado na prática!
 *
 * ► O que foi feito para adicionar este novo desconto?
 * -----------------------------------------------------
 *   1. Criamos este arquivo (DescontoEstudante.java).       ← ÚNICO passo!
 *   2. Nada mais.
 *
 *   Não foi preciso:
 *     ✗ Abrir Desconto.java
 *     ✗ Abrir CalculadoraDePedido.java
 *     ✗ Abrir DescontoSazonal.java ou DescontoFidelidade.java
 *     ✗ Adicionar um novo "if" ou "case" em alguma classe já existente
 *
 * ► Por que isso é poderoso?
 * ---------------------------
 * Imagine que o sistema está em produção e tem 10 tipos de desconto.
 *   → SEM o princípio O: para cada novo desconto, você abre a classe
 *     central, adiciona um if/case, e corre o risco de quebrar os
 *     outros 10 descontos que já funcionavam.
 *
 *   → COM o princípio O: você cria uma nova classe isolada.
 *     Se der errado, só este arquivo é afetado. Os outros 10 ficam
 *     intocados e continuam funcionando normalmente.
 *
 * ► O que é "implements Desconto"?
 * ---------------------------------
 * Esta classe "assina o contrato" da interface Desconto.
 * Isso significa que ela GARANTE ter os métodos:
 *   - aplicar(double precoOriginal) → calcula o preço com desconto
 *   - descrever()                   → retorna o nome do desconto
 *
 * Se faltar qualquer um deles, o Java acusa erro antes mesmo de rodar.
 */
public class DescontoEstudante implements Desconto {

    /*
     * Constante: o desconto de estudante é sempre 50%.
     *
     * ► O que é "private static final"?
     * -----------------------------------
     * private → só esta classe pode usar este valor
     * static  → pertence à classe, não a um objeto específico
     *           (todos os objetos DescontoEstudante compartilham o mesmo valor)
     * final   → não pode ser alterado depois de definido (é uma constante)
     *
     * Por convenção, constantes em Java são escritas em MAIÚSCULAS_COM_UNDERSCORE.
     *
     * Usar uma constante nomeada é melhor que escrever 0.50 direto no código:
     *   ✗  return precoOriginal * 0.50;   // o que significa esse 0.50?
     *   ✓  return precoOriginal * (1.0 - PERCENTUAL_DESCONTO); // auto-explicativo
     */
    private static final double PERCENTUAL_DESCONTO = 0.50; // 50% de desconto

    /*
     * O nome do estudante é armazenado para personalizar a mensagem.
     *
     * ► O que é "private final"?
     * ---------------------------
     * private → só esta classe acessa este campo
     * final   → depois de atribuído no construtor, não pode mudar
     *           (o nome do estudante não muda durante a vida do objeto)
     *
     * Usar "final" em campos é uma boa prática: torna o objeto imutável
     * e evita bugs causados por mudança acidental de estado.
     */
    private final String nomeEstudante;

    /**
     * Construtor: recebe o nome do estudante para personalizar a exibição.
     *
     * ► O que é um construtor?
     * -------------------------
     * É o método chamado quando usamos "new DescontoEstudante(...)".
     * Ele inicializa os campos do objeto.
     *
     * Exemplo de uso:
     *   Desconto d = new DescontoEstudante("Maria Clara");
     *   // agora d.nomeEstudante == "Maria Clara"
     *
     * @param nomeEstudante nome do estudante que receberá o desconto
     */
    public DescontoEstudante(String nomeEstudante) {
        this.nomeEstudante = nomeEstudante;
        // "this.nomeEstudante" → campo do objeto
        // "nomeEstudante"      → parâmetro recebido
        // O "this." resolve a ambiguidade quando os nomes são iguais.
    }

    /**
     * Aplica 50% de desconto sobre o preço original.
     *
     * ► Fórmula:
     *   precoFinal = precoOriginal × (1 - 0.50)
     *              = precoOriginal × 0.50
     *
     * Exemplo: R$ 200,00 com 50% → R$ 200,00 × 0.50 = R$ 100,00
     *
     * ► @Override: estamos substituindo o método "aplicar" da interface Desconto.
     *   Sem @Override, o código funciona igual — mas com ele, o compilador
     *   avisa se errarmos o nome do método (proteção extra).
     */
    @Override
    public double aplicar(double precoOriginal) {
        return precoOriginal * (1.0 - PERCENTUAL_DESCONTO);
    }

    /**
     * Retorna uma descrição legível do desconto.
     *
     * ► String.format() — como funciona?
     * ------------------------------------
     * String.format("Olá, %s! Desconto de %.0f%%", "Ana", 50.0)
     *   → "Olá, Ana! Desconto de 50%"
     *
     * %s   → substitui por uma String (nomeEstudante)
     * %.0f → substitui por um número com 0 casas decimais (PERCENTUAL_DESCONTO * 100)
     * %%   → imprime o símbolo % (um % duplo vira um % literal)
     */
    @Override
    public String descrever() {
        return String.format(
            "Desconto Estudante para %s — %.0f%% de desconto",
            nomeEstudante,
            PERCENTUAL_DESCONTO * 100
        );
    }
}

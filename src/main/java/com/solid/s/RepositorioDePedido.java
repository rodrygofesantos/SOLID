package com.solid.s;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO S — Responsabilidade Única                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE tem responsabilidade: SALVAR e BUSCAR pedidos.
 *
 * ► O que é um Repositório?
 * --------------------------
 * "Repositório" é um padrão de projeto muito comum. Pense nele como
 * uma GAVETA de arquivo: você coloca documentos (save) e depois
 * pode pegar de volta (findById, findAll).
 *
 * No mundo real, essa gaveta seria um banco de dados (MySQL, PostgreSQL...).
 * Aqui simulamos com uma lista em memória para simplificar.
 *
 * ► Por que separar do Pedido?
 * -----------------------------
 * Se amanhã você trocar o banco de dados de MySQL para MongoDB,
 * APENAS esta classe precisa mudar.
 * A classe Pedido continua igual. O ServicoDePedido continua igual.
 * Isso é a beleza do Princípio da Responsabilidade Única!
 */
public class RepositorioDePedido {

    // Simulamos um banco de dados com uma lista em memória.
    // ArrayList = lista que cresce dinamicamente conforme adicionamos itens.
    // "List<Pedido>" = uma lista que só aceita objetos do tipo Pedido.
    private final List<Pedido> bancoDeDados = new ArrayList<>();

    /**
     * Salva um pedido no repositório.
     *
     * No mundo real, aqui teria código SQL como:
     *   "INSERT INTO pedidos VALUES (?, ?, ...)"
     */
    public void salvar(Pedido pedido) {
        bancoDeDados.add(pedido);
        System.out.println("  [Repositório] Pedido salvo: " + pedido.getNumeroPedido());
    }

    /**
     * Retorna todos os pedidos salvos.
     *
     * "Collections.unmodifiableList" garante que quem receber a lista
     * não consiga modificá-la (segurança adicional).
     */
    public List<Pedido> buscarTodos() {
        return Collections.unmodifiableList(bancoDeDados);
    }

    /**
     * Busca um pedido pelo número. Retorna null se não encontrado.
     *
     * "stream()" transforma a lista num fluxo de dados que podemos filtrar.
     * ".filter(...)" mantém só os pedidos com o número desejado.
     * ".findFirst()" pega o primeiro resultado.
     * ".orElse(null)" retorna null se não encontrou nenhum.
     */
    public Pedido buscarPorNumero(String numeroPedido) {
        return bancoDeDados.stream()
                .filter(p -> p.getNumeroPedido().equals(numeroPedido))
                .findFirst()
                .orElse(null);
    }
}

package com.solid.s;

/**
 * ╔══════════════════════════════════════════════════════════════════════╗
 * ║   PRINCÍPIO S — Responsabilidade Única                              ║
 * ╚══════════════════════════════════════════════════════════════════════╝
 *
 * ESTA CLASSE tem responsabilidade: aplicar as REGRAS DE NEGÓCIO.
 *
 * ► O que são "regras de negócio"?
 * ---------------------------------
 * São as regras que a empresa define. Por exemplo:
 *   - "Não podemos aceitar pedido com quantidade zero."
 *   - "O preço não pode ser negativo."
 *   - "Um pedido precisa ter nome do cliente."
 *
 * Essas regras MUDAM com o tempo (negócio evolui). Por isso ficam
 * numa classe separada: se a regra mudar, só esta classe muda.
 *
 * ► INJEÇÃO DE DEPENDÊNCIA (conceito importante!)
 * ------------------------------------------------
 * Repare que esta classe não cria o Repositório nem o EmissorDeNota.
 * Ela os RECEBE pelo construtor:
 *
 *   public ServicoDePedido(RepositorioDePedido repo, EmissorDeNota emissor)
 *
 * Isso se chama INJEÇÃO DE DEPENDÊNCIA. Vantagem: se quisermos testar
 * esta classe, podemos passar versões "falsas" (mocks) do repositório
 * sem precisar de banco de dados real.
 *
 * ► Fluxo de um pedido:
 *   1. Validar (regras de negócio)
 *   2. Salvar (delega ao repositório)
 *   3. Emitir nota (delega ao emissor)
 */
public class ServicoDePedido {

    // Dependências recebidas pelo construtor (não criadas aqui dentro)
    private final RepositorioDePedido repositorio;
    private final EmissorDeNota emissor;

    public ServicoDePedido(RepositorioDePedido repositorio, EmissorDeNota emissor) {
        this.repositorio = repositorio;
        this.emissor     = emissor;
    }

    /**
     * Processa um pedido: valida → salva → emite nota.
     *
     * Este método ORQUESTRA o processo, mas DELEGA cada parte
     * para a classe responsável por ela.
     *
     * @param pedido o pedido a ser processado
     * @throws IllegalArgumentException se o pedido for inválido
     */
    public void realizarPedido(Pedido pedido) {
        validar(pedido);           // aplica as regras de negócio
        repositorio.salvar(pedido); // delega o salvamento ao repositório
        emissor.emitir(pedido);     // delega a impressão ao emissor
    }

    // ── Regras de negócio (privadas — detalhes internos desta classe) ─────────

    private void validar(Pedido pedido) {
        if (pedido.getQuantidade() <= 0) {
            // IllegalArgumentException = exceção lançada quando um argumento é inválido
            throw new IllegalArgumentException(
                "Pedido " + pedido.getNumeroPedido() + ": quantidade deve ser maior que zero.");
        }
        if (pedido.getPrecoUnitario() < 0) {
            throw new IllegalArgumentException(
                "Pedido " + pedido.getNumeroPedido() + ": preço unitário não pode ser negativo.");
        }
        System.out.println("  [Serviço]     Pedido validado: " + pedido.getNumeroPedido());
    }
}

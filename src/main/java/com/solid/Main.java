package com.solid;

// ── Princípio S: Responsabilidade Única ──────────────────────────────────────
import com.solid.s.EmissorDeNota;
import com.solid.s.Pedido;
import com.solid.s.RepositorioDePedido;
import com.solid.s.ServicoDePedido;

// ── Princípio O: Aberto/Fechado ───────────────────────────────────────────────
import com.solid.o.CalculadoraDePedido;
import com.solid.o.DescontoFidelidade;
import com.solid.o.DescontoSazonal;

// ── Princípio L: Substituição de Liskov ──────────────────────────────────────
import com.solid.l.CalculadoraDeArea;
import com.solid.l.Circulo;
import com.solid.l.Forma;
import com.solid.l.Retangulo;

// ── Princípio I: Segregação de Interface ─────────────────────────────────────
import com.solid.i.DispositivoMultiFuncional;
import com.solid.i.Impressora;
import com.solid.i.Imprimivel;

// ── Princípio D: Inversão de Dependência ─────────────────────────────────────
import com.solid.d.EnviadorDeEmail;
import com.solid.d.EnviadorDeSms;
import com.solid.d.ServicoDeNotificacao;

import java.util.List;

/**
 * ╔══════════════════════════════════════════════════════════════════════════╗
 * ║          TUTORIAL PRÁTICO — OS 5 PRINCÍPIOS SOLID EM JAVA              ║
 * ╠══════════════════════════════════════════════════════════════════════════╣
 * ║                                                                          ║
 * ║  S — Single Responsibility  (Responsabilidade Única)                    ║
 * ║  O — Open/Closed            (Aberto/Fechado)                            ║
 * ║  L — Liskov Substitution    (Substituição de Liskov)                    ║
 * ║  I — Interface Segregation  (Segregação de Interface)                   ║
 * ║  D — Dependency Inversion   (Inversão de Dependência)                   ║
 * ║                                                                          ║
 * ╠══════════════════════════════════════════════════════════════════════════╣
 * ║  Para compilar e executar:                                               ║
 * ║    mvn clean package                                                     ║
 * ║    java -jar target/solid-principios.jar                                 ║
 * ╚══════════════════════════════════════════════════════════════════════════╝
 *
 * Esta é a classe PRINCIPAL (ponto de entrada do programa).
 * Em Java, todo programa começa pelo método "public static void main".
 * É como a "porta de entrada" do seu código.
 */
public class Main {

    // ── Helpers de formatação ─────────────────────────────────────────────────

    // String.repeat(n) = repete o caractere n vezes
    // Usamos para criar linhas decorativas no console
    private static final String LINHA_GROSSA  = "═".repeat(65);
    private static final String LINHA_FINA    = "─".repeat(65);

    /** Imprime um cabeçalho grande para cada princípio. */
    private static void cabecalho(String titulo) {
        System.out.println();
        System.out.println(LINHA_GROSSA);
        System.out.println("  " + titulo);
        System.out.println(LINHA_GROSSA);
    }

    /** Imprime um separador de seção menor. */
    private static void secao(String rotulo) {
        System.out.println();
        System.out.println(LINHA_FINA);
        System.out.println("  " + rotulo);
        System.out.println(LINHA_FINA);
    }

    // ─────────────────────────────────────────────────────────────────────────

    /**
     * Método principal — ponto de entrada do programa.
     *
     * "public"  = pode ser chamado de qualquer lugar
     * "static"  = pertence à classe, não a um objeto específico
     * "void"    = não retorna nenhum valor
     * "String[] args" = argumentos passados pela linha de comando (podemos ignorar)
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("  ╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("  ║         TUTORIAL SOLID — Princípios de POO em Java            ║");
        System.out.println("  ║         Desenvolvido para alunos de programação                ║");
        System.out.println("  ╚═══════════════════════════════════════════════════════════════╝");

        // Cada método abaixo demonstra UM princípio SOLID
        demoPrincipioS();
        demoPrincipioO();
        demoPrincipioL();
        demoPrincipioI();
        demoPrincipioD();

        System.out.println();
        System.out.println(LINHA_GROSSA);
        System.out.println("  Todos os 5 princípios SOLID foram demonstrados com sucesso!");
        System.out.println(LINHA_GROSSA);
    }

    // =========================================================================
    //  S — RESPONSABILIDADE ÚNICA (Single Responsibility Principle)
    // =========================================================================

    /**
     * Demonstra o Princípio S:
     *
     * Cada classe tem UMA responsabilidade:
     *   Pedido              → guarda os dados
     *   RepositorioDePedido → salva e busca
     *   ServicoDePedido     → valida e orquestra
     *   EmissorDeNota       → formata e imprime
     *
     * Se o banco de dados mudar → só RepositorioDePedido muda.
     * Se o layout da nota mudar → só EmissorDeNota muda.
     * Se as regras mudarem     → só ServicoDePedido muda.
     */
    private static void demoPrincipioS() {
        cabecalho("S — RESPONSABILIDADE ÚNICA");
        System.out.println("  Cada classe tem UM único motivo para mudar.");
        System.out.println();
        System.out.println("  Classes envolvidas:");
        System.out.println("    Pedido              → guarda os dados do pedido");
        System.out.println("    RepositorioDePedido → salva e busca pedidos");
        System.out.println("    ServicoDePedido     → valida e processa");
        System.out.println("    EmissorDeNota       → formata e imprime a nota");

        // Criamos cada "colaborador" separadamente
        // (cada um com sua responsabilidade específica)
        RepositorioDePedido repositorio = new RepositorioDePedido();
        EmissorDeNota       emissor     = new EmissorDeNota();
        ServicoDePedido     servico     = new ServicoDePedido(repositorio, emissor);

        secao("Realizando Pedido #001 — Notebook");
        // "new Pedido(...)" cria um objeto Pedido com os dados fornecidos
        Pedido pedido1 = new Pedido("001", "Ana Souza", "Notebook Dell Inspiron", 1, 3499.90);
        servico.realizarPedido(pedido1);

        secao("Realizando Pedido #002 — Fone de Ouvido");
        Pedido pedido2 = new Pedido("002", "Carlos Lima", "Fone Bluetooth JBL", 2, 289.90);
        servico.realizarPedido(pedido2);

        secao("Consultando todos os pedidos no repositório");
        repositorio.buscarTodos().forEach(p ->
                System.out.println("  [Repositório] → " + p));
    }

    // =========================================================================
    //  O — ABERTO/FECHADO (Open/Closed Principle)
    // =========================================================================

    /**
     * Demonstra o Princípio O:
     *
     * CalculadoraDePedido está FECHADA para modificação — seu código
     * nunca muda, mesmo quando adicionamos novos tipos de desconto.
     *
     * O sistema está ABERTO para extensão — podemos criar DescontoSazonal,
     * DescontoFidelidade, DescontoCupom... sem tocar na calculadora.
     */
    private static void demoPrincipioO() {
        cabecalho("O — ABERTO/FECHADO");
        System.out.println("  CalculadoraDePedido nunca é modificada.");
        System.out.println("  Novos descontos são adicionados apenas criando novas classes.");

        double precoBase = 1000.00;
        System.out.printf("%n  Preço base: R$ %.2f%n", precoBase);

        secao("Desconto Sazonal: Black Friday (25% off)");
        // Adicionamos este desconto SEM modificar CalculadoraDePedido
        CalculadoraDePedido calcBlackFriday =
                new CalculadoraDePedido(new DescontoSazonal("Black Friday", 0.25));
        calcBlackFriday.calcularPrecoFinal(precoBase);

        secao("Desconto Sazonal: Natal (15% off)");
        CalculadoraDePedido calcNatal =
                new CalculadoraDePedido(new DescontoSazonal("Natal", 0.15));
        calcNatal.calcularPrecoFinal(precoBase);

        secao("Desconto Fidelidade: Cliente Ouro (20% off)");
        // Mais um novo desconto — CalculadoraDePedido continua intocada!
        CalculadoraDePedido calcOuro =
                new CalculadoraDePedido(new DescontoFidelidade(DescontoFidelidade.Categoria.OURO));
        calcOuro.calcularPrecoFinal(precoBase);

        secao("Desconto Fidelidade: Cliente Prata (10% off)");
        CalculadoraDePedido calcPrata =
                new CalculadoraDePedido(new DescontoFidelidade(DescontoFidelidade.Categoria.PRATA));
        calcPrata.calcularPrecoFinal(precoBase);
    }

    // =========================================================================
    //  L — SUBSTITUIÇÃO DE LISKOV (Liskov Substitution Principle)
    // =========================================================================

    /**
     * Demonstra o Princípio L:
     *
     * CalculadoraDeArea trabalha com "List<Forma>" — não importa se
     * a lista tem Retangulo, Circulo, ou qualquer forma futura.
     * O resultado é sempre correto porque todas as subclasses
     * honram o contrato de area().
     *
     * Sem este princípio, precisaríamos de "instanceof" para cada tipo —
     * código frágil que quebra a cada nova forma adicionada.
     */
    private static void demoPrincipioL() {
        cabecalho("L — SUBSTITUIÇÃO DE LISKOV");
        System.out.println("  Qualquer subclasse de Forma pode substituir Forma sem quebrar nada.");
        System.out.println("  CalculadoraDeArea usa List<Forma> — não importa se é Retangulo ou Circulo.");

        CalculadoraDeArea calculadora = new CalculadoraDeArea();

        // Lista mista de formas — polimorfismo em ação!
        // A CalculadoraDeArea não sabe (e não precisa saber) os tipos concretos.
        List<Forma> formas = List.of(
                new Retangulo(5.0, 3.0),    // área = 15.0
                new Circulo(4.0),            // área = π × 16 ≈ 50.27
                new Retangulo(10.0, 2.5),   // área = 25.0
                new Circulo(1.5)             // área = π × 2.25 ≈ 7.07
        );

        secao("Calculando áreas de uma lista mista (Retangulos e Circulos)");
        calculadora.areaTotal(formas);
    }

    // =========================================================================
    //  I — SEGREGAÇÃO DE INTERFACE (Interface Segregation Principle)
    // =========================================================================

    /**
     * Demonstra o Princípio I:
     *
     * Impressora implementa APENAS Imprimivel — ela não é forçada
     * a ter métodos de salvar ou escanear que não fazem sentido para ela.
     *
     * DispositivoMultiFuncional implementa as três interfaces porque
     * ele genuinamente suporta as três operações.
     *
     * Clientes recebem apenas a interface que precisam — acoplamento mínimo.
     */
    private static void demoPrincipioI() {
        cabecalho("I — SEGREGAÇÃO DE INTERFACE");
        System.out.println("  Nenhuma classe é forçada a implementar métodos que não usa.");
        System.out.println("  Interfaces pequenas e focadas > uma interface gigante.");

        secao("Impressora simples — implementa apenas Imprimivel");
        Impressora impressoraSimples = new Impressora("HP LaserJet 107a");
        impressoraSimples.imprimir("Relatório Mensal de Vendas");
        // impressoraSimples.salvar(...) ← NÃO EXISTE — honesto!
        // impressoraSimples.escanear(...) ← NÃO EXISTE — honesto!

        secao("Dispositivo Multifuncional — implementa Imprimivel + Salvavel + Escaneavel");
        DispositivoMultiFuncional mfp = new DispositivoMultiFuncional("Canon PIXMA G3160");
        mfp.imprimir("Contrato de Prestação de Serviços");
        mfp.salvar("Contrato de Prestação de Serviços", "/documentos/contratos/");
        String escaneado = mfp.escanear("RG_frente.jpg");
        System.out.println("  [Resultado]  Conteúdo escaneado: " + escaneado);

        secao("Usando o MFP apenas como Imprimivel (acoplamento mínimo)");
        // Um método que só precisa imprimir recebe "Imprimivel"
        // Ele não precisa saber que o objeto também sabe salvar e escanear
        Imprimivel apenasImprimir = mfp;  // polimorfismo!
        apenasImprimir.imprimir("Orçamento Q1 2025");
        // apenasImprimir.salvar(...)   ← invisível nesta visão
        // apenasImprimir.escanear(...) ← invisível nesta visão
    }

    // =========================================================================
    //  D — INVERSÃO DE DEPENDÊNCIA (Dependency Inversion Principle)
    // =========================================================================

    /**
     * Demonstra o Princípio D:
     *
     * ServicoDeNotificacao (alto nível) depende APENAS da abstração
     * EnviadorDeMensagem. Ele nunca menciona EnviadorDeEmail ou EnviadorDeSms.
     *
     * Para trocar o canal de notificação, basta injetar uma implementação
     * diferente no construtor — zero linhas alteradas no serviço.
     *
     * Isso é "Inversão de Dependência": os detalhes (email, sms) é que
     * dependem da abstração, não o contrário.
     */
    private static void demoPrincipioD() {
        cabecalho("D — INVERSÃO DE DEPENDÊNCIA");
        System.out.println("  ServicoDeNotificacao depende da ABSTRAÇÃO EnviadorDeMensagem.");
        System.out.println("  O canal (e-mail, SMS...) é injetado de fora — zero código alterado no serviço.");

        secao("ServicoDeNotificacao usando E-mail");
        // Injetamos EnviadorDeEmail no construtor
        ServicoDeNotificacao porEmail =
                new ServicoDeNotificacao(new EnviadorDeEmail());
        porEmail.enviarConfirmacaoDePedido("ana.souza@email.com", "001");
        porEmail.enviarAtualizacaoDeEnvio("ana.souza@email.com", "001", "BR112233445BR");

        secao("ServicoDeNotificacao usando SMS (zero linhas mudadas no serviço!)");
        // Trocamos apenas a implementação injetada — ServicoDeNotificacao é idêntico
        ServicoDeNotificacao porSms =
                new ServicoDeNotificacao(new EnviadorDeSms());
        porSms.enviarConfirmacaoDePedido("+55 31 98765-4321", "002");
        porSms.enviarAtualizacaoDeEnvio("+55 31 98765-4321", "002", "BR554433221BR");

        secao("Conclusão do Princípio D");
        System.out.println("  O código de ServicoDeNotificacao NÃO foi modificado entre as duas chamadas.");
        System.out.println("  Apenas a implementação de EnviadorDeMensagem foi trocada.");
        System.out.println("  Alto nível + baixo nível dependendo da abstração = sistema flexível!");
    }
}

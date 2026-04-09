package com.solid;

// ── Princípio S: Responsabilidade Única ──────────────────────────────────────
import com.solid.s.EmissorDeNota;
import com.solid.s.Pedido;
import com.solid.s.RepositorioDePedido;
import com.solid.s.ServicoDePedido;

// ── Princípio O: Aberto/Fechado ───────────────────────────────────────────────
import com.solid.o.CalculadoraDePedido;
import com.solid.o.DescontoEstudante;
import com.solid.o.DescontoFidelidade;
import com.solid.o.DescontoSazonal;

// ── Princípio L: Substituição de Liskov ──────────────────────────────────────
import com.solid.l.CalculadoraDeArea;
import com.solid.l.Circulo;
import com.solid.l.Forma;
import com.solid.l.Retangulo;
import com.solid.l.Triangulo;

// ── Princípio I: Segregação de Interface ─────────────────────────────────────
import com.solid.i.DispositivoMultiFuncional;
import com.solid.i.Impressora;
import com.solid.i.Imprimivel;

// ── Princípio D: Inversão de Dependência ─────────────────────────────────────
import com.solid.d.EnviadorDeEmail;
import com.solid.d.EnviadorDeSms;
import com.solid.d.EnviadorDeWhatsApp;
import com.solid.d.ServicoDeNotificacao;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String LINHA_GROSSA = "═".repeat(65);
    private static final String LINHA_FINA   = "─".repeat(65);

    // Scanner compartilhado por todos os métodos
    private static final Scanner TECLADO = new Scanner(System.in);

    // ── Helpers de formatação ─────────────────────────────────────────────────

    private static void cabecalho(String titulo) {
        System.out.println();
        System.out.println(LINHA_GROSSA);
        System.out.println("  " + titulo);
        System.out.println(LINHA_GROSSA);
    }

    private static void secao(String rotulo) {
        System.out.println();
        System.out.println(LINHA_FINA);
        System.out.println("  " + rotulo);
        System.out.println(LINHA_FINA);
    }

    // ── Breakpoint ────────────────────────────────────────────────────────────
    //
    // Este método simula um "breakpoint" de depurador:
    // o programa para, exibe uma dica do que vai acontecer a seguir
    // e só continua quando o aluno pressionar ENTER.

    private static void breakpoint(String dica) {
        System.out.println();
        System.out.println("  ┌─── BREAKPOINT ─────────────────────────────────────────────┐");
        System.out.printf ("  │  ► %s%n", dica);
        System.out.println("  │  Pressione ENTER para executar o próximo passo...           │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        TECLADO.nextLine(); // pausa aqui até o aluno pressionar ENTER
    }

    // ─────────────────────────────────────────────────────────────────────────

    public static void main(String[] args) {

        System.out.println();
        System.out.println("  ╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("  ║         TUTORIAL SOLID — Princípios de POO em Java            ║");
        System.out.println("  ║         Desenvolvido para alunos de programação                ║");
        System.out.println("  ╚═══════════════════════════════════════════════════════════════╝");

        boolean continuar = true;

        while (continuar) {
            System.out.println();
            System.out.println(LINHA_GROSSA);
            System.out.println("  MENU — Escolha qual princípio demonstrar:");
            System.out.println(LINHA_FINA);
            System.out.println("  [S] Responsabilidade Única");
            System.out.println("  [O] Aberto/Fechado");
            System.out.println("  [L] Substituição de Liskov");
            System.out.println("  [I] Segregação de Interface");
            System.out.println("  [D] Inversão de Dependência");
            System.out.println("  [T] Todos os princípios");
            System.out.println("  [0] Sair");
            System.out.println(LINHA_GROSSA);
            System.out.print("  Digite sua opção: ");

            String opcao = TECLADO.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "S" -> demoPrincipioS();
                case "O" -> demoPrincipioO();
                case "L" -> demoPrincipioL();
                case "I" -> demoPrincipioI();
                case "D" -> demoPrincipioD();
                case "T" -> {
                    demoPrincipioS();
                    demoPrincipioO();
                    demoPrincipioL();
                    demoPrincipioI();
                    demoPrincipioD();
                }
                case "0" -> continuar = false;
                default  -> System.out.println("  Opção inválida. Digite S, O, L, I, D, T ou 0.");
            }
        }

        TECLADO.close();
        System.out.println();
        System.out.println(LINHA_GROSSA);
        System.out.println("  Até logo!");
        System.out.println(LINHA_GROSSA);
    }

    // =========================================================================
    //  S — RESPONSABILIDADE ÚNICA
    // =========================================================================

    private static void demoPrincipioS() {
        cabecalho("S — RESPONSABILIDADE ÚNICA");
        System.out.println("  Cada classe tem UM único motivo para mudar.");
        System.out.println();
        System.out.println("  Vamos criar 4 objetos, cada um com sua responsabilidade:");
        System.out.println("    Pedido              → guarda os dados do pedido");
        System.out.println("    RepositorioDePedido → salva e busca pedidos");
        System.out.println("    ServicoDePedido     → valida e processa");
        System.out.println("    EmissorDeNota       → formata e imprime a nota");

        breakpoint("Criando os objetos RepositorioDePedido, EmissorDeNota e ServicoDePedido...");

        RepositorioDePedido repositorio = new RepositorioDePedido();
        EmissorDeNota       emissor     = new EmissorDeNota();
        ServicoDePedido     servico     = new ServicoDePedido(repositorio, emissor);

        System.out.println("  ✔ Objetos criados! Cada um cuida só do que é seu.");
        System.out.println("    ServicoDePedido recebeu o repositório e o emissor via construtor.");
        System.out.println("    Isso é INJEÇÃO DE DEPENDÊNCIA — ele não cria, recebe pronto.");

        breakpoint("Criando o Pedido #001 (Ana Souza — Notebook Dell Inspiron)...");

        Pedido pedido1 = new Pedido("001", "Ana Souza", "Notebook Dell Inspiron", 1, 3499.90);
        System.out.println("  ✔ Objeto Pedido criado: " + pedido1);

        breakpoint("Chamando servico.realizarPedido(pedido1) → valida → salva → emite nota...");

        secao("Passo a passo: validar → salvar → emitir nota");
        servico.realizarPedido(pedido1);

        breakpoint("Pedido #001 processado! Criando o Pedido #002 (Carlos Lima — Fone JBL)...");

        Pedido pedido2 = new Pedido("002", "Carlos Lima", "Fone Bluetooth JBL", 2, 289.90);
        System.out.println("  ✔ Objeto Pedido criado: " + pedido2);

        breakpoint("Processando o Pedido #002...");

        secao("Passo a passo: validar → salvar → emitir nota");
        servico.realizarPedido(pedido2);

        breakpoint("Ambos os pedidos processados! Consultando o repositório para ver o que foi salvo...");

        secao("Conteúdo do repositório (banco de dados simulado)");
        repositorio.buscarTodos().forEach(p ->
                System.out.println("  [Repositório] → " + p));

        System.out.println();
        System.out.println("  CONCLUSÃO DO PRINCÍPIO S:");
        System.out.println("  Se o banco de dados mudar  → só RepositorioDePedido muda.");
        System.out.println("  Se o layout da nota mudar  → só EmissorDeNota muda.");
        System.out.println("  Se as regras mudarem       → só ServicoDePedido muda.");
        System.out.println("  A classe Pedido nunca precisa mudar por causa disso.");
    }

    // =========================================================================
    //  O — ABERTO/FECHADO
    // =========================================================================

    private static void demoPrincipioO() {
        cabecalho("O — ABERTO/FECHADO");
        System.out.println("  CalculadoraDePedido NUNCA é modificada.");
        System.out.println("  Cada novo desconto = uma nova classe. O código antigo fica intacto.");

        double precoBase = 1000.00;
        System.out.printf("%n  Preço base para todos os exemplos: R$ %.2f%n", precoBase);

        breakpoint("Criando CalculadoraDePedido com DescontoSazonal('Black Friday', 25%)...");

        secao("Desconto Sazonal — Black Friday (25% off)");
        CalculadoraDePedido calcBlackFriday =
                new CalculadoraDePedido(new DescontoSazonal("Black Friday", 0.25));
        System.out.println("  ✔ CalculadoraDePedido criada com DescontoSazonal injetado.");

        breakpoint("Chamando calcBlackFriday.calcularPrecoFinal(1000.00)...");

        calcBlackFriday.calcularPrecoFinal(precoBase);

        breakpoint("Criando OUTRA CalculadoraDePedido — agora com DescontoSazonal('Natal', 15%)...");

        secao("Desconto Sazonal — Natal (15% off)");
        CalculadoraDePedido calcNatal =
                new CalculadoraDePedido(new DescontoSazonal("Natal", 0.15));

        breakpoint("Chamando calcNatal.calcularPrecoFinal(1000.00)...");

        calcNatal.calcularPrecoFinal(precoBase);

        breakpoint("Agora com DescontoFidelidade(OURO) — 20% off. CalculadoraDePedido nunca foi tocada!");

        secao("Desconto Fidelidade — Cliente Ouro (20% off)");
        CalculadoraDePedido calcOuro =
                new CalculadoraDePedido(new DescontoFidelidade(DescontoFidelidade.Categoria.OURO));

        breakpoint("Chamando calcOuro.calcularPrecoFinal(1000.00)...");

        calcOuro.calcularPrecoFinal(precoBase);

        breakpoint("Por último antes do novo: DescontoFidelidade(PRATA) — 10% off...");

        secao("Desconto Fidelidade — Cliente Prata (10% off)");
        CalculadoraDePedido calcPrata =
                new CalculadoraDePedido(new DescontoFidelidade(DescontoFidelidade.Categoria.PRATA));
        calcPrata.calcularPrecoFinal(precoBase);

        // ── NOVO DESCONTO ─────────────────────────────────────────────────────
        // Para adicionar DescontoEstudante, bastou criar o arquivo
        // DescontoEstudante.java e instanciá-lo aqui.
        // CalculadoraDePedido NÃO foi tocada — ela aceita qualquer Desconto.
        // ─────────────────────────────────────────────────────────────────────

        breakpoint("NOVO! DescontoEstudante('João Vitor') — 50% off. Apenas uma classe nova foi criada!");

        secao("Desconto Estudante — 50% off  ← NOVO TIPO ADICIONADO SEM MODIFICAR NADA");
        System.out.println("  ► O que foi feito para adicionar este desconto?");
        System.out.println("    1. Criado o arquivo DescontoEstudante.java  ← único passo");
        System.out.println("    2. Nada mais. CalculadoraDePedido, Desconto,");
        System.out.println("       DescontoSazonal e DescontoFidelidade ficaram intocados.");

        CalculadoraDePedido calcEstudante =
                new CalculadoraDePedido(new DescontoEstudante("João Vitor"));

        breakpoint("Chamando calcEstudante.calcularPrecoFinal(1000.00)...");

        calcEstudante.calcularPrecoFinal(precoBase);

        System.out.println();
        System.out.println("  CONCLUSÃO DO PRINCÍPIO O:");
        System.out.println("  Adicionamos 5 tipos de desconto diferentes.");
        System.out.println("  A classe CalculadoraDePedido NÃO foi modificada nenhuma vez.");
        System.out.println("  Cada novo desconto = uma nova classe isolada.");
        System.out.println("  Aberta para extensão, fechada para modificação.");
    }

    // =========================================================================
    //  L — SUBSTITUIÇÃO DE LISKOV
    // =========================================================================

    private static void demoPrincipioL() {
        cabecalho("L — SUBSTITUIÇÃO DE LISKOV");
        System.out.println("  Subclasses devem poder substituir a classe mãe sem quebrar nada.");
        System.out.println("  Forma é a classe mãe. Retangulo e Circulo são as filhas.");

        breakpoint("Criando uma lista mista com Retangulos e Circulos (todos do tipo Forma)...");

        // Todos declarados como "Forma" — o tipo concreto fica escondido.
        // Triangulo foi adicionado sem modificar CalculadoraDeArea — isso é Liskov!
        List<Forma> formas = List.of(
                new Retangulo(5.0, 3.0),
                new Circulo(4.0),
                new Retangulo(10.0, 2.5),
                new Circulo(1.5),
                new Triangulo(6.0, 4.0)   // NOVO: CalculadoraDeArea não foi tocada
        );

        System.out.println("  ✔ Lista criada com " + formas.size() + " formas:");
        for (Forma f : formas) {
            System.out.println("      → " + f.nome() + "  (tipo real: " + f.getClass().getSimpleName() + ")");
        }
        System.out.println();
        System.out.println("  Observe: a variável é do tipo 'Forma', mas o objeto real");
        System.out.println("  pode ser Retangulo, Circulo ou Triangulo — isso é POLIMORFISMO.");

        breakpoint("Criando CalculadoraDeArea e chamando areaTotal(formas)...");

        secao("CalculadoraDeArea processando a lista — chamando forma.area() para cada uma");
        CalculadoraDeArea calculadora = new CalculadoraDeArea();
        calculadora.areaTotal(formas);

        System.out.println();
        System.out.println("  CONCLUSÃO DO PRINCÍPIO L:");
        System.out.println("  CalculadoraDeArea nunca usou 'instanceof Retangulo',");
        System.out.println("  'instanceof Circulo' ou 'instanceof Triangulo'.");
        System.out.println("  Ela só chamou forma.area() — e cada subclasse respondeu corretamente.");
        System.out.println("  Triangulo foi adicionado criando apenas Triangulo.java.");
        System.out.println("  Isso é Liskov: subclasses substituem a mãe sem surpresas.");
    }

    // =========================================================================
    //  I — SEGREGAÇÃO DE INTERFACE
    // =========================================================================

    private static void demoPrincipioI() {
        cabecalho("I — SEGREGAÇÃO DE INTERFACE");
        System.out.println("  Nenhuma classe deve ser forçada a implementar métodos que não usa.");
        System.out.println("  Solução: dividir em interfaces pequenas e focadas.");
        System.out.println();
        System.out.println("  Interfaces criadas:");
        System.out.println("    Imprimivel  → só define imprimir()");
        System.out.println("    Salvavel    → só define salvar()");
        System.out.println("    Escaneavel  → só define escanear()");

        breakpoint("Criando uma Impressora simples — ela implementa APENAS Imprimivel...");

        secao("Impressora simples — só sabe imprimir");
        Impressora impressoraSimples = new Impressora("HP LaserJet 107a");
        System.out.println("  ✔ Impressora criada. Ela NÃO tem salvar() nem escanear().");
        System.out.println("    Se tentasse chamar impressoraSimples.salvar(...) → erro de compilação!");
        System.out.println("    O compilador protege o aluno de chamar algo que não existe.");

        breakpoint("Chamando impressoraSimples.imprimir(...)...");

        impressoraSimples.imprimir("Relatório Mensal de Vendas");

        breakpoint("Criando DispositivoMultiFuncional — implementa as 3 interfaces ao mesmo tempo...");

        secao("Dispositivo Multifuncional — implementa Imprimivel + Salvavel + Escaneavel");
        DispositivoMultiFuncional mfp = new DispositivoMultiFuncional("Canon PIXMA G3160");
        System.out.println("  ✔ MFP criado. Ele pode imprimir, salvar E escanear.");

        breakpoint("Chamando mfp.imprimir(...)...");

        mfp.imprimir("Contrato de Prestação de Serviços");

        breakpoint("Chamando mfp.salvar(...)...");

        mfp.salvar("Contrato de Prestação de Serviços", "/documentos/contratos/");

        breakpoint("Chamando mfp.escanear(...)...");

        String escaneado = mfp.escanear("RG_frente.jpg");
        System.out.println("  [Resultado]  Conteúdo escaneado: " + escaneado);

        breakpoint("Agora vamos ver polimorfismo: usando o MFP apenas como Imprimivel...");

        secao("Polimorfismo: MFP visto pela lente da interface Imprimivel");
        Imprimivel apenasImprimir = mfp;
        System.out.println("  ✔ A variável 'apenasImprimir' é do tipo Imprimivel.");
        System.out.println("    Ela aponta para o mesmo MFP, mas agora só enxerga imprimir().");
        System.out.println("    salvar() e escanear() ficam invisíveis — acoplamento mínimo!");

        breakpoint("Chamando apenasImprimir.imprimir(...)...");

        apenasImprimir.imprimir("Orçamento Q1 2025");

        System.out.println();
        System.out.println("  CONCLUSÃO DO PRINCÍPIO I:");
        System.out.println("  Impressora nunca foi forçada a ter salvar() ou escanear().");
        System.out.println("  MFP tem os 3 porque realmente os suporta — nenhuma mentira.");
        System.out.println("  Clientes recebem só a interface que precisam.");
    }

    // =========================================================================
    //  D — INVERSÃO DE DEPENDÊNCIA
    // =========================================================================

    private static void demoPrincipioD() {
        cabecalho("D — INVERSÃO DE DEPENDÊNCIA");
        System.out.println("  Alto nível (ServicoDeNotificacao) depende da ABSTRAÇÃO.");
        System.out.println("  Baixo nível (Email, SMS) implementa a abstração.");
        System.out.println();
        System.out.println("  SEM o princípio D (ruim):");
        System.out.println("    private EmailSender email = new EmailSender(); // hard-coded!");
        System.out.println("    // impossível trocar para SMS sem modificar a classe");
        System.out.println();
        System.out.println("  COM o princípio D (correto):");
        System.out.println("    private EnviadorDeMensagem enviador; // depende da abstração");
        System.out.println("    // qualquer implementação pode ser injetada pelo construtor");

        breakpoint("Criando ServicoDeNotificacao injetando EnviadorDeEmail no construtor...");

        secao("Cenário 1: notificações por E-mail");
        ServicoDeNotificacao porEmail =
                new ServicoDeNotificacao(new EnviadorDeEmail());
        System.out.println("  ✔ ServicoDeNotificacao criado com EnviadorDeEmail injetado.");
        System.out.println("    O serviço não sabe que é e-mail — só conhece EnviadorDeMensagem.");

        breakpoint("Chamando porEmail.enviarConfirmacaoDePedido(...)...");

        porEmail.enviarConfirmacaoDePedido("ana.souza@email.com", "001");

        breakpoint("Chamando porEmail.enviarAtualizacaoDeEnvio(...)...");

        porEmail.enviarAtualizacaoDeEnvio("ana.souza@email.com", "001", "BR112233445BR");

        breakpoint("Agora trocamos para SMS — criando NOVO ServicoDeNotificacao com EnviadorDeSms...");

        secao("Cenário 2: as mesmas notificações, agora por SMS");
        ServicoDeNotificacao porSms =
                new ServicoDeNotificacao(new EnviadorDeSms());
        System.out.println("  ✔ ServicoDeNotificacao criado com EnviadorDeSms injetado.");
        System.out.println("    O código do ServicoDeNotificacao é IDÊNTICO ao de cima.");
        System.out.println("    Só a implementação injetada mudou.");

        breakpoint("Chamando porSms.enviarConfirmacaoDePedido(...)...");

        porSms.enviarConfirmacaoDePedido("+55 31 98765-4321", "002");

        breakpoint("Chamando porSms.enviarAtualizacaoDeEnvio(...)...");

        porSms.enviarAtualizacaoDeEnvio("+55 31 98765-4321", "002", "BR554433221BR");

        // ── NOVO CANAL ────────────────────────────────────────────────────────
        // Para adicionar WhatsApp, bastou criar EnviadorDeWhatsApp.java.
        // ServicoDeNotificacao NÃO foi tocado — ele só conhece a abstração.
        // ─────────────────────────────────────────────────────────────────────

        breakpoint("NOVO! Trocando para WhatsApp — só a implementação injetada muda. ServicoDeNotificacao continua intocado!");

        secao("Cenário 3: as mesmas notificações, agora por WhatsApp  ← NOVO CANAL");
        System.out.println("  ► O que foi feito para adicionar WhatsApp?");
        System.out.println("    1. Criado EnviadorDeWhatsApp.java              ← único passo!");
        System.out.println("    2. Injetado abaixo no construtor de ServicoDeNotificacao.");
        System.out.println("    ServicoDeNotificacao.java: zero linhas modificadas.");

        ServicoDeNotificacao porWhatsApp =
                new ServicoDeNotificacao(new EnviadorDeWhatsApp("+55 31 98000-0000"));
        System.out.println("  ✔ ServicoDeNotificacao criado com EnviadorDeWhatsApp injetado.");
        System.out.println("    O serviço ainda não sabe qual canal usa — só chama enviador.enviar().");

        breakpoint("Chamando porWhatsApp.enviarConfirmacaoDePedido(...)...");

        porWhatsApp.enviarConfirmacaoDePedido("+55 31 99999-1234", "003");

        breakpoint("Chamando porWhatsApp.enviarAtualizacaoDeEnvio(...)...");

        porWhatsApp.enviarAtualizacaoDeEnvio("+55 31 99999-1234", "003", "BR998877665BR");

        System.out.println();
        System.out.println("  CONCLUSÃO DO PRINCÍPIO D:");
        System.out.println("  ServicoDeNotificacao NÃO foi modificado em nenhum dos 3 cenários.");
        System.out.println("  E-mail, SMS e WhatsApp: trocar o canal = trocar a implementação injetada.");
        System.out.println("  Para testar sem enviar mensagens de verdade: injete um EnviadorFalso.");
    }
}

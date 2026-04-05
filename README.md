# Tutorial SOLID — Princípios de Orientação a Objetos em Java

> Material didático desenvolvido para alunos da **Unipac**.
> 
> Prof. Rodrigo Fernandes dos Santos 
> Não é necessário ter experiência prévia com POO para acompanhar este projeto.

---

## O que você vai aprender

Este projeto ensina os **5 Princípios SOLID** — as boas práticas mais importantes
do desenvolvimento de software orientado a objetos.

| Letra | Nome em português | Ideia central |
|-------|-------------------|---------------|
| **S** | Responsabilidade Única | Cada classe faz **uma coisa só** |
| **O** | Aberto/Fechado | Adicione funcionalidades **sem modificar** código que já funciona |
| **L** | Substituição de Liskov | Subclasses devem poder **substituir** a classe mãe sem quebrar nada |
| **I** | Segregação de Interface | Interfaces pequenas e focadas, **nunca gigantes** |
| **D** | Inversão de Dependência | Dependa de **abstrações**, não de implementações concretas |

---

## Antes de tudo — conceitos básicos de POO

> Se você já conhece POO, pode pular esta seção.

### O que é uma Classe?

Uma **classe** é como uma "ficha cadastral" que descreve um tipo de objeto do mundo real.

```
Classe Pedido
  ├── número do pedido
  ├── nome do cliente
  ├── produto
  ├── quantidade
  └── preço unitário
```

### O que é um Objeto?

Um **objeto** é uma "ficha preenchida" — uma instância da classe com dados reais.

```java
// Criando um objeto do tipo Pedido
Pedido meuPedido = new Pedido("001", "João Silva", "Notebook", 1, 2999.90);
//     ↑            ↑         ↑       ↑              ↑          ↑   ↑
//   variável    palavra     numero  cliente       produto    qtd  preço
//               "new" cria
//               o objeto
```

### O que é uma Interface?

Uma **interface** é um **contrato**. Ela diz *o que* deve ser feito, mas não *como*.

```java
// Interface = contrato
interface Desconto {
    double aplicar(double preco); // toda classe que implementar DEVE ter este método
}

// Classes que "assinam o contrato"
class DescontoBlackFriday implements Desconto { ... }
class DescontoFidelidade      implements Desconto { ... }
```

### O que é Herança?

**Herança** é quando uma classe filha herda características da classe mãe.

```
Forma (mãe — abstrata)
  ├── Retangulo (filha)
  └── Circulo   (filha)
```

A palavra-chave é `extends`:
```java
class Retangulo extends Forma { ... } // Retangulo É UMA Forma
```

---

## Estrutura do projeto

```
SOLID/
├── pom.xml                          ← Configuração do projeto Maven
└── src/
    └── main/
        └── java/
            └── com/solid/
                ├── Main.java        ← Ponto de entrada (roda tudo)
                │
                ├── s/               ← Princípio S (Responsabilidade Única)
                │   ├── Pedido.java
                │   ├── RepositorioDePedido.java
                │   ├── ServicoDePedido.java
                │   └── EmissorDeNota.java
                │
                ├── o/               ← Princípio O (Aberto/Fechado)
                │   ├── Desconto.java
                │   ├── DescontoSazonal.java
                │   ├── DescontoFidelidade.java
                │   └── CalculadoraDePedido.java
                │
                ├── l/               ← Princípio L (Substituição de Liskov)
                │   ├── Forma.java
                │   ├── Retangulo.java
                │   ├── Circulo.java
                │   └── CalculadoraDeArea.java
                │
                ├── i/               ← Princípio I (Segregação de Interface)
                │   ├── Imprimivel.java
                │   ├── Salvavel.java
                │   ├── Escaneavel.java
                │   ├── Impressora.java
                │   └── DispositivoMultiFuncional.java
                │
                └── d/               ← Princípio D (Inversão de Dependência)
                    ├── EnviadorDeMensagem.java
                    ├── EnviadorDeEmail.java
                    ├── EnviadorDeSms.java
                    └── ServicoDeNotificacao.java
```

---

## Pré-requisitos

Você precisa instalar três ferramentas antes de rodar o projeto:

### 1. JDK 17 (Java Development Kit)

É o compilador e a máquina virtual Java.

**Windows:**
1. Acesse: https://adoptium.net
2. Clique em **Latest LTS Release** (versão 17 ou superior)
3. Baixe o instalador `.msi` e execute
4. Marque a opção **"Add to PATH"** durante a instalação

**Mac:**
```bash
brew install openjdk@17
```

**Verificar se foi instalado:**
```bash
java -version
```
Deve aparecer algo como: `openjdk version "17.x.x"`

---

### 2. Maven

É a ferramenta que compila e empacota o projeto Java.

**Windows:**
1. Acesse: https://maven.apache.org/download.cgi
2. Baixe o arquivo `apache-maven-x.x.x-bin.zip`
3. Extraia em uma pasta (ex: `C:\maven`)
4. Adicione `C:\maven\bin` à variável de ambiente `PATH`

**Mac:**
```bash
brew install maven
```

**Verificar se foi instalado:**
```bash
mvn -version
```
Deve aparecer: `Apache Maven 3.x.x`

---

### 3. Visual Studio Code

É o editor de código que vamos usar.

1. Acesse: https://code.visualstudio.com
2. Baixe e instale a versão para seu sistema operacional

**Extensões necessárias no VSCode** (instale após abrir o VSCode):

- **Extension Pack for Java** (da Microsoft) — pacote completo para Java
  - Pressione `Ctrl + Shift + X` (ou `Cmd + Shift + X` no Mac)
  - Pesquise: `Extension Pack for Java`
  - Clique em **Install**

---

## Como baixar e abrir o projeto no VSCode

### Passo 1 — Baixar o projeto

**Opção A — Git (recomendado):**
```bash
git clone <url-do-repositório>
cd "SOLID "
```

**Opção B — Download direto:**
1. Clique em **Code → Download ZIP** no GitHub
2. Extraia o arquivo `.zip` em uma pasta de sua preferência

---

### Passo 2 — Abrir no VSCode

1. Abra o **Visual Studio Code**
2. Vá em **File → Open Folder** (ou `Ctrl + K`, depois `Ctrl + O`)
3. Navegue até a pasta `SOLID` e clique em **Select Folder**

O VSCode vai detectar automaticamente que é um projeto Java/Maven.

> **Aguarde** — na primeira vez, o VSCode pode levar alguns minutos
> para baixar as dependências e configurar o projeto.

---

### Passo 3 — Compilar o projeto

Abra o **Terminal** dentro do VSCode:
- Menu: **Terminal → New Terminal**
- Atalho: `` Ctrl + ` `` (acento grave)

Digite o comando:
```bash
mvn clean package
```

O que cada parte significa:
- `mvn` — chama o Maven
- `clean` — apaga compilações anteriores
- `package` — compila e gera o arquivo `.jar` executável

Se tudo der certo, você verá:
```
[INFO] BUILD SUCCESS
```

E um arquivo `solid-principles.jar` será criado dentro da pasta `target/`.

---

### Passo 4 — Rodar o projeto

Ainda no terminal do VSCode, execute:
```bash
java -jar target/solid-principles.jar
```

Você verá a demonstração de todos os 5 princípios SOLID no console.

---

## Saída esperada no console

```
  ╔═══════════════════════════════════════════════════════════════╗
  ║         TUTORIAL SOLID — Princípios de POO em Java            ║
  ║         Desenvolvido para alunos de programação               ║
  ╚═══════════════════════════════════════════════════════════════╝

═════════════════════════════════════════════════════════════════
  S — RESPONSABILIDADE ÚNICA
═════════════════════════════════════════════════════════════════

  [Serviço]     Pedido validado: 001
  [Repositório] Pedido salvo: 001
  [Emissor]     ┌─────────────── NOTA FISCAL ───────────────┐
  [Emissor]     │ Pedido Nº  : 001                          │
  [Emissor]     │ Cliente    : Ana Souza                    │
  ...

═════════════════════════════════════════════════════════════════
  O — ABERTO/FECHADO
═════════════════════════════════════════════════════════════════

  [Calculadora] Desconto: Black Friday — 25% de desconto
  [Calculadora] Original: R$ 1000,00  →  Final: R$ 750,00
  ...
```

---

## Guia rápido de cada princípio

### S — Responsabilidade Única

**Analogia:** Em uma loja, o caixa cuida do pagamento, o estoquista cuida do estoque.
Cada funcionário tem **uma função**.

**No código:**
```
Pedido              → guarda os dados do pedido
RepositorioDePedido → salva e busca pedidos no banco
ServicoDePedido     → valida as regras de negócio
EmissorDeNota       → formata e imprime a nota fiscal
```

**Arquivo principal para estudar:** `src/main/java/com/solid/s/ServicoDePedido.java`

---

### O — Aberto/Fechado

**Analogia:** Uma tomada elétrica aceita qualquer aparelho novo sem precisar ser
"modificada" — você só precisa do plugue certo.

**No código:**
```
CalculadoraDePedido nunca muda.
Novos descontos são criados implementando a interface Desconto.
```

**Arquivo principal para estudar:** `src/main/java/com/solid/o/CalculadoraDePedido.java`

---

### L — Substituição de Liskov

**Analogia:** Se você pede "uma bebida", pode receber água, suco ou refrigerante —
qualquer um serve, sem surpresas.

**No código:**
```
CalculadoraDeArea recebe uma List<Forma>.
Pode conter Retangulo, Circulo, ou qualquer forma futura — tudo funciona.
```

**Arquivo principal para estudar:** `src/main/java/com/solid/l/CalculadoraDeArea.java`

---

### I — Segregação de Interface

**Analogia:** Uma faxineira não precisa saber consertar o ar-condicionado só porque
trabalha no mesmo prédio que o técnico.

**No código:**
```
Impressora implementa apenas Imprimivel (não é forçada a implementar salvar/escanear).
DispositivoMultiFuncional implementa Imprimivel + Salvavel + Escaneavel.
```

**Arquivo principal para estudar:** `src/main/java/com/solid/i/Impressora.java`

---

### D — Inversão de Dependência

**Analogia:** Um interruptor de luz não precisa saber se a lâmpada é LED ou fluorescente —
ele só liga e desliga. A abstração é "lâmpada".

**No código:**
```
ServicoDeNotificacao depende de EnviadorDeMensagem (abstração).
Pode usar EnviadorDeEmail ou EnviadorDeSms sem mudar uma linha do serviço.
```

**Arquivo principal para estudar:** `src/main/java/com/solid/d/ServicoDeNotificacao.java`

---

## Solução de problemas comuns

**Erro: `java: error: release version 17 not supported`**
> Sua versão do Java é antiga. Reinstale o JDK 17.

**Erro: `mvn: command not found`**
> Maven não está instalado ou não está no PATH. Reinstale seguindo o passo 2.

**Erro: `Could not find or load main class com.solid.Main`**
> Execute `mvn clean package` antes de rodar o `java -jar`.

**O VSCode não reconhece as classes Java (sublinhado vermelho em tudo)**
> Aguarde o VSCode terminar de indexar o projeto (barra de progresso no rodapé).
> Se persistir, pressione `Ctrl + Shift + P` e execute **"Java: Clean Java Language Server Workspace"**.

---

## Como estudar este projeto

1. **Comece pelo** `Main.java` — ele chama cada princípio em ordem
2. **Leia os comentários** — cada arquivo tem explicações detalhadas do zero
3. **Tente modificar** — o melhor aprendizado é quebrando e consertando:
   - Tente adicionar um novo tipo de desconto (princípio O)
   - Tente criar uma nova forma geométrica — Triângulo (princípio L)
   - Tente criar um enviador de WhatsApp (princípio D)

---

*Material produzido para a disciplina de Construção e Arquitetura de Software — Unipac*

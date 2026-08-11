# 🤖 Robot Game — Simulação de Robôs em Matriz (Java)

> **Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO)**  
> 👨‍💻 **Autores:** [Theodorico Vale](https://github.com/The-TheoVale) & [Miguel Maia](https://github.com/MiguelmxDev)

Um sistema de simulação interativo e autônomo em Java, onde robôs com diferentes inteligências e comportamentos navegam por um tabuleiro $4 \times 4$ em busca de alimento, desviando (ou colidindo) com obstáculos dinâmicos como bombas e rochas.

---

## 📌 Sumário
- [Visão Geral](#-visão-geral)
- [Arquitetura e Organização do Projeto](#-arquitetura-e-organização-do-projeto)
- [Conceitos de Orientação a Objetos Aplicados](#-conceitos-de-orientação-a-objetos-aplicados)
- [Hierarquia de Classes](#-hierarquia-de-classes)
  - [🤖 Robôs (`robos`)](#-robôs-robos)
  - [🪨 Obstáculos (`obstaculos`)](#-obstáculos-obstaculos)
  - [⚠️ Exceções (`excecoes`)](#️-exceções-excecoes)
  - [🎮 Modos de Simulação (`simulacao`)](#-modos-de-simulação-simulacao)
- [Como Compilar e Executar](#-como-compilar-e-executar)
- [Regras do Tabuleiro e Controles](#-regras-do-tabuleiro-e-controles)

---

## 🤖 Visão Geral

O **Robot Game** simula um ambiente matricial de $4 \times 4$ posições onde robôs tentam alcançar uma posição alvo ("Alimento"). O ambiente conta com suporte a:
- Movimentação manual via terminal ou simulação automática de turnos.
- Robôs autônomos com algoritmos variados (aleatório, memória de posições, estratégia de aproximação e memória de movimentos inválidos).
- Obstáculos como **Bombas** (destroem o robô) e **Rochas** (impedem a passagem e retornam o robô à casa anterior).
- Renderização visual colorida no terminal usando códigos ANSI.

---

## 📁 Arquitetura e Organização do Projeto

O código-fonte está estruturado de forma modular em pacotes bem delimitados:

```text
Robot_game/
├── README.md
├── excecoes/
│   └── MovimentoInvalidoException.java    # Exceção customizada para limites do tabuleiro
├── obstaculos/
│   ├── Obstaculo.java                     # Classe abstrata base para obstáculos
│   ├── Bomba.java                         # Obstáculo mortal que destrói o robô
│   └── Rocha.java                         # Obstáculo rígido que repele o robô
├── robos/
│   ├── Robo.java                          # Classe base com movimentação essencial
│   ├── RoboInteligente.java               # Evita repetição imediata de movimentos inválidos
│   ├── RoboEstrategico.java               # Move-se na direção do alimento
│   └── RoboMemoria.java                   # Armazena histórico de casas visitadas
└── simulacao/
    ├── App.java                           # Modo 1: Controle manual interativo
    ├── Main2.java                         # Modo 2: Simulação com 2 Robôs Básicos
    ├── Main3.java                         # Modo 3: Robô Básico vs Robô Inteligente
    ├── Main4.java                         # Modo 4: Simulação com Alocação de Obstáculos
    └── Main5.java                         # Modo 5: Robô Memória vs Robô Estratégico + Obstáculos
```

---

## 🧩 Conceitos de Orientação a Objetos Aplicados

- **Herança**: `RoboInteligente`, `RoboEstrategico` e `RoboMemoria` herdam da classe base `Robo`. `Bomba` e `Rocha` herdam da classe abstrata `Obstaculo`.
- **Polimorfismo**: Métodos como `mover()` e `bater()` são sobrescritos/implementados especificamente por cada subclasse. Listas genéricas como `ArrayList<Obstaculo>` processam diferentes obstáculos de maneira uniforme.
- **Abstração**: A classe `Obstaculo` define o contrato abstrato `bater(Robo bot)`, permitindo criar novos tipos de obstáculos sem alterar a lógica principal.
- **Tratamento de Exceções**: A exceção personalizada `MovimentoInvalidoException` é disparada ao tentar mover um robô para fora dos limites $0 \le X, Y \le 3$.

---

## 🏛️ Hierarquia de Classes

### 🤖 Robôs (`robos`)

| Classe | Descrição | Comportamento de Movimentação |
|---|---|---|
| `Robo` | Classe base para todos os robôs. | Movimenta-se por comandos (`up`, `down`, `left`, `right`) ou inteiros (`1`, `2`, `3`, `4`). |
| `RoboInteligente` | Herda de `Robo`. | Memoriza o último movimento inválido e escolhe outra direção aleatória, evitando falhar duas vezes seguidas na mesma borda. |
| `RoboEstrategico` | Herda de `Robo`. | Recebe as coordenadas do alimento e sempre escolhe o movimento que reduz a distância em relação ao objetivo. |
| `RoboMemoria` | Herda de `Robo`. | Guarda o histórico de coordenadas visitadas (`visitadas`) e prioriza avançar para posições inéditas. Se ficar preso, reseta a memória. |

---

### 🪨 Obstáculos (`obstaculos`)

| Classe | Efeito ao Colidir (`bater`) |
|---|---|
| `Obstaculo` | Classe abstrata que guarda a posição identificadora `id` (formato `YX`). |
| `Bomba` | Desativa a vida do robô (`bot.setVida(false)`) e é desarmada após a primeira explosão. |
| `Rocha` | Não causa dano ao robô, mas desfaz o movimento, retornando-o para a posição anterior (`antigoX`, `antigoY`). |

---

### ⚠️ Exceções (`excecoes`)

- **`MovimentoInvalidoException`**: Lançada quando qualquer robô tenta ultrapassar os limites do tabuleiro ($4 \times 4$). Exibe a direção que causou o erro.

---

### 🎮 Modos de Simulação (`simulacao`)

1. **`App.java` (Modo Manual)**:
   - O usuário escolhe a posição do Alimento.
   - Controla manualmente o Robô Azul digitando as direções a cada turno.
2. **`Main2.java` (Simulação com 2 Robôs Básicos)**:
   - Robô Azul (`Robo`) vs Robô Verde (`Robo`).
   - Movimentação aleatória em turnos alternados até um dos dois encontrar o alimento.
3. **`Main3.java` (Robô Básico vs Robô Inteligente)**:
   - Compara o desempenho do Robô Básico contra o `RoboInteligente`.
4. **`Main4.java` (Simulação com Obstáculos Customizáveis)**:
   - Permite que o usuário insira até 2 Bombas e 2 Rochas no tabuleiro.
   - Testa a sobrevivência de robôs contra os obstáculos.
5. **`Main5.java` (Simulação Avançada)**:
   - `RoboMemoria` vs `RoboEstrategico` em um mapa com Bombas e Rochas.
   - Exibe o histórico de memória e posições visitadas a cada rodada.

---

## ⚙️ Como Compilar e Executar

### 🛠️ Compilação

Na raiz do projeto (`Robot_game/`), execute o comando para compilar todos os módulos:

```bash
javac simulacao/App.java simulacao/Main2.java simulacao/Main3.java simulacao/Main4.java simulacao/Main5.java
```

### 🚀 Execução

Escolha o modo de jogo/simulação que deseja rodar:

```bash
# Modo 1: Jogo Interativo Manual
java simulacao.App

# Modo 2: Simulação 2x Robôs Básicos
java simulacao.Main2

# Modo 3: Simulação Básico vs Inteligente
java simulacao.Main3

# Modo 4: Simulação Básico vs Inteligente com Obstáculos
java simulacao.Main4

# Modo 5: Simulação Memória vs Estratégico com Obstáculos
java simulacao.Main5
```

---

## 🕹️ Regras do Tabuleiro e Controles

### Tabuleiro
- Grade $4 \times 4$ com eixos de coordenadas de $0$ a $3$:
  - Linha $0$ é a base inferior; Linha $3$ é o topo.
  - Coluna $0$ é a esquerda; Coluna $3$ é a direita.
  - A posição $(0,0)$ é o ponto de partida padrão dos robôs.

### Legenda da Matriz
- `*` : Célula vazia
- `B` : Robô Azul / Posição Inicial
- `G` : Robô Verde
- `A` : Alimento (Objetivo)

### Comandos de Movimento (Modo Manual - `App`)
| Comando Texto | Comando Numérico | Direção |
|---|---|---|
| `up` | `1` | ⬆️ Mover para cima (+1 em Y) |
| `down` | `2` | ⬇️ Mover para baixo (-1 em Y) |
| `right` | `3` | ➡️ Mover para a direita (+1 em X) |
| `left` | `4` | ⬅️ Mover para a esquerda (-1 em X) |
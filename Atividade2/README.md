
# 📱 App de Despesas Pessoais – Jetpack Compose

Aplicativo Android desenvolvido em **Kotlin com Jetpack Compose**, utilizando **arquitetura MVVM**, com o objetivo de **gerenciar ganhos, gastos e sonhos financeiros**, exibindo o **saldo atualizado em tempo real**, com **animações e visualizações gráficas**.

---

## 🧠 Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)**, com estado reativo utilizando **StateFlow**.

### Principais conceitos aplicados
- **Fonte única de verdade (ViewModel compartilhado)**
- **UI reativa com Jetpack Compose**
- **Separação clara de responsabilidades**
- **Navegação com Navigation Compose**
- **Componentes reutilizáveis**
- **Arquitetura escalável**

---

## 🗂️ Estrutura do Projeto

```text
br.com.treinamento.despesaspessoais
│
├── model
│   ├── Ganho.kt
│   ├── Gasto.kt
│   └── Sonho.kt
│
├── ui
│   ├── components
│   │   ├── CardAction.kt
│   │   ├── CardIncluirSonho.kt
│   │   ├── CardSonho.kt
│   │   └── FormFinance.kt
│   │
│   ├── navigation
│   │   └── NavGraph.kt
│   │
│   ├── screens
│   │   ├── ganhos
│   │   │   └── GanhosScreen.kt
│   │   │
│   │   ├── gastos
│   │   │   └── GastosScreen.kt
│   │   │
│   │   ├── home
│   │   │   └── HomeScreen.kt
│   │   │
│   │   └── sonhos
│   │       ├── SonhoFormScreen.kt
│   │       └── SonhoFormUIState.kt
│   │
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
│
├── utils
│   └── Util.kt
│
├── viewmodel
│   └── FinanceViewModel.kt
│
└── MainActivity.kt
```

---

## 📊 Funcionalidades Implementadas

### ✅ Página Inicial (Home)

- Card com **Saldo Atual**, calculado automaticamente:

```
Saldo = Total de Ganhos − Total de Gastos
```

- Cards reutilizáveis exibidos na Home:
  - **Ganhos**
  - **Gastos**
- Card de **Sonhos**, exibido apenas quando existe um sonho cadastrado
- Botões flutuantes (**FAB**) dentro dos cards para navegação
- Atualização automática dos valores ao incluir ganhos, gastos ou sonhos

---

### ✅ Ganhos

- Tela de listagem de ganhos
- Tela de inclusão de ganho
- Campo de descrição
- Valor com **máscara de moeda (R$)**
- Atualização automática do saldo após inclusão

---

### ✅ Gastos

- Tela de listagem de gastos
- Tela de inclusão de gasto
- Campo de descrição
- Valor com **máscara de moeda (R$)**
- Atualização automática do saldo após inclusão

---

### ✅ Sonhos / Desejos

- Cadastro de um sonho contendo:
  - Título
  - Data inicial
  - Data final
  - Valor do sonho
- Card exibido diretamente na Home com:
  - Título do sonho
  - Data final
  - Valor total
  - Valor faltante considerando o saldo disponível
- Atualização automática do card após salvar ou editar o sonho

---

## 💡 Reatividade e Estado

- O `FinanceViewModel` é **compartilhado no NavGraph**
- Estados financeiros utilizam **StateFlow**
- A UI utiliza `collectAsState()` para recomposição automática

```kotlin
val saldo by viewModel.saldoDisponivel.collectAsState()
```

---

## 🎞️ Animação de Valores (Saldo, Ganhos e Gastos)

Para melhorar a experiência do usuário, o app utiliza **animações suaves na atualização de valores financeiros**.

### 🔹 Tecnologia utilizada
- `animateFloatAsState` (Jetpack Compose)

### 🔹 Benefícios
- Transição visual suave ao adicionar ganhos ou gastos
- UX semelhante a aplicativos bancários
- Melhor percepção da mudança de valores

### 🔹 Exemplo de implementação

```kotlin
val saldo by viewModel.saldoDisponivel.collectAsState()

val saldoAnimado by animateFloatAsState(
    targetValue = saldo.toFloat(),
    animationSpec = tween(
        durationMillis = 600,
        easing = FastOutSlowInEasing
    ),
    label = "saldoAnimado"
)
```

---

## 📊 Gráfico de Progresso do Sonho

O **Card de Sonho** exibido na Home possui um **gráfico de progresso**, que representa visualmente o quanto do sonho já pode ser alcançado com o saldo atual.

### 🔹 Tipo de gráfico
- **Barra de progresso horizontal**
- Implementada com **Canvas (Jetpack Compose puro)**

> “Quanto do valor do sonho já está coberto pelo saldo atual?”

---

### 🔹 Cálculo do progresso do sonho

```kotlin
fun calcularProgressoSonho(valorSonho: Double, saldo: Double): Float {
    return (saldo / valorSonho)
        .coerceIn(0.0, 1.0)
        .toFloat()
}
```

---

### 🎞️ Animação do gráfico

O progresso do sonho é animado automaticamente sempre que o saldo muda.

```kotlin
val progressoAnimado by animateFloatAsState(
    targetValue = progresso,
    animationSpec = tween(600),
    label = "progressoSonho"
)
```

---

## 🎨 UI / UX

- Material Design 3
- Layout limpo e profissional
- Cards reutilizáveis
- FABs reduzidos (`SmallFloatingActionButton`)
- Animações suaves
- Gráficos integrados aos cards

---

## 🛠️ Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- StateFlow
- MVVM Architecture

---

## ✅ Status do Projeto

✔ Arquitetura profissional  
✔ Estado reativo  
✔ Animações de valores  
✔ Gráfico de progresso do sonho  
✔ Código organizado  
✔ Pronto para evolução (Room, gráficos, histórico)

---

Aplicativo desenvolvido com foco em **boas práticas**, **experiência do usuário** e **manutenibilidade**.

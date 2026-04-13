# 📱 App de Despesas Pessoais – Jetpack Compose

Aplicativo Android desenvolvido em **Kotlin com Jetpack Compose**, utilizando **arquitetura MVVM**, com o objetivo de **gerenciar ganhos, gastos e sonhos financeiros**, exibindo o **saldo atualizado em tempo real**.

---

## 🧠 Arquitetura

O projeto segue o padrão **MVVM (Model-View-ViewModel)**, com estado reativo utilizando **StateFlow**.

### Principais conceitos aplicados:
- **Fonte única de verdade (ViewModel compartilhado)**
- **UI reativa com Jetpack Compose**
- **Separação clara de responsabilidades**
- **Navegação com Navigation Compose**

---

## 🗂️ Estrutura do Projeto

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
│   │   │   ├── GanhosScreen.kt
│   │   │
│   │   ├── gastos
│   │   │   ├── GastosScreen.kt
│   │   │
│   │   ├── home
│   │   │   └── HomeScreen.kt
│   │   │
│   │   └── sonhos
│   │       ├── SonhoFormScreen.kt
│   │       └─ SonhoFormUIState.kt
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

## 📊 Funcionalidades Implementadas

### ✅ Página Inicial (Home)
- Card com **Saldo Atual**, calculado automaticamente:

Saldo = Total de Ganhos − Total de Gastos

- Cards reutilizáveis para:
- **Ganhos**
- **Gastos**
- Card de **Sonhos**, exibido apenas quando existe um sonho cadastrado.
- Botões flutuantes (FAB) nos cards para navegação.

---

### ✅ Ganhos
- Tela de listagem de ganhos
- Tela de inclusão de ganho com:
- Descrição
- Atualização automática do saldo após inclusão

---

### ✅ Gastos
- Tela de listagem de gastos
- Tela de inclusão de gasto com:
- Descrição
- Atualização automática do saldo após inclusão

---

### ✅ Sonhos / Desejos
- Cadastro de um sonho contendo:
- Título
- Data inicial
- Data final
- Valor do sonho
- Card exibido na Home com:
- Título do sonho
- Data final
- Valor total
- Valor faltante considerando o saldo disponível
- Atualização automática do card após salvar o sonho

---

## 💡 Reatividade e Estado

- O `FinanceViewModel` é **compartilhado no NavGraph**
- Estados financeiros utilizam **StateFlow**
- A UI usa `collectAsState()` para recomposição automática

```kotlin
val saldo by viewModel.saldoDisponivel.collectAsState()
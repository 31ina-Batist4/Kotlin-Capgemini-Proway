
# 📘 Projeto – Introdução ao Desenvolvimento com Kotlin

Este projeto tem como objetivo introduzir conceitos fundamentais do desenvolvimento em **Kotlin**, utilizando princípios de **Programação Orientada a Objetos (POO)**, manipulação de listas, tratamento de nulos e interação com o usuário via `Scanner`.

---

## 🎯 Objetivo do Projeto

Criar uma aplicação simples em Kotlin que permita gerenciar objetos de uma classe de modelo, oferecendo operações básicas de CRUD (Create, Read, Update, Delete) e seguindo boas práticas de programação.

---

## 📦 Funcionalidades Implementadas

### ✔️ 1. **Cadastrar**
Permite criar um novo objeto da classe de modelo, recebendo dados via `Scanner`.

### ✔️ 2. **Listar**
Exibe todos os registros criados, apresentando-os de forma organizada.

### ✔️ 3. **Pesquisar**
Busca um item específico da lista com base em algum atributo (ex.: ID ou nome).

### ✔️ 4. **Alterar**
Localiza um registro já existente e permite alterar seus dados.

### ✔️ 5. **Remover**
Exclui um registro da lista após encontrá-lo.

### ✔️ 6. **Finalizar**
Encerra a execução da aplicação de forma segura.

---

## 🧱 Estrutura do Projeto

### 🔹 **Classe de Modelo**
O projeto possui ao menos uma classe representando a entidade principal do sistema.
Exemplo: `data class Pessoa(val id: Int, var nome: String?, var email: String?)`

### 🔹 **Gerenciamento via Lista**
Os objetos da classe de modelo são armazenados e manipulados por meio de uma `MutableList`.

---

## 🛡️ Requisitos Técnicos Atendidos

### ✔️ **Null Safety**
O projeto utiliza tipos anuláveis (`String?`) e validações para evitar `NullPointerException`.

### ✔️ **Elvis Operator (`?:`)**
Aplica o operador Elvis para fornecer valores padrão ou tratar nulos.

```kotlin
val nomeSeguro = nome ?: "Desconhecido"
```

### ✔️ **Programação Orientada a Objetos**
A estrutura do código aplica:
- Encapsulamento
- Classes e objetos
- Métodos organizados para cada operação
- Separação de responsabilidades

### ✔️ **Validações**
Antes de cadastrar ou alterar dados, o sistema valida:
- Campos vazios
- Formatos incorretos
- Duplicidade de registros quando necessário

### ✔️ **Interação com o Usuário**
Toda entrada de dados ocorre via `Scanner`, permitindo uma experiência simples e interativa no console.

---

## ▶️ Como Executar

1. Instale o **Kotlin** ou utilize uma IDE como **IntelliJ IDEA**.
2. Adicione o arquivo `.kt` do projeto ao diretório-fonte.
3. Execute o método `main()`.
4. Use o menu exibido no console para navegar pelas opções.

---

## 📁 Estrutura Sugerida de Arquivos

```
src/
 ├── Main.kt
 ├── Pessoa.kt
 └── GerenciadorPessoas.kt
```

---




package com.example.treinamento

import com.example.treinamento.repository.CursoRepository
import com.example.treinamento.service.CursoService
import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    val service = CursoService(CursoRepository())

    loop@ while(true) {
        println()
        println("===== GESTÃO DE CURSOS =====")
        println("1) Cadastrar")
        println("2) Listar")
        println("3) Pesquisar")
        println("4) Alterar")
        println("5) Remover")
        println("6) Finalizar")
        print("Escolha uma opção: ")

        val opcao = sc.nextLine().toIntOrNull() ?: -1
        println()
        when(opcao) {
            1 -> cadastrar(sc, service)
            2 -> listar(service)
            3 -> pesquisar(sc, service)
            4 -> alterar(sc, service)
            5 -> remover(sc, service)
            6 -> {
                println("Finalizando... Até mais! ")
                break@loop
            }
            else -> println("Opção inválida. Tente novamente.")
        }
    }
}

private fun cadastrar(scanner: Scanner, service: CursoService) {
    println(">>> Cadastrar curso")

    val nome = lerNaoVazio(scanner, "Nome do curso: ")
    val descricao = lerOpcional(scanner, "Descrição (opcional, até 20 cacacteres): ")
    val cargaHoraria = lerIntObrigatorio(scanner, "Carga horária (horas): ", min = 1, max = 2000)
    val professor = lerOpcional(scanner, "Professor (opcional): ")
    val vagas = lerIntOpcional(scanner, "Vagas (opcional): ", min = 1, max = 30)

    try {
        val c = service.cadastrar(nome, descricao, cargaHoraria, professor, vagas)
        println("Curso cadastrado com ID ${c.id}.")
    } catch (e: IllegalArgumentException) {
        println("Erro de validação: ${e.message}")
    }
}

private fun listar(service: CursoService) {
    println(">>> Listar cursos")
    val lista = service.listar()
    if (lista.isEmpty()) {
        println("(nenhum registro)")
        return
    }
    lista.forEach { c ->
        println(
            "ID: ${c.id} | Nome: ${c.nomeCurso} | CH: ${c.cargaHoraria}h | " +
                    "Professor: ${c.professor ?: "(não informado)"} | " +
                    "Vagas: ${c.vagas?.toString() ?: "(sem limite)"} | " +
                    "Descrição: ${c.descricao ?: "(sem descrição)"}"
        )
    }
}

private fun pesquisar(scanner: Scanner, service: CursoService) {
    println(">>> Pesquisar")
    println("1) Por ID")
    println("2) Por nome (contem)")
    print("Escolha: ")
    val tipo = scanner.nextLine().toIntOrNull() ?: -1

    when (tipo) {
        1 -> {
            val id = lerId(scanner) ?: return
            val c = service.pesquisarPorId(id)
            if (c == null) {
                println("Curso $id nao encontrado .")
            } else {
                println("Encontrado: $c")
            }
        }
        2 -> {
            val termo = lerNaoVazio(scanner, "Informe parte do nome: ")
            val achados = service.pesquisarPorNome(termo)
            if (achados.isEmpty()) {
                println("Nenhum curso contém \"$termo\".")
            } else {
                achados.forEach { println(it) }
            }
        }
        else -> println("Opção inválida.")
    }
}

private fun alterar(scanner: Scanner, service: CursoService) {
    println(">>> Alterar curso")
    val id = lerId(scanner) ?: return

    val existente = service.pesquisarPorId(id)
    if (existente == null) {
        println("ID $id não encontrado.")
        return
    }
    println("Atual: $existente")
    println("Deixe em branco para manter. Use '-' para limpar campos opcionais.")

    print("Novo nome: ")
    val nome = scanner.nextLine().takeIf { it.isNotBlank() }

    print("Nova descrição: ")
    val descRaw = scanner.nextLine()
    val descricao: String? = when {
        descRaw.isBlank() -> null
        descRaw == "-" -> ""
        else -> descRaw
    }

    print("Nova carga horária (horas): ")
    val chRaw = scanner.nextLine()
    val novaCH: Int? = when {
        chRaw.isBlank() -> null
        else -> chRaw.toIntOrNull().also {
            if (it == null) println("Valor inválido de carga horária; mantendo atual.")
        }
    }

    print("Novo professor: ")
    val profRaw = scanner.nextLine()
    val professor: String? = when {
        profRaw.isBlank() -> null
        profRaw == "-" -> ""
        else -> profRaw
    }

    print("Novas vagas: ")
    val vagasRaw = scanner.nextLine()
    val vagasParaService: String? = when {
        vagasRaw.isBlank() -> null
        vagasRaw == "-" -> ""
        else -> vagasRaw
    }

    try {
        val ok = service.alterar(id, nome, descricao, novaCH, professor, vagasParaService)
        if (ok) println("Alterado com sucesso.") else println("Não foi possível alterar.")
    } catch (e: IllegalArgumentException) {
        println(" Erro de validação: ${e.message}")
    }
}

private fun remover(scanner: Scanner, service: CursoService) {
    println(">>> Remover curso")
    val id = lerId(scanner) ?: return
    print("Confirma remoção do ID $id? (s/N): ")
    val conf = scanner.nextLine().trim().lowercase()
    if (conf == "s" || conf == "sim") {
        val ok = service.remover(id)
        if (ok) println(" Removido.") else println("ID não encontrado.")
    } else {
        println("Operação cancelada.")
    }
}

private fun lerNaoVazio(scanner: Scanner, prompt: String): String {
    while (true) {
        print(prompt)
        val s = scanner.nextLine()
        if (s.isBlank()) println("Valor não pode ser vazio.") else return s
    }
}

private fun lerOpcional(scanner: Scanner, prompt: String): String? {
    print(prompt)
    val s = scanner.nextLine().trim()
    return s.ifBlank { null }
}

private fun lerIntObrigatorio(scanner: Scanner, prompt: String, min: Int, max: Int): Int {
    while (true) {
        print(prompt)
        val v = scanner.nextLine().toIntOrNull()
        if (v == null) {
            println("Informe um número inteiro.")
            continue
        }
        if (v !in min..max) {
            println("Valor deve estar entre $min e $max.")
            continue
        }
        return v
    }
}

private fun lerIntOpcional(scanner: Scanner, prompt: String, min: Int, max: Int): Int? {
    print(prompt)
    val raw = scanner.nextLine().trim()
    if (raw.isBlank()) return null
    val v = raw.toIntOrNull()
    if (v == null) {
        println("Valor inválido; ignorando (será mantido vazio).")
        return null
    }
    return if (v in min..max) v else {
        println("Valor fora do intervalo ($min..$max); ignorando.")
        null
    }
}

private fun lerId(scanner: Scanner): Int? {
    print("ID: ")
    val id = scanner.nextLine().toIntOrNull()
    if (id == null) {
        println("ID inválido.")
        return null
    }
    return id
}



package br.com.treinamento.agendadigital.model

data class Contato(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String,
    val nascimento: String,
    val endereco: Endereco
)

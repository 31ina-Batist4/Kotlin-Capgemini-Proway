package br.com.treinamento.agendadigital.model

data class Endereco(
    val cep: String ,
    val bairro: String,
    val logradouro: String,
    val numero: Int? = null,
    val estado: String,
    val cidade: String,
)

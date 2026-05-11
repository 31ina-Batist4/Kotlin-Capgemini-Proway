package br.com.treinamento.agendadigital.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contatos")
data class ContatoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,
    val email: String,
    val telefone: String,
    val nascimento: String,
    val cep: String,
    val bairro: String,
    val logradouro: String,
    val numero: Int? = null,
    val estado: String,
    val cidade: String,
)

package br.com.treinamento.despesaspessoais.ui.screens.sonhos

import java.util.Date

data class SonhoFormUiState(
    val titulo: String = "",
    val dataInicial: Date = Date(),
    val dataFinal: Date = Date(),
    val valor: String = ""
)


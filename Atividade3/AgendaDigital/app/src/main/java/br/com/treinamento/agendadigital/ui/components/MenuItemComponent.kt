package br.com.treinamento.agendadigital.ui.components

data class MenuItemComponent(
    val title: String,
    val onClick: () -> Unit,
    val id: Int? = null
)



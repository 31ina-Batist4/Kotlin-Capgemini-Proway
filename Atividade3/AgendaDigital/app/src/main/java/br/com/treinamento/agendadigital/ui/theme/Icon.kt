package br.com.treinamento.agendadigital.ui.theme

import android.graphics.drawable.Icon
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import br.com.treinamento.agendadigital.R


data class AppIcon(
    val icon: Int,
    val contentDescription: String,
    val tint : Color? = null
)

object AppIcons {
    val Phone = AppIcon(
        icon = R.drawable.phone,
        contentDescription =  "Ligar"
    )

    val Voltar = AppIcon(
        icon = R.drawable.back,
        contentDescription =  "Voltar"
    )

    val Editar = AppIcon(
        icon = R.drawable.edit,
        contentDescription =  "Editar"
    )
}


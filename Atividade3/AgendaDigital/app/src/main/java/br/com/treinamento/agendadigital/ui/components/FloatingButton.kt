package br.com.treinamento.agendadigital.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.treinamento.agendadigital.ui.theme.Purple40
import java.nio.file.WatchEvent

@Composable
fun FloatingButton(
    onAddClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onAddClick,
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Adicionar contato",
            tint = Purple40
        )
    }
}
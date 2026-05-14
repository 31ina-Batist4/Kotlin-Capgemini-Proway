package br.com.treinamento.agendadigital.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogComponent(
    show: Boolean,
    title: String,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
)
{
        if (show) {
            AlertDialog(
                onDismissRequest = { onDismiss() },

                title = { Text(title) },

                text = {Text(text)},

                confirmButton = {
                    TextButton(onClick = {
                        onConfirm()
                        onDismiss()
                    }) {
                        Text("Excluir", color = MaterialTheme.colorScheme.error)
                    }
                },

                dismissButton = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }

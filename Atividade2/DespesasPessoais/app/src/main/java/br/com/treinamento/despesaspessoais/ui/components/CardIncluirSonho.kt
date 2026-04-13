package br.com.treinamento.despesaspessoais.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.treinamento.despesaspessoais.ui.theme.Purple40

@Composable
fun CardIncluirSonho(onIncluir: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sonho",
                style = MaterialTheme.typography.titleLarge,
                color = Purple40
            )

            Text(
                text = "Nenhum sonho cadastrado",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(12.dp))

            Button(onClick = onIncluir) {
                Text("Incluir sonho")
            }
        }
    }
}
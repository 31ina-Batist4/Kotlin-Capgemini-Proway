package br.com.treinamento.despesaspessoais.ui.components

import FinanceViewModel
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.treinamento.despesaspessoais.model.Sonho
import br.com.treinamento.despesaspessoais.ui.theme.Purple40
import br.com.treinamento.despesaspessoais.utils.formatMoeda
import br.com.treinamento.despesaspessoais.utils.formatarData

@Composable
fun CardSonho(
    sonho: Sonho,
    saldoDisponivel: Double,
    onIncluirOuEditar: () -> Unit
) {
    val valorFaltante = (sonho.valor - saldoDisponivel).coerceAtLeast(0.0)
    val viewModel: FinanceViewModel = viewModel()
    val progress = viewModel.calcularProgresso(sonho.valor, saldoDisponivel)
    val percentual = (progress * 100).toInt()
    val faltante = (sonho.valor - saldoDisponivel).coerceAtLeast(0.0)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Sonhos",
                style = MaterialTheme.typography.titleLarge,
                color = Purple40
            )

            Text(
                text = sonho.titulo,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Data final: ${formatarData(sonho.dataFinal)}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Valor do sonho: ${formatMoeda(sonho.valor)}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = if (valorFaltante == 0.0)
                    "✅ Sonho alcançável com o saldo atual"
                else
                    "Faltam: ${formatMoeda(valorFaltante)}",
                color = if (valorFaltante == 0.0)
                    Color(0xFF2E7D32)
                else
                    MaterialTheme.colorScheme.error
            )

            Grafico(progress = progress)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onIncluirOuEditar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Editar sonho")
            }
        }
    }
}
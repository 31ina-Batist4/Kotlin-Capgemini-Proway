
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.treinamento.despesaspessoais.ui.theme.Purple40
import br.com.treinamento.despesaspessoais.ui.theme.Purple80
import br.com.treinamento.despesaspessoais.utils.formatMoeda

@Composable
fun CardAction(
    titulo: String,
    icon: Painter,
    valorTotal: Double,
    corIcone: Color,
    onAdicionarClick: () -> Unit
) {
    Box {
        Card(
            modifier = Modifier
                .width(170.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = titulo,
                        tint = corIcone
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Total",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = formatMoeda(valorTotal),
                    style = MaterialTheme.typography.titleMedium,
                    color = Purple40

                )
            }
        }

        FloatingActionButton(
            onClick = onAdicionarClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(50.dp)
                .padding(8.dp),
                 containerColor = Purple40
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar",
                tint = Purple80
            )
        }
    }
}
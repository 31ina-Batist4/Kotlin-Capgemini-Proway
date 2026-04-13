package br.com.treinamento.despesaspessoais.ui.screens.home

import CardAction
import FinanceViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.treinamento.despesaspessoais.ui.components.CardIncluirSonho
import br.com.treinamento.despesaspessoais.ui.components.CardSonho
import br.com.treinamento.despesaspessoais.ui.navigation.Routes
import br.com.treinamento.despesaspessoais.R
import br.com.treinamento.despesaspessoais.ui.theme.Purple40
import br.com.treinamento.despesaspessoais.utils.formatMoeda

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: FinanceViewModel
    ) {
    val sonho by viewModel.sonho.collectAsState()

    val totalGanhos by viewModel.totalGanhos.collectAsState()
    val totalGastos by viewModel.totalGastos.collectAsState()
    val saldo by viewModel.saldoDisponivel.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Minhas Finanças") }
            )
        }
    ) { padding ->

        Column (
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier.
                fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("Saldo Atual", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = formatMoeda(saldo),
                        style = MaterialTheme.typography.headlineMedium,
                        color = if(saldo >= 0) Purple40
                                else Color.Red
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CardAction(
                    titulo = "Ganhos",
                    icon = painterResource(R.drawable.baseline_arrow_upward_24) ,
                    valorTotal = (totalGanhos),
                    corIcone = Color(0xFF2E7D32),
                    onAdicionarClick = {
                        navController.navigate(Routes.GANHOS)
                    }

                )

                CardAction(
                    titulo = "Gastos",
                    icon = painterResource(R.drawable.baseline_arrow_downward_24),
                    valorTotal = totalGastos,
                    corIcone = MaterialTheme.colorScheme.error,
                    onAdicionarClick = {
                        navController.navigate(Routes.GASTOS)
                    }

                )
            }

            if(sonho !=  null) {
                CardSonho(
                    sonho = sonho!!,
                    saldoDisponivel = saldo,
                    onIncluirOuEditar = {
                        navController.navigate(Routes.SONHOS)
                    }
                )
            } else {
                CardIncluirSonho {
                    navController.navigate(Routes.SONHOS)
                }
            }
        }
    }
}
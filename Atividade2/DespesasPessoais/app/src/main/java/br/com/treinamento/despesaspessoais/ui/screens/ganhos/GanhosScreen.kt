package br.com.treinamento.despesaspessoais.ui.screens.ganhos

import FinanceViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.treinamento.despesaspessoais.ui.components.FormFinance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GanhosScreen(
    viewModel: FinanceViewModel,
    navController: NavController
) {
    FormFinance(
        titulo = "Adicionar Ganho",
        labelBotao = "Salvar",
        onSalvar = {
            descricao, valor ->
            viewModel.adicionarGanho(descricao, valor)
        },
        onVoltar = {
            navController.popBackStack()
        }
    )
}
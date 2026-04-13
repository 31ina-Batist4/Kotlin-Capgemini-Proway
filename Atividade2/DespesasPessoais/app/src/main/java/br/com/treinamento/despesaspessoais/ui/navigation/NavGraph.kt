package br.com.treinamento.despesaspessoais.ui.navigation

import FinanceViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.treinamento.despesaspessoais.ui.screens.ganhos.GanhosScreen
import br.com.treinamento.despesaspessoais.ui.screens.gastos.GastosScreen
import br.com.treinamento.despesaspessoais.ui.screens.home.HomeScreen
import br.com.treinamento.despesaspessoais.ui.screens.sonhos.SonhoFormScreen

object Routes {
    const val HOME = "home"
    const val GANHOS = "ganhos"
    const val GASTOS = "gastos"
    const val SONHOS = "sonhos"
}

@Composable
fun NavGraph(navController: NavHostController) {
    val viewModelFinance: FinanceViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {

            HomeScreen(
                navController,
                viewModel = viewModelFinance
            )
        }

        composable(Routes.GANHOS) {
            GanhosScreen(
                viewModel = viewModelFinance,
                navController
            )
        }

        composable(Routes.GASTOS) {
            GastosScreen(
                viewModel = viewModelFinance,
                navController,
            )
        }

        composable(Routes.SONHOS) {
            SonhoFormScreen(
                viewModel = viewModelFinance,
                onSalvarComSucesso = {
                    navController.popBackStack()
                }
            )
        }

    }
}


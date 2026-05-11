package br.com.treinamento.agendadigital.ui.navigation

import DetalheContatoScreen
import FormContatoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.treinamento.agendadigital.ui.screens.home.HomeScreen

object Routes {
    const val HOME = "home"
    const val FORM = "form_contato?id={id}"
    const val DETALHE = "detalhe_contato?id="

    fun form(id: Int) = "form_contato?id=${id}"
}

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                navController
            )
        }

        composable(
            route = "form_contato?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            FormContatoScreen(
                navController = navController,
                contatoId = id
            )
        }


        composable(
            route = "detalhe_contato?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DetalheContatoScreen(
                navController = navController,
                contatoId = id
            )
        }

    }

}
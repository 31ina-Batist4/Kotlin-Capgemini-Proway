package br.com.treinamento.agendadigital.ui.navigation

import DetalheContatoScreen
import FormContatoScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.treinamento.agendadigital.ui.components.Contatos
import br.com.treinamento.agendadigital.ui.screens.about.AboutScreen
import br.com.treinamento.agendadigital.ui.screens.home.HomeScreen

object Routes {
    const val HOME = "home"
    const val FORM = "form_contato?id={id}"
    const val DETALHE = "detalhe_contato?id="
    const val ABOUT = "about"
    const val CONTATOS = "contatos"

    fun form(id: Int) = "form_contato?id=${id}"
}

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Form: Screen("form_contato?id={id}", "Novo Contato")
    object About: Screen("about", "Sobre")
    object Contatos: Screen("contatos", "Contatos do dispositivo")
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

        composable(Routes.ABOUT) {
            AboutScreen(
                navController
            )
        }

        composable(Routes.CONTATOS) {
            Contatos()
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
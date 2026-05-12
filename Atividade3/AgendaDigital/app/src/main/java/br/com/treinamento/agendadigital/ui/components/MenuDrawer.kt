package br.com.treinamento.agendadigital.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.treinamento.agendadigital.ui.navigation.Screen
import kotlinx.coroutines.launch

data class MenuItem(
    val title: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuDrawer(
    navController: NavController,
    title: String,
    menuItems: List<MenuItem>,
    content: @Composable (PaddingValues) -> Unit
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val menuRotas = listOf(
        Screen.Home,
        Screen.Form,
        Screen.About,
        Screen.Contatos
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )

                menuRotas.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                           navController.navigate(screen.route) {
                               popUpTo("home") { inclusive = false }
                               launchSingleTop = true
                           }
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {Text(title) },
                    navigationIcon = {
                        IconButton(onClick = {
                                scope.launch { drawerState.open() }
                            }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "abrir menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            content(padding)
        }
    }

}
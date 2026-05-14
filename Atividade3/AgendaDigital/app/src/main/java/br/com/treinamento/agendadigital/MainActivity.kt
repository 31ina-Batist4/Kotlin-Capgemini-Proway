package br.com.treinamento.agendadigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.treinamento.agendadigital.ui.navigation.NavGraph
import br.com.treinamento.agendadigital.ui.theme.AgendaDigitalTheme



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaDigitalTheme {
                val navController = rememberNavController()
                NavGraph(navController)

            }
        }
    }
}



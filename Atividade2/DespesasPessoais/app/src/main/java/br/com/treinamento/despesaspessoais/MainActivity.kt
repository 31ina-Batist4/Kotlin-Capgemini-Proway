package br.com.treinamento.despesaspessoais

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import br.com.treinamento.despesaspessoais.ui.navigation.NavGraph
import br.com.treinamento.despesaspessoais.ui.theme.DespesasPessoaisTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           MaterialTheme{
                val navController = rememberNavController()
                NavGraph(navController )
           }
        }
    }
}


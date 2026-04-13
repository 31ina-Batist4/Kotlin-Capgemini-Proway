package br.com.treinamento.despesaspessoais.ui.screens.sonhos

import FinanceViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.treinamento.despesaspessoais.model.Sonho
import br.com.treinamento.despesaspessoais.utils.formatarData
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SonhoFormScreen(
    viewModel: FinanceViewModel,
    onSalvarComSucesso: () -> Unit
) {
   val uiState by viewModel.uiState.collectAsState()

    var mostrarDataInicial by remember { mutableStateOf(false) }
    var mostrarDataFinal by remember { mutableStateOf(false) }

    val datePickerInicial = rememberDatePickerState()
    val datePickerFinal = rememberDatePickerState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Novo Sonho") }
            )
        }
    ) { padding ->

        Column (
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = uiState.titulo,
                onValueChange = viewModel::onTituloChange,
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.valor,
                onValueChange = viewModel::onValorChange,
                label = { Text("Valor") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = formatarData(uiState.dataInicial),
                onValueChange = {},
                enabled = false,
                label = { Text("Data inicial") },
                trailingIcon = {
                    IconButton(onClick = { mostrarDataInicial = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = formatarData(uiState.dataFinal),
                onValueChange = {},
                enabled = false,
                label = { Text("Data final") },
                trailingIcon = {
                    IconButton(onClick = { mostrarDataFinal = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val sonho = Sonho(
                        titulo = uiState.titulo,
                        dataInicial = uiState.dataInicial,
                        dataFinal = uiState.dataFinal,
                        valor = uiState.valor.toDouble()
                    )
                    viewModel.salvarSonho(sonho = sonho)
                    onSalvarComSucesso()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Sonho")
            }
        }
    }

    if (mostrarDataInicial) {
        DatePickerDialog(
            onDismissRequest = { mostrarDataInicial = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerInicial.selectedDateMillis?.let {
                        viewModel.onDataInicialChange(Date(it))
                    }
                    mostrarDataInicial = false
                }) { Text("OK") }
            }
        ) {
            DatePicker(state = datePickerInicial)
        }
    }

    if (mostrarDataFinal) {
        DatePickerDialog(
            onDismissRequest = { mostrarDataFinal = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerFinal.selectedDateMillis?.let {
                        viewModel.onDataFinalChange(Date(it))
                    }
                    mostrarDataFinal = false
                }) { Text("OK") }
            }
        ) {
            DatePicker(state = datePickerFinal)
        }
    }
}

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.treinamento.agendadigital.viewmodel.ContatoUiState
import br.com.treinamento.agendadigital.viewmodel.ContatoViewModel

@Composable
fun FormContatoScreen(
    navController: NavController,
    contatoId: Int
) {
    val context = LocalContext.current
    val viewModel: ContatoViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory
            .getInstance(context.applicationContext as Application)
    )

    val state by viewModel.uiState.observeAsState(ContatoUiState())

    val isUpdate = contatoId != -1


    LaunchedEffect(contatoId) {
        if(isUpdate) {
            viewModel.getContato(contatoId)
        } else {
            viewModel.clearForm()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = if(isUpdate) "Atualizar Contato" else "Novo Contato",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value =  state.nome,
            onValueChange = { viewModel.onChangeNome(it) },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onChangeEmail(it)},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
        )

        OutlinedTextField(
            value = state.telefone,
            onValueChange = { viewModel.onChangeTelefone(it) },
            label = { Text("Telefone") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.nascimento,
            onValueChange = { viewModel.onChangeNascimento(it) },
            label = { Text("Nascimento") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.cep,
            onValueChange = {
                viewModel.onChangeCep(it)
                if(it.length == 8) {
                    viewModel.getEndrecoCep()
                }
                            },
            label = { Text("CEP") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        viewModel.getEndrecoCep()
                    }
                ) {
                    Icon(   Icons.Default.Search,
                        contentDescription = null)
                }
            },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = state.logradouro,
            onValueChange = { viewModel.onChangeLogradouro(it) },
            label = { Text("Logradouro") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(
                value = state.bairro,
                onValueChange = { viewModel.onChangeBairro(it) },
                label = { Text("Bairro") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = state.numero,
                onValueChange = { viewModel.onChangeNumero(it) },
                label = { Text("N°") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.width(80.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(
                value = state.cidade,
                onValueChange = { viewModel.onChangeCidade(it) },
                label = { Text("Cidade") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = state.estado,
                onValueChange = { viewModel.onChangeEstado(it) },
                label = { Text("Estado") },
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.width(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
               if(isUpdate) {
                   viewModel.updateContato()
               } else {
                   viewModel.insertContato()
               }
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if(isUpdate) "Atualizar" else "Salvar")
        }
    }
}


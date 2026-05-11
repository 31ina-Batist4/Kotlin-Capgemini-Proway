import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.treinamento.agendadigital.ui.navigation.Routes
import br.com.treinamento.agendadigital.viewmodel.ContatoUiState
import br.com.treinamento.agendadigital.viewmodel.ContatoViewModel

@Composable
fun DetalheContatoScreen(
    navController: NavController,
    contatoId : Int
) {

    val context = LocalContext.current

    val viewModel: ContatoViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory
            .getInstance(context.applicationContext as Application)
    )

    val contato by viewModel.getContatoById(contatoId).observeAsState()

 contato?.let { c ->
     Scaffold(
         floatingActionButton = {
             FloatingActionButton(
                 onClick = {
                     navController.navigate(Routes.form(c.id))
                     contatoId
                 }
             ) {
                 Icon(Icons.Default.Edit, contentDescription = "Editar")
             }
         }

     ) { padding ->

         Column(
             modifier = Modifier
                 .fillMaxSize()
                 .padding(padding)
                 .padding(16.dp),
             verticalArrangement = Arrangement.spacedBy(12.dp)
         ) {
             Row(verticalAlignment = Alignment.CenterVertically) {

                 Box(
                     modifier = Modifier
                         .size(72.dp)
                         .clip(CircleShape)
                         .background(Color(0xFF4CAF50)),
                     contentAlignment = Alignment.Center
                 ) {
                     Text(
                         text = c.nome.take(1),
                         fontSize = 28.sp,
                         color = Color.White
                     )
                 }

                 Spacer(modifier = Modifier.width(16.dp))

                 Text(
                     text = c.nome,
                     style = MaterialTheme.typography.titleLarge,
                     fontWeight = FontWeight.Bold,
                     modifier = Modifier.weight(1f)
                 )
                 Icon(
                     imageVector = Icons.Default.Delete,
                     contentDescription = "Excluir",
                     tint = Color.Red,
                     modifier = Modifier.size(28.dp)
                         .clickable {
                            viewModel.deleteContato(c)
                         }
                 )
             }

             Divider()

             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier.fillMaxWidth()
             ) {

                 Icon(Icons.Default.Phone, contentDescription = null)

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(
                     text = c.telefone,
                     modifier = Modifier.weight(1f)
                 )

                 val context = LocalContext.current

                 Icon(
                     imageVector = Icons.Default.Call,
                     contentDescription = "Ligar",
                     tint = Color.Green,
                     modifier = Modifier
                         .size(28.dp)
                         .clickable {
                             val intent = Intent(Intent.ACTION_DIAL).apply {
                                 data = Uri.parse("tel:${c.telefone}")
                             }
                             context.startActivity(intent)
                         }
                 )
             }

             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier.fillMaxWidth()
             ) {

                 Icon(Icons.Default.Email, contentDescription = null)

                 Spacer(modifier = Modifier.width(8.dp))

                 Text(
                     text = c.email,
                     modifier = Modifier.weight(1f)
                 )

                 val context = LocalContext.current

                 Icon(
                     imageVector = Icons.AutoMirrored.Filled.Send,
                     contentDescription = "Email",
                     tint = Color.Blue,
                     modifier = Modifier
                         .size(28.dp)
                         .clickable {

                             val intent = Intent(Intent.ACTION_SENDTO).apply {
                                 data = Uri.parse("mailto:${c.email}")
                             }

                             context.startActivity(intent)
                         }
                 )
             }

             Divider()

             Text(
                 text = "Nascimento: ${c.nascimento}",
                 style = MaterialTheme.typography.bodyLarge
             )

             Divider()

             Text(
                 text = "Endereço",
                 style = MaterialTheme.typography.titleMedium,
                 fontWeight = FontWeight.Bold
             )

             Text("${c.endereco.logradouro}, ${c.endereco.numero ?: "S/N"}")
             Text(c.endereco.bairro)
             Text("${c.endereco.cidade} - ${c.endereco.estado}")
             Text("CEP: ${c.endereco.cep}")
         }
     }
 }
}
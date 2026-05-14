package br.com.treinamento.agendadigital.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.treinamento.agendadigital.model.Contato
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import br.com.treinamento.agendadigital.ui.navigation.Routes
import br.com.treinamento.agendadigital.ui.theme.AppIcons
import br.com.treinamento.agendadigital.viewmodel.ContatoViewModel

@Composable
fun CardContato(
    contato: Contato,
    navController: NavController,
   viewModel: ContatoViewModel
) {

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    var listMenu = listOf<MenuItemComponent>(
        MenuItemComponent("Detalhe contato",  { navController.navigate("${Routes.DETALHE}${contato.id}")} ),
        MenuItemComponent("Editar contato", { navController.navigate("${Routes.FORM}${contato.id}") }),
        MenuItemComponent("Excluir contato", {  showDialog = true })
    )


    Card (
        modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
           Row(
               modifier = Modifier
                   .padding(12.dp)
                   .fillMaxWidth()
           ) {
               Spacer(modifier = Modifier.width(12.dp))

               Column(modifier = Modifier.weight(1f)) {
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           Box(
                               modifier = Modifier
                                   .size(48.dp)
                                   .clip(CircleShape)
                                   .background(Color(0xFF4CAF50)),
                               contentAlignment = Alignment.Center
                           ) {
                               Text(
                                   text = contato.nome.take(1),
                                   fontSize = 18.sp
                               )


                           }

                           DropMenu("", listMenu)

                       }

                       Spacer(modifier = Modifier.height(4.dp))

                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           Text(
                               text = contato.nome,
                               style = MaterialTheme.typography.titleMedium
                           )

                       }

                   Row(
                       modifier = Modifier.fillMaxWidth(),

                       verticalAlignment = Alignment.CenterVertically
                   ) {
                       Text(
                           text = contato.telefone,
                           style = MaterialTheme.typography.bodySmall
                       )

                       Icon(
                           painter = painterResource(id = AppIcons.Phone.icon),
                           contentDescription = "Telefone",
                           tint = Color(0xFF4CAF50),
                           modifier = Modifier.size(22.dp)
                               .clickable {
                                   val intent = Intent(Intent.ACTION_DIAL)
                                       .apply {
                                           data = Uri.parse("tel:${contato.telefone}")
                                       }
                                   context.startActivity(intent)
                               }
                               .clip(CircleShape)
                       )

                   }

               }
           }
    }
    AlertDialogComponent(
        showDialog,
        "Excluir Contato",
        "Confirma a exclusao do contato contato",
        onConfirm = { viewModel.deleteContato(contato)},
        onDismiss = { showDialog = false }
    )
}
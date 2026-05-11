package br.com.treinamento.agendadigital.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.treinamento.agendadigital.model.Contato
import android.content.Intent
import android.net.Uri
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.ui.platform.LocalContext


@Composable
fun CardContato(
    contato: Contato,
    onClick: (contatoId: Int) -> Unit
) {

    val context = LocalContext.current

    Card (
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ){
           Row(
              modifier = Modifier
                  .padding(10.dp),
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
                   Spacer(modifier = Modifier.width(12.dp))

                   Column(
                       modifier = Modifier.weight(1f)
                   ) {

                       Text(
                           text = contato.nome,
                           fontWeight = FontWeight.Bold,
                           fontSize = 16.sp
                       )

                       Spacer(modifier = Modifier.height(4.dp))

                       Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {

                           Text(
                               text = contato.telefone,
                               fontSize = 14.sp,
                               color = Color.DarkGray
                           )
                           Spacer(modifier = Modifier.weight(1f))

                           Icon(
                               imageVector = Icons.Default.Phone,
                               contentDescription = "Telefone",
                               tint = Color(0xFF4CAF50),
                               modifier = Modifier.size(28.dp)
                                   .clickable {
                                       val intent = Intent(Intent.ACTION_DIAL)
                                           .apply {
                                               data = Uri.parse("tel:${contato.telefone}")
                                           }
                                       context.startActivity(intent)
                                   }
                           )
                       }
                   }
           }
     Row (
         verticalAlignment = Alignment.CenterVertically,
         modifier = Modifier.fillMaxWidth()
         ){
         Spacer(modifier = Modifier.weight(1f))
         Icon(
             imageVector = Icons.Default.KeyboardArrowRight,
             contentDescription = "Detalhes",
             modifier = Modifier
                 .clickable {
                   onClick(contato.id)
                 }
                 .size(42.dp)
                 .padding(start = 8.dp, end = 4.dp)
         )

     }
    }
}
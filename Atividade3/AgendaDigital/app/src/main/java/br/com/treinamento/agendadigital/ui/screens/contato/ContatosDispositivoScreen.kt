package br.com.treinamento.agendadigital.ui.screens.contato

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import br.com.treinamento.agendadigital.ui.components.Contato
import br.com.treinamento.agendadigital.ui.components.obterContatos
import br.com.treinamento.agendadigital.ui.navigation.Screen
import br.com.treinamento.agendadigital.ui.theme.AppIcons


@Composable
fun ContatosDispositivosScreen() {

    val context = LocalContext.current

    var listaContatos by remember { mutableStateOf(listOf<Contato>()) }
    var jaSolicitou by remember { mutableStateOf(false) }
    val permissao = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

            retorno ->
        if(retorno) {
            listaContatos = obterContatos(context)
        }
    }


    val hasPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_CONTACTS
    )== PackageManager.PERMISSION_GRANTED

    LaunchedEffect(Unit) {
        if (!jaSolicitou) {
            jaSolicitou = true

            if (hasPermission) {
                listaContatos = obterContatos(context)
            } else {
                permissao.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(AppIcons.Voltar.icon),
                contentDescription = AppIcons.Voltar.contentDescription
            )
        }
        Text(
            text =  Screen.Contatos.title,
            style = MaterialTheme.typography.titleLarge
        )

        LazyColumn {
            items(listaContatos) { contato ->
                Card (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row() {
                        Text(
                            text =  "Nome: ${contato.nome}",
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Telefone: ${contato.telefone}",
                            modifier = Modifier.padding(10.dp)
                        )
                        Icon(
                            painter = painterResource(AppIcons.Phone.icon),
                            contentDescription = AppIcons.Phone.contentDescription,
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
    }
}
package br.com.treinamento.agendadigital.ui.components

import android.content.Context
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager
import android.Manifest

fun obterContatos(context: Context): List<String> {


    val lista = mutableListOf<String>()

    try {
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        )

        cursor?.use {
            val indiceNome = it.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
/*
            val indiceId = it.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            )
*/
            while(it.moveToNext()) {
              //  val id = it.getLong(indiceId)
                if(indiceNome != -1) {
                    val nome = it.getString(indiceNome) ?: ""
                    lista.add(nome)
                }
            }
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }

    return lista.distinct();

}

@Composable
fun Contatos() {

    val context = LocalContext.current

    var listaContatos by remember { mutableStateOf(listOf<String>()) }
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

    LazyColumn {
        items(listaContatos) { nome ->
            Text(
                text = nome,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider

data class Contato(
    val nome: String,
    val telefone: String
)

fun obterContatos(context: Context): List<Contato> {


    val lista = mutableListOf<Contato>()

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

            val indiceTelefone = it.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            while(it.moveToNext()) {
                if(indiceNome != -1) {
                    val nome = it.getString(indiceNome) ?: ""
                    val telefone = it.getString(indiceTelefone) ?: ""
                    lista.add(
                      Contato(
                          nome = nome,
                          telefone = telefone
                      )
                    )
                }
            }
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
    }

    return lista.distinct();

}



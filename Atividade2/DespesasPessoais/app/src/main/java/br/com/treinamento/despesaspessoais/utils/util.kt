package br.com.treinamento.despesaspessoais.utils

import android.icu.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun formatarData(date: Date): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(date)
}

fun formatMoeda(valor: Double): String {
    val formatar = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formatar.format(valor)
}


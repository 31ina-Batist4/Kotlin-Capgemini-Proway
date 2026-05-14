package br.com.treinamento.agendadigital.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation


class MascaraTelefone: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val input = text.text.filter { it.isDigit() }
        val formatted = buildString {
            for(i in input.indices) {
                when (i) {
                    0 -> append("(")
                    2 -> append(")")
                    7 -> append("-")
                }
                append(input[i])
            }
        }
        return TransformedText(
            AnnotatedString(formatted),
            OffsetMapping.Identity
        )
    }
}
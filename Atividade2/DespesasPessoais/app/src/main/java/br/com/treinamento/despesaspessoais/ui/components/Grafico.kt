package br.com.treinamento.despesaspessoais.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Grafico(
    progress: Float,
    modifier: Modifier = Modifier,
    background: Color = Color.LightGray,
    corProgresso: Color  = Color(0xFF2E7D32)
) {
   Canvas(
    modifier = modifier
        .fillMaxWidth()
        .height(10.dp)
   ) {
       val wh = size.width
       val ht = size.height

       drawRoundRect(
           color = corProgresso,
           size = Size(wh * progress, ht),
           cornerRadius = CornerRadius(50f)
       )

       // Fundo
       drawRoundRect(
           color = background,
           size = Size(wh, ht),
           cornerRadius = CornerRadius(50f)
       )

       // Progresso
       drawRoundRect(
           color = corProgresso,
           size = Size(wh * progress, ht),
           cornerRadius = CornerRadius(50f)
       )

   }
}
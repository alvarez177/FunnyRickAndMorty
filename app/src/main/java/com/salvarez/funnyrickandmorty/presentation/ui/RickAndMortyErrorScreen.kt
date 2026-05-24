package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salvarez.funnyrickandmorty.R

@Composable
fun RickAndMortyErrorScreen(
    modifier: Modifier,
    title: String,
    subtitle: String,
    buttonText: String,
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(color =  androidx.compose.ui.graphics.Color.White)
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.ic_connection_error
            ),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            style = MaterialTheme
                .typography
                .titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme
                .colorScheme
                .onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            style = MaterialTheme
                .typography
                .bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme
                .colorScheme
                .onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onButtonClick
        ) {
            Text(text = buttonText)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RickAndMortyScreenPreview() {
    RickAndMortyErrorScreen(
        modifier = Modifier,
        title = "Error de conexión a internet",
        subtitle = "Intentelo de nuevo.",
        buttonText = "Reintentar",
        onButtonClick = {}
    )
}
package com.salvarez.funnyrickandmorty.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.salvarez.funnyrickandmorty.R
import com.salvarez.funnyrickandmorty.model.RickAndMortyCharacterSupportingVisual

@Composable
fun RickAndMortyCharacterItem(rickAndMortyCharacterSupportingVisual: RickAndMortyCharacterSupportingVisual) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(150.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(rickAndMortyCharacterSupportingVisual.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placerholder_image),
                error = painterResource(R.drawable.image_not_found)
            )

            Spacer(modifier = Modifier.height(8.dp))

            BottomInformation(
                name = rickAndMortyCharacterSupportingVisual.name,
                status = rickAndMortyCharacterSupportingVisual.status
            )
        }
    }
}

@Composable
private fun BottomInformation(
    name: String,
    status: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = status,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RickAndMortyItemPreview() {
    RickAndMortyCharacterItem(
        rickAndMortyCharacterSupportingVisual = RickAndMortyCharacterSupportingVisual(
            id = "id",
            name = "Rick Sanchez",
            status = "Alive",
            image = ""
        )
    )
}
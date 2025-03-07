package org.marton.studio.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.marton.studio.movies
import org.marton.studio.ui.screens.Screen
import org.marton.studio.ui.screens.components.MyTopAppBar

@Composable
fun DetailScreen(movie: String, onBack: ()-> Unit) {
    val movieDetail = movies.find { it.id.toString() == movie } ?: return
    Screen {
        Scaffold(
            topBar =
            {
                MyTopAppBar(
                    title = movieDetail.title,
                    onBackClick = { onBack() },
                    onSettingsClick = {}
                )
            }

        ) { padding ->
            Column(modifier = Modifier.padding(padding).verticalScroll(rememberScrollState())) {
                            AsyncImage(
                                model = movieDetail.poster,
                                contentDescription = movieDetail.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                                    .aspectRatio(16 / 9f)
                            )
                Text(
                    text = movieDetail.title,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Button(onClick = { onBack() }) {
                    Text(text = "Volver")
                }
            }

        }
    }
}
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
import org.marton.studio.ui.screens.Screen
import org.marton.studio.ui.screens.components.LoadingIndicator
import org.marton.studio.ui.screens.components.MyTopAppBar

@Composable
fun DetailScreen(viewModel: DetailViewModel, onBack: () -> Unit) {
    val state = viewModel.state
    Screen {
        Scaffold(
            topBar =
            {
                MyTopAppBar(
                    title = state.movie?.title ?: "",
                    onBackClick = { onBack() },
                    onSettingsClick = {}
                )
            }

        ) { padding ->

            LoadingIndicator(eneable = state.loading)
            state.movie.let {
                Column(modifier = Modifier.padding(padding).verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        model = state.movie?.backdrop,
                        contentDescription = state.movie?.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxWidth()
                            .aspectRatio(16 / 9f)
                    )
                    Text(
                        text = state.movie?.title ?: "",
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
}
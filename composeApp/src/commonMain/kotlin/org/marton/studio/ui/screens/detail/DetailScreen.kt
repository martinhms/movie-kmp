package org.marton.studio.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import coil3.compose.AsyncImage
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource
import org.marton.studio.movies
import org.marton.studio.ui.screens.Screen
import org.marton.studio.ui.screens.components.MyTopAppBar

class DetailScreenVoyager(private val movie: String) : Screen {

    @Composable
    override fun Content() {
        DetailScreen(movie)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movie: String) {
    val navigator = LocalNavigator.current
    val movieDetail = movies.find { it.id.toString() == movie } ?: return
    Screen {
        Scaffold(
            topBar =
            {
                MyTopAppBar(
                    title = movieDetail.title,
                    onBackClick = { navigator?.pop() },
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
                Button(onClick = { navigator?.pop() }) {
                    Text(text = "Volver")
                }
            }

        }
    }
}
package org.marton.studio.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.language
import moviesapp.composeapp.generated.resources.popular
import moviesapp.composeapp.generated.resources.title
import moviesapp.composeapp.generated.resources.votes
import org.jetbrains.compose.resources.stringResource
import org.marton.studio.Movie
import org.marton.studio.ui.screens.Screen
import org.marton.studio.ui.screens.components.LoadingIndicator
import org.marton.studio.ui.screens.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel, onBack: () -> Unit) {
    val state = viewModel.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Screen {
        Scaffold(
            topBar =
                {
                    MyTopAppBar(
                        title = state.movie?.title ?: "",
                        onBackClick = { onBack() },
                        onSettingsClick = {},
                        scrollBehavior = scrollBehavior
                    )
                },
            bottomBar = {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { onBack() }) {
                        Text(text = "Volver")
                    }
                }

            }

        ) { padding ->
            LoadingIndicator(eneable = state.loading, modifier = Modifier.padding(padding))
            state.movie.let {
                MovieDetailComponent(modifier = Modifier.padding(padding), it, onBack)
            }
        }
    }
}

@Composable
private fun MovieDetailComponent(
    modifier: Modifier = Modifier,
    movie: Movie?,
    onBack: () -> Unit
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        AsyncImage(
            model = movie?.backdrop,
            contentDescription = movie?.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
        Text(
            text = movie?.title ?: "",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = movie?.overview ?: "",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = buildAnnotatedString {
                property(stringResource(Res.string.title), movie?.originalTitle ?: "")
                property(stringResource(Res.string.language), movie?.originalLanguage ?: "")
                property(stringResource(Res.string.popular), movie?.popularity.toString() ?: "")
                property(stringResource(Res.string.votes), movie?.voteAverage.toString() ?: "")
            },
            modifier = Modifier.padding(16.dp).fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        )


    }
}

private fun AnnotatedString.Builder.property(name: String, value: String, end: Boolean = false) {
    withStyle(ParagraphStyle(lineHeight = 18.sp)) {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!end) {
            append("\n")
        }
    }
}

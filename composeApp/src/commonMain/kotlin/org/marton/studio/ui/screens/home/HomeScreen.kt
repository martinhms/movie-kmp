package org.marton.studio.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import coil3.compose.AsyncImage
import org.marton.studio.Movie
import org.marton.studio.movies
import org.marton.studio.ui.screens.Screen
import org.marton.studio.ui.screens.bottombar.BottomBarScreen
import org.marton.studio.ui.screens.components.MyTopAppBar
import org.marton.studio.ui.screens.detail.DetailScreenVoyager

class HomeScreenVoyager : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Screen {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        onBackClick = {},
                        firstAction = true,
                        onSettingsClick = {navigator?.push(BottomBarScreen())}
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { padding ->
                LazyVerticalGrid(
                    modifier = Modifier.padding(padding),
                    columns = GridCells.Adaptive(120.dp),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(items = movies, key = { it.id }) {
                        MovieItem(movie = it) { movie ->
                            navigator?.push(DetailScreenVoyager(movie))
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun MovieItem(movie: Movie, onMovieClick: (String) -> Unit) {
        Column {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(2 / 3f)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { onMovieClick(movie.id.toString()) }
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
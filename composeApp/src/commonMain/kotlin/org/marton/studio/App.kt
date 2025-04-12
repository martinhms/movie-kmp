package org.marton.studio

import androidx.compose.runtime.Composable
import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.api_key
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.marton.studio.ui.screens.navigations.NavigationCompose

@Composable
@Preview
fun App() {
    val apiKey = stringResource(Res.string.api_key)
    NavigationCompose()
}


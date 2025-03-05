package org.marton.studio

import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.marton.studio.ui.screens.home.HomeScreenVoyager

@Composable
@Preview
fun App() {
    Navigator(screen = HomeScreenVoyager())
   //DetailScreen()
}


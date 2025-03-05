package org.marton.studio.ui.screens.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.marton.studio.ui.screens.components.MyTopAppBar

class BottomBarScreen : Screen {

    @Composable
    override fun Content() {
        val nav = LocalNavigator.current
        TabNavigator(
            HomeTab,
            tabDisposable = {
                TabDisposable(
                    it,
                    listOf(HomeTab, FavTab, ProfileTab)
                )
            }
        ) {
            Scaffold(
                topBar = {
                    MyTopAppBar(
                        title = it.current.options.title,
                        onBackClick = {nav?.pop()},
                        firstAction = false,
                        onSettingsClick = {}
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val tabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = it.current.key == HomeTab.key,
                            label = { Text(text = HomeTab.options.title) },
                            icon = {
                                HomeTab.options.icon?.let {
                                    Icon(
                                        painter = HomeTab.options.icon!!,
                                        contentDescription = HomeTab.options.title
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = HomeTab },
                        )
                        NavigationBarItem(
                            selected = it.current.key == FavTab.key,
                            label = { Text(text = FavTab.options.title) },
                            icon = {
                                FavTab.options.icon?.let {
                                    Icon(
                                        painter = FavTab.options.icon!!,
                                        contentDescription = FavTab.options.title
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = FavTab },
                        )
                        NavigationBarItem(
                            selected = it.current.key == ProfileTab.key,
                            label = { Text(text = ProfileTab.options.title) },
                            icon = {
                                ProfileTab.options.icon?.let {
                                    Icon(
                                        painter = ProfileTab.options.icon!!,
                                        contentDescription = ProfileTab.options.title
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = ProfileTab },
                        )
                    }
                }
            ) {
                CurrentTab()
            }
        }
    }
}
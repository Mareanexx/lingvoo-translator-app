package ru.mareanexx.lingvootranslatorapp.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.mareanexx.core.navigation.NavigationGraphRoutes
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.presentation.components.navbar.BottomNavigationBar
import ru.mareanexx.feature_translate.presentation.screen.favorites.FavoritesScreen
import ru.mareanexx.feature_translate.presentation.screen.history.TranslationHistoryScreen
import ru.mareanexx.feature_translate.presentation.screen.translate.TranslatorScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = currentRouteAsNavigationRoute(navController) ?: NavigationGraphRoutes.Home,
                onTabSelected = { route ->
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationGraphRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = NavigationGraphRoutes.Favorites.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ){
                FavoritesScreen()
            }
            composable(
                route = NavigationGraphRoutes.Home.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) {
                TranslatorScreen()
            }
            composable(
                route = NavigationGraphRoutes.History.route,
                enterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) }
            ) {
                TranslationHistoryScreen()
            }
        }
    }
}

@Composable
private fun currentRouteAsNavigationRoute(navController: NavHostController): NavigationGraphRoutes? {
    val backstackEntry by navController.currentBackStackEntryAsState()
    val route = backstackEntry?.destination?.route ?: return null
    return NavigationGraphRoutes.entries.firstOrNull { it.route == route }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAppNavHost() {
    LingvooTranslatorAppTheme {
        AppNavHost()
    }
}

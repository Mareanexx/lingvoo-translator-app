package ru.mareanexx.feature_translate.presentation.components.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.navigation.NavigationGraphRoutes
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R

@Composable
fun BottomNavigationBar(
    selectedTab: NavigationGraphRoutes,
    onTabSelected: (NavigationGraphRoutes) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(top = 12.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CommonNavItem(
            icon = R.drawable.star_icon,
            contentDescription = R.string.cd_navigate_to_favorites_screen,
            selected = selectedTab == NavigationGraphRoutes.Favorites,
            onTabSelected = { onTabSelected(NavigationGraphRoutes.Favorites) }
        )

        HomeNavItem(
            selected = selectedTab == NavigationGraphRoutes.Home,
            onTabSelected = { onTabSelected(NavigationGraphRoutes.Home) }
        )

        CommonNavItem(
            icon = R.drawable.history_icon,
            contentDescription = R.string.cd_navigate_to_history_screen,
            selected = selectedTab == NavigationGraphRoutes.History,
            onTabSelected = { onTabSelected(NavigationGraphRoutes.History) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomNavigationBarHomeActive() {
    val selectedTab = remember { mutableStateOf(NavigationGraphRoutes.Home) }

    LingvooTranslatorAppTheme {
        BottomNavigationBar(
            selectedTab = selectedTab.value,
            onTabSelected = { selectedTab.value = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomNavigationBarFavoritesActive() {
    val selectedTab = remember { mutableStateOf(NavigationGraphRoutes.Favorites) }

    LingvooTranslatorAppTheme {
        BottomNavigationBar(
            selectedTab = selectedTab.value,
            onTabSelected = { selectedTab.value = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBottomNavigationBarHistoryActive() {
    val selectedTab = remember { mutableStateOf(NavigationGraphRoutes.History) }

    LingvooTranslatorAppTheme {
        BottomNavigationBar(
            selectedTab = selectedTab.value,
            onTabSelected = { selectedTab.value = it }
        )
    }
}
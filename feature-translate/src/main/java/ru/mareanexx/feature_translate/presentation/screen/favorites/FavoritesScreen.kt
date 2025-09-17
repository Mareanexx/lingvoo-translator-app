package ru.mareanexx.feature_translate.presentation.screen.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.mareanexx.core.ui.theme.HeaderShape
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation
import ru.mareanexx.feature_translate.presentation.components.BlueScreenHeader
import ru.mareanexx.feature_translate.presentation.components.EmptyContentPlaceholder
import ru.mareanexx.feature_translate.presentation.screen.favorites.components.EventHandler
import ru.mareanexx.feature_translate.presentation.screen.favorites.components.FavoritesLazyColumn
import ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel.FavoritesViewModel
import java.time.OffsetDateTime

@Composable
fun FavoritesScreen(
    onNavigateToMain: () -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.favorites.collectAsState()

    EventHandler(
        eventFlow = viewModel.eventFlow,
        title = R.string.dialog_ttl_delete_all_favorites,
        mainText = R.string.dialog_sub_delete_favorites,
        onDeleteClicked = { viewModel.deleteAll() }
    )

    FavoritesScreenContent(
        favorites = favorites,
        onDeleteAllClicked = { viewModel.showDeleteDialog() },
        onCopyToClipboard = { viewModel.copyToClipboard(it) },
        onDeleteOneItem = { viewModel.deleteOneFavorite(it) },
        onNavigateToMain = onNavigateToMain
    )
}

@Composable
fun FavoritesScreenContent(
    favorites: List<FavoriteTranslation>,
    onDeleteAllClicked: () -> Unit,
    onCopyToClipboard: (String) -> Unit,
    onDeleteOneItem: (FavoriteTranslation) -> Unit,
    onNavigateToMain: () -> Unit
) {
    Column {
        BlueScreenHeader(
            headText = R.string.header_favorites,
            buttonIcon = R.drawable.delete_icon,
            cdButton = R.string.dialog_sub_delete_favorites,
            onButtonClicked = onDeleteAllClicked
        )

        if (favorites.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                        shape = HeaderShape
                    )
                    .background(
                        color = MaterialTheme.colorScheme.secondary,
                        shape = MaterialTheme.shapes.large
                    )
            ) {
                EmptyContentPlaceholder(
                    title = R.string.ttl_empty_favorites_list,
                    subText = R.string.sub_ttl_empty_favorites_list,
                    modifier = Modifier.padding(vertical = 46.dp),
                    onButtonClicked = onNavigateToMain
                )
            }
        } else {
            FavoritesLazyColumn(
                favorites = favorites,
                onCopyToClipboard = onCopyToClipboard,
                onDeleteOneItem = onDeleteOneItem
            )
        }
    }
}

@Preview(name = "FavoritesScreen empty list", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFavoritesScreenEmpty() {
    LingvooTranslatorAppTheme {
        FavoritesScreenContent(favorites = emptyList(), {}, {}, {}, {})
    }
}

@Preview(name = "FavoritesScreen one favorite translation", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFavoritesScreenOneElementList() {
    LingvooTranslatorAppTheme {
        FavoritesScreenContent(
            favorites = listOf(FavoriteTranslation(1, "Hello", "Привет", OffsetDateTime.now())),
            {}, {}, {}, {}
        )
    }
}

@Preview(name = "FavoritesScreen ten favorite translations", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewFavoritesScreenFilledList() {
    LingvooTranslatorAppTheme {
        FavoritesScreenContent(
            favorites = listOf(
                FavoriteTranslation(1, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(2, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(3, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(4, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(5, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(6, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(7, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(8, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(9, "Hello", "Привет", OffsetDateTime.now()),
                FavoriteTranslation(10, "Hello", "Привет", OffsetDateTime.now())
            ), {}, {}, {}, {}
        )
    }
}

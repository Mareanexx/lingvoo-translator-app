package ru.mareanexx.feature_translate.presentation.screen.history

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
import ru.mareanexx.feature_translate.domain.entity.HistoryWithFavorite
import ru.mareanexx.feature_translate.presentation.components.BlueScreenHeader
import ru.mareanexx.feature_translate.presentation.components.EmptyContentPlaceholder
import ru.mareanexx.feature_translate.presentation.screen.favorites.components.EventHandler
import ru.mareanexx.feature_translate.presentation.screen.history.components.HistoryLazyColumn
import ru.mareanexx.feature_translate.presentation.screen.history.viewmodel.TranslationHistoryViewModel
import java.time.LocalDate

@Composable
fun TranslationHistoryScreen(
    onNavigateToMain: () -> Unit,
    viewModel: TranslationHistoryViewModel = hiltViewModel()
) {
    val history by viewModel.history.collectAsState()

    EventHandler(
        eventFlow = viewModel.eventFlow,
        title = R.string.dialog_ttl_clear_history,
        mainText = R.string.dialog_sub_clear_history,
        onDeleteClicked = { viewModel.deleteAll() },
    )

    TranslationHistoryScreenContent(
        history = history,
        onDeleteAllClicked = { viewModel.showDeleteDialog() },
        onCopyToClipboard = { viewModel.copyToClipboard(it) },
        onDeleteOneItem = { viewModel.deleteOneItem(it) },
        onAddFavoriteButtonClicked = { viewModel.switchFavorite(it) },
        onNavigateToMain = onNavigateToMain
    )
}

@Composable
fun TranslationHistoryScreenContent(
    history: List<HistoryWithFavorite>,
    onDeleteAllClicked: () -> Unit,
    onCopyToClipboard: (String) -> Unit,
    onDeleteOneItem: (HistoryWithFavorite) -> Unit,
    onAddFavoriteButtonClicked: (HistoryWithFavorite) -> Unit,
    onNavigateToMain: () -> Unit
) {
    Column {
        BlueScreenHeader(
            headText = R.string.header_history,
            buttonIcon = R.drawable.delete_icon,
            cdButton = R.string.dialog_sub_delete_favorites,
            onButtonClicked = onDeleteAllClicked
        )

        if (history.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                    title = R.string.ttl_empty_history,
                    subText = R.string.sub_ttl_empty_history,
                    modifier = Modifier.padding(vertical = 46.dp),
                    onButtonClicked = onNavigateToMain
                )
            }
        } else {
            HistoryLazyColumn(
                history = history,
                onCopyToClipboard = onCopyToClipboard,
                onDeleteOneItem = onDeleteOneItem,
                onAddToFavoriteButtonClicked = onAddFavoriteButtonClicked
            )
        }
    }
}

@Preview(name = "TranslationHistoryScreen no content", showSystemUi = true)
@Composable
private fun PreviewTranslationHistoryScreenNoContent() {
    LingvooTranslatorAppTheme {
        TranslationHistoryScreenContent(
            history = emptyList(),
            {}, {}, {}, {}, {}
        )
    }
}

@Preview(name = "TranslationHistoryScreen ten items", showSystemUi = true)
@Composable
private fun PreviewTranslationHistoryScreen() {
    LingvooTranslatorAppTheme {
        TranslationHistoryScreenContent(
            history = listOf(HistoryWithFavorite(1, "Hello", "Привет", LocalDate.now(), true)),
            {}, {}, {}, {}, {}
        )
    }
}
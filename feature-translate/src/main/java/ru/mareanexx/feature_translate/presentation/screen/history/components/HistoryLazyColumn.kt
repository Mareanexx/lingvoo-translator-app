package ru.mareanexx.feature_translate.presentation.screen.history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.utils.date.DateFormatter
import ru.mareanexx.feature_translate.domain.entity.HistoryWithFavorite
import ru.mareanexx.feature_translate.presentation.screen.favorites.components.TranslationCard
import ru.mareanexx.feature_translate.presentation.screen.translate.components.AddToFavoriteIconButton

@Composable
fun HistoryLazyColumn(
    history: List<HistoryWithFavorite>,
    onCopyToClipboard: (String) -> Unit,
    onDeleteOneItem: (HistoryWithFavorite) -> Unit,
    onAddToFavoriteButtonClicked: (HistoryWithFavorite) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        itemsIndexed(
            items = history,
            key = { _, item -> item.id }
        ) { index, translation ->
            if (index == 0) {
                TranslationCard(
                    translation = translation,
                    date = DateFormatter.formatHistoryCardTime(translation.date),
                    onDeleteTranslation = { onDeleteOneItem(translation) },
                    onCopyToClipboard = { onCopyToClipboard(translation.translation) },
                    modifier = Modifier.animateItem(),
                    isFirst = true,
                    addToFavoriteButton = {
                        AddToFavoriteIconButton(
                            isFavorite = translation.isFavorite,
                            onButtonClicked = { onAddToFavoriteButtonClicked(translation) }
                        )
                    }
                )
            } else {
                TranslationCard(
                    translation = translation,
                    date = DateFormatter.formatHistoryCardTime(translation.date),
                    onDeleteTranslation = { onDeleteOneItem(translation) },
                    onCopyToClipboard = { onCopyToClipboard(translation.translation) },
                    modifier = Modifier.animateItem(),
                    addToFavoriteButton = {
                        AddToFavoriteIconButton(
                            isFavorite = translation.isFavorite,
                            onButtonClicked = { onAddToFavoriteButtonClicked(translation) }
                        )
                    }
                )
            }
        }
    }
}
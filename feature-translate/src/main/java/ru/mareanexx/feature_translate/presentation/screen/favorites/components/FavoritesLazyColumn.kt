package ru.mareanexx.feature_translate.presentation.screen.favorites.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.utils.date.DateFormatter
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation

@Composable
fun FavoritesLazyColumn(
    favorites: List<FavoriteTranslation>,
    onCopyToClipboard: (String) -> Unit,
    onDeleteOneItem: (FavoriteTranslation) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        itemsIndexed(
            items = favorites,
            key = { _, item -> item.id }
        ) { index, translation ->
            if (index == 0) {
                TranslationCard(
                    translation = translation,
                    date = DateFormatter.formatFavoriteCardTime(translation.date),
                    onDeleteTranslation = { onDeleteOneItem(translation) },
                    onCopyToClipboard = { onCopyToClipboard(translation.translation) },
                    modifier = Modifier.animateItem(),
                    isFirst = true
                )
            } else {
                TranslationCard(
                    translation = translation,
                    date = DateFormatter.formatFavoriteCardTime(translation.date),
                    onDeleteTranslation = { onDeleteOneItem(translation) },
                    onCopyToClipboard = { onCopyToClipboard(translation.translation) },
                    modifier = Modifier.animateItem()
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${favorites.size} favorites",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
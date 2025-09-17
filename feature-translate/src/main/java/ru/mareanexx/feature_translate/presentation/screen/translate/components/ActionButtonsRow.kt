package ru.mareanexx.feature_translate.presentation.screen.translate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mareanexx.feature_translate.presentation.components.buttons.CopyToClipboardButton

@Composable
fun TranslationActionButtonsRow(
    isFavorite: Boolean,
    onContentCopyClicked: () -> Unit,
    onAddToFavoriteClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CopyToClipboardButton(onContentCopyClicked)

        Spacer(modifier = Modifier.width(15.dp))

        AddToFavoriteIconButton(
            isFavorite = isFavorite,
            onButtonClicked = onAddToFavoriteClicked
        )
    }
}
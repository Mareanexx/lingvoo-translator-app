package ru.mareanexx.feature_translate.presentation.screen.translate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.mareanexx.feature_translate.R

@Composable
fun TranslationActionButtonsRow(
    isFavorite: Boolean,
    onContentCopyClicked: () -> Unit,
    onAddToFavoriteClicked: () -> Unit
) {
    val showCheckIcon = remember { mutableStateOf(false) }

    LaunchedEffect(showCheckIcon.value) {
        if (showCheckIcon.value) {
            delay(1500)
            showCheckIcon.value = false
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onContentCopyClicked(); showCheckIcon.value = true }) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(
                    if (!showCheckIcon.value) R.drawable.content_copy_icon else R.drawable.check_icon
                ),
                contentDescription = stringResource(R.string.cd_btn_copy_to_clipboard),
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        AddToFavoriteIconButton(
            isFavorite = isFavorite,
            onButtonClicked = onAddToFavoriteClicked
        )
    }
}
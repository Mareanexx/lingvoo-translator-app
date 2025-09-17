package ru.mareanexx.feature_translate.presentation.screen.favorites.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.FirstCardShape
import ru.mareanexx.core.ui.theme.HeaderShape
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.core.utils.common.Translation
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation
import ru.mareanexx.feature_translate.presentation.components.buttons.CopyToClipboardButton
import java.time.OffsetDateTime

@Composable
fun TranslationCard(
    translation: Translation,
    date: String,
    onDeleteTranslation: () -> Unit,
    onCopyToClipboard: () -> Unit,
    modifier: Modifier = Modifier,
    isFirst: Boolean = false,
    addToFavoriteButton: (@Composable () -> Unit)? = null
) {
    val swipeToDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) {
                onDeleteTranslation()
                true
            }
            false
        }
    )

    val modifier = if (isFirst) modifier.background(
        color = MaterialTheme.colorScheme.surfaceContainerHighest,
        shape = HeaderShape
    ) else Modifier

    SwipeToDismissBox(
        state = swipeToDismissBoxState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            when (swipeToDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart -> {
                    Row(
                        modifier = modifier
                            .fillMaxSize()
                            .background(
                                color = Color.Red,
                                shape = if (isFirst) FirstCardShape else MaterialTheme.shapes.small
                            )
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.btn_txt_delete),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(R.drawable.delete_icon),
                            contentDescription = "Remove item",
                            modifier = Modifier.size(32.dp),
                            tint = Color.White
                        )
                    }
                }
                else -> {}
            }
        }
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = if (isFirst) FirstCardShape else MaterialTheme.shapes.small
                )
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = translation.original,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = translation.translation,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CopyToClipboardButton(
                    onContentCopyClicked = onCopyToClipboard
                )

                addToFavoriteButton?.let {
                    addToFavoriteButton()
                }
            }
        }
    }
}

@Preview(name = "Favorite Card first item in list", showBackground = true, backgroundColor = 1110)
@Composable
private fun PreviewFavoriteTranslationCardFirstItem() {
    LingvooTranslatorAppTheme {
        TranslationCard(
            FavoriteTranslation(1, "Hello", "Привет", OffsetDateTime.now()), date = "25 July, 2025 - 20:13",
            {}, {}, isFirst = true
        )
    }
}

@Preview(name = "Favorite Card other item in list", showBackground = true, backgroundColor = 1110)
@Composable
private fun PreviewFavoriteTranslationCard() {
    LingvooTranslatorAppTheme {
        TranslationCard(
            FavoriteTranslation(1, "Hello", "Привет", OffsetDateTime.now()), date = "25 July, 2025 - 20:13",
            {}, {}, isFirst = false
        )
    }
}
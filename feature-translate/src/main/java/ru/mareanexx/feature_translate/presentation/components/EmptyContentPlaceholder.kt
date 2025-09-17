package ru.mareanexx.feature_translate.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R

@Composable
fun EmptyContentPlaceholder(
    @StringRes title: Int,
    @StringRes subText: Int,
    @StringRes buttonText: Int = R.string.btn_go_for_words,
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.headlineSmall
                .copy(textAlign = TextAlign.Center, fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            modifier = Modifier.padding(top = 3.dp),
            text = stringResource(subText),
            style = MaterialTheme.typography.titleMedium
                .copy(textAlign = TextAlign.Center),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = onButtonClicked,
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Text(
                text = stringResource(buttonText),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(name = "No Favorites Yet, Empty Favorites List")
@Composable
private fun PreviewEmptyFavoritesListPlaceholder() {
    LingvooTranslatorAppTheme {
        EmptyContentPlaceholder(
            title = R.string.ttl_empty_favorites_list,
            subText = R.string.sub_ttl_empty_favorites_list,
            buttonText = R.string.btn_go_for_words,
            onButtonClicked = {}
        )
    }
}

@Preview(name = "No Translation History, Empty List")
@Composable
private fun PreviewEmptyHistoryPlaceholder() {
    LingvooTranslatorAppTheme {
        EmptyContentPlaceholder(
            title = R.string.ttl_empty_history,
            subText = R.string.sub_ttl_empty_history,
            buttonText = R.string.btn_go_for_words,
            onButtonClicked = {}
        )
    }
}
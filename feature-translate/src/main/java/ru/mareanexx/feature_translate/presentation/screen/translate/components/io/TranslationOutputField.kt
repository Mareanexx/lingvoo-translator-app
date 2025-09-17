package ru.mareanexx.feature_translate.presentation.screen.translate.components.io

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.core.ui.theme.ShimmerColor
import ru.mareanexx.core.ui.theme.WordSkeletonShape
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.domain.entity.WordTranslationWithFavorite
import ru.mareanexx.feature_translate.presentation.screen.translate.components.LanguageNameText
import ru.mareanexx.feature_translate.presentation.screen.translate.components.TranslationActionButtonsRow
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.state.TranslationUiState

@Composable
fun TranslationOutputField(
    translationState: TranslationUiState,
    modifier: Modifier = Modifier,
    onContentCopyClicked: (String) -> Unit,
    onAddFavoriteClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = 25.dp, vertical = 48.dp)
    ) {
        if (translationState !is TranslationUiState.Init) {
            LanguageNameText(
                language = R.string.lbl_russian_lang,
                textColor = MaterialTheme.colorScheme.tertiary
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        when(translationState) {
            TranslationUiState.Init -> {}
            TranslationUiState.Loading -> OutputTextSkeleton()
            TranslationUiState.NoTranslation -> {
                Text(
                    text = stringResource(R.string.txt_placeholder_no_translation),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.4f)
                )
            }
            is TranslationUiState.Success -> {
                Text(
                    text = translationState.data.translation,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                    maxLines = 10
                )
                Spacer(modifier = Modifier.height(20.dp))
                TranslationActionButtonsRow(
                    isFavorite = translationState.data.isFavorite,
                    onContentCopyClicked = { onContentCopyClicked(translationState.data.translation) },
                    onAddToFavoriteClicked = onAddFavoriteClicked
                )
            }
        }
    }
}

@Composable
fun OutputTextSkeleton() {
    Box(
        modifier = Modifier
            .shimmer()
            .size(width = 200.dp, height = 36.dp)
            .background(
                color = ShimmerColor,
                shape = WordSkeletonShape
            )
    )
}

@Preview(name = "No user input yet / output is empty")
@Composable
private fun PreviewEmptyTranslationOutputField() {
    LingvooTranslatorAppTheme {
        TranslationOutputField(
            translationState = TranslationUiState.Init,
            onContentCopyClicked = {},
            onAddFavoriteClicked = {}
        )
    }
}

@Preview(name = "Word translation loading")
@Composable
private fun PreviewLoadingTranslationOutputField() {
    LingvooTranslatorAppTheme {
        TranslationOutputField(
            translationState = TranslationUiState.Loading,
            onContentCopyClicked = {},
            onAddFavoriteClicked = {}
        )
    }
}

@Preview(name = "Word translation got successfully")
@Composable
private fun PreviewSuccessTranslationOutputField() {
    LingvooTranslatorAppTheme {
        TranslationOutputField(
            translationState = TranslationUiState.Success(
                WordTranslationWithFavorite(id = 1, original = "Text", "Текст", isFavorite = true)
            ),
            onContentCopyClicked = {},
            onAddFavoriteClicked = {}
        )
    }
}
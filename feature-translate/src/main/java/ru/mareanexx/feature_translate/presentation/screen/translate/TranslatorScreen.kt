package ru.mareanexx.feature_translate.presentation.screen.translate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.mareanexx.core.ui.theme.LingvooTranslatorAppTheme
import ru.mareanexx.feature_translate.R
import ru.mareanexx.feature_translate.domain.entity.WordTranslationWithFavorite
import ru.mareanexx.feature_translate.presentation.components.BlueScreenHeader
import ru.mareanexx.feature_translate.presentation.screen.translate.components.SwitchLanguageRow
import ru.mareanexx.feature_translate.presentation.screen.translate.components.TranslatorEventHandler
import ru.mareanexx.feature_translate.presentation.screen.translate.components.io.TranslationOutputField
import ru.mareanexx.feature_translate.presentation.screen.translate.components.io.WordInputField
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.TranslationViewModel
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.state.TranslationUiState

@Composable
fun TranslatorScreen(viewModel: TranslationViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    TranslatorEventHandler(
        eventFlow = viewModel.eventFlow,
        onRetryClicked = { viewModel.retry() }
    )

    TranslatorScreenContent(
        uiState = uiState,
        searchText = searchText,
        onTextChanged = { viewModel.onTextChanged(it) },
        onClearInputClicked = { viewModel.clearInput() },
        onSettingsClicked = { viewModel.showNotImplementedToast() },
        onSwitchLanguageClicked = { viewModel.showNotImplementedToast() },
        onContentCopyClicked = { viewModel.copyToClipboard(it) },
        onAddFavoriteClicked = { viewModel.switchFavorite() }
    )
}

@Composable
fun TranslatorScreenContent(
    uiState: TranslationUiState,
    searchText: String,
    onTextChanged: (String) -> Unit,
    onClearInputClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onSwitchLanguageClicked: () -> Unit,
    onContentCopyClicked: (String) -> Unit,
    onAddFavoriteClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box {
            Column {
                BlueScreenHeader(
                    headText = ru.mareanexx.core.R.string.app_name,
                    buttonIcon = R.drawable.settings_icon,
                    cdButton = R.string.cd_open_settings,
                    onButtonClicked = onSettingsClicked
                )

                WordInputField(
                    value = searchText,
                    onValueChanged = onTextChanged,
                    onClearInputClicked = onClearInputClicked
                )
            }

            SwitchLanguageRow(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 76.dp),
                onSwitchClicked = onSwitchLanguageClicked
            )
        }
        TranslationOutputField(
            translationState = uiState,
            modifier = Modifier.weight(1f),
            onContentCopyClicked = onContentCopyClicked,
            onAddFavoriteClicked = onAddFavoriteClicked
        )
    }
}

@Preview(name = "TranslatorScreen init state", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTranslatorScreenWithoutInput() {
    LingvooTranslatorAppTheme {
        TranslatorScreenContent(
            uiState = TranslationUiState.Init,
            searchText = "",
            {}, {}, {}, {}, {}, {}
        )
    }
}

@Preview(name = "TranslatorScreen loading translation state", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTranslatorScreenLoading() {
    LingvooTranslatorAppTheme {
        TranslatorScreenContent(
            uiState = TranslationUiState.Loading,
            searchText = "hello",
            {}, {}, {}, {}, {}, {},
        )
    }
}

@Preview(name = "TranslatorScreen no translation state", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTranslatorScreenNoTranslation() {
    LingvooTranslatorAppTheme {
        TranslatorScreenContent(
            uiState = TranslationUiState.NoTranslation,
            searchText = "abrasksdjf",
            {}, {}, {}, {}, {}, {},
        )
    }
}

@Preview(name = "TranslatorScreen success translation state / not in favorite", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTranslatorScreenSuccessNotInFavorite() {
    LingvooTranslatorAppTheme {
        TranslatorScreenContent(
            uiState = TranslationUiState.Success(
                WordTranslationWithFavorite(1, "Hello", "Привет", false)
            ),
            searchText = "hello",
            {}, {}, {}, {}, {}, {}
        )
    }
}

@Preview(name = "TranslatorScreen success translation state / is in favorite", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewTranslatorScreenSuccessInFavorite() {
    LingvooTranslatorAppTheme {
        TranslatorScreenContent(
            uiState = TranslationUiState.Success(
                WordTranslationWithFavorite(1, "Hello", "Привет", true)
            ),
            searchText = "hello",
            {}, {}, {}, {}, {}, {}
        )
    }
}
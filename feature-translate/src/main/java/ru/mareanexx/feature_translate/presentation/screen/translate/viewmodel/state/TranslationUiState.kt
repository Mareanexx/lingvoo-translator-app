package ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.state

import ru.mareanexx.feature_translate.domain.entity.WordTranslationWithFavorite

sealed class TranslationUiState {
    data object Init : TranslationUiState()
    data class Success(val data: WordTranslationWithFavorite): TranslationUiState()
    data object Loading : TranslationUiState()
    data object NoTranslation : TranslationUiState()
}
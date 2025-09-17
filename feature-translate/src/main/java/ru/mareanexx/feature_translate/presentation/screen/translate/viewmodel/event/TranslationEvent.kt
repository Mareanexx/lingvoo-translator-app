package ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.event

import ru.mareanexx.core.utils.common.Error

sealed class TranslationEvent {
    data object ShowNoConnectionDialog : TranslationEvent()
    data object ShowNotImplementedToast : TranslationEvent()
    data class ShowErrorToast(val error: Error) : TranslationEvent()
    data class CopyToClipboard(val text: String) : TranslationEvent()
}
package ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel.event

import ru.mareanexx.core.utils.common.Error

sealed class SecondaryScreenEvent {
    data class ShowToast(val error: Error) : SecondaryScreenEvent()
    data object ShowDeleteDialog : SecondaryScreenEvent()
    data class CopyToClipboard(val text: String) : SecondaryScreenEvent()
}
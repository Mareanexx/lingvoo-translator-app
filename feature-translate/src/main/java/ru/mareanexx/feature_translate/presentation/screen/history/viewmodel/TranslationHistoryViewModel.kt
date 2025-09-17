package ru.mareanexx.feature_translate.presentation.screen.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.feature_translate.data.mapper.toDeleteRequest
import ru.mareanexx.feature_translate.data.mapper.toWord
import ru.mareanexx.feature_translate.domain.entity.HistoryWithFavorite
import ru.mareanexx.feature_translate.domain.usecase.ClearTranslationHistoryUseCase
import ru.mareanexx.feature_translate.domain.usecase.DeleteHistoryItemUseCase
import ru.mareanexx.feature_translate.domain.usecase.GetFavoritesUseCase
import ru.mareanexx.feature_translate.domain.usecase.GetTranslationHistoryUseCase
import ru.mareanexx.feature_translate.domain.usecase.SwitchIsFavoritesUseCase
import ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel.event.SecondaryScreenEvent
import javax.inject.Inject

@HiltViewModel
class TranslationHistoryViewModel @Inject constructor(
    getTranslationHistory: GetTranslationHistoryUseCase,
    getFavoritesUseCase: GetFavoritesUseCase,
    private val clearTranslationHistory: ClearTranslationHistoryUseCase,
    private val deleteHistoryItemUseCase: DeleteHistoryItemUseCase,
    private val switchIsFavoritesUseCase: SwitchIsFavoritesUseCase
): ViewModel() {
    val history: StateFlow<List<HistoryWithFavorite>> =
        combine(
            getTranslationHistory(),
            getFavoritesUseCase()
        ) { history, favorites ->
            val favoritesSet = favorites.map { it.id }.toSet()
            history.map { item ->
                HistoryWithFavorite(
                    id = item.id,
                    original = item.original,
                    translation = item.translation,
                    date = item.date,
                    isFavorite = item.id in favoritesSet
                )
            }
        }.catch { e -> showToast(Error(type = ErrorType.UnknownError)); emit(emptyList()) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _eventFlow = MutableSharedFlow<SecondaryScreenEvent>()
    val eventFlow: SharedFlow<SecondaryScreenEvent> get() = _eventFlow

    // events
    fun showDeleteDialog() =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.ShowDeleteDialog) }
    fun showToast(error: Error) =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.ShowToast(error)) }
    fun copyToClipboard(text: String) =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.CopyToClipboard(text)) }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                clearTranslationHistory()
            }
        }
    }

    fun deleteOneItem(translation: HistoryWithFavorite) {
        viewModelScope.launch {
            val baseResult = withContext(Dispatchers.Default) {
                deleteHistoryItemUseCase(translation.toDeleteRequest())
            }
            when(baseResult) {
                is BaseResult.Error<*> -> showToast(Error(type = ErrorType.UnknownError))
                is BaseResult.Success<*> -> {}
            }
        }
    }

    fun switchFavorite(data: HistoryWithFavorite) {
        viewModelScope.launch {
            val isAddOperation = !data.isFavorite
            val baseResult = switchIsFavoritesUseCase(
                word = data.toWord(),
                isAddOperation = isAddOperation
            )
            when(baseResult) {
                is BaseResult.Error<*> -> {
                    showToast(Error(type = ErrorType.UnknownError))
                }
                is BaseResult.Success<*> -> {}
            }
        }
    }
}
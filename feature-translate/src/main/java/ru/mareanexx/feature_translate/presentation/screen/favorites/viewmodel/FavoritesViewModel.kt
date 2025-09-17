package ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.feature_translate.data.mapper.toDeleteRequest
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation
import ru.mareanexx.feature_translate.domain.usecase.ClearAllFavoritesUseCase
import ru.mareanexx.feature_translate.domain.usecase.DeleteOneFavoriteUseCase
import ru.mareanexx.feature_translate.domain.usecase.GetFavoritesUseCase
import ru.mareanexx.feature_translate.presentation.screen.favorites.viewmodel.event.SecondaryScreenEvent
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val clearAllFavoritesUseCase: ClearAllFavoritesUseCase,
    private val deleteOneFavoriteUseCase: DeleteOneFavoriteUseCase
): ViewModel() {
    val favorites: StateFlow<List<FavoriteTranslation>> =
        getFavoritesUseCase()
            .catch { exception -> showToast(Error(type = ErrorType.UnknownError)) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _eventFlow = MutableSharedFlow<SecondaryScreenEvent>()
    val eventFlow: SharedFlow<SecondaryScreenEvent> get() = _eventFlow

    // event emitters
    fun showDeleteDialog() =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.ShowDeleteDialog) }
    fun showToast(error: Error) =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.ShowToast(error)) }
    fun copyToClipboard(text: String) =
        viewModelScope.launch { _eventFlow.emit(SecondaryScreenEvent.CopyToClipboard(text)) }

    fun deleteAll() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                clearAllFavoritesUseCase()
            }
        }
    }

    fun deleteOneFavorite(translation: FavoriteTranslation) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                deleteOneFavoriteUseCase(translation.toDeleteRequest())
            }
        }
    }
}
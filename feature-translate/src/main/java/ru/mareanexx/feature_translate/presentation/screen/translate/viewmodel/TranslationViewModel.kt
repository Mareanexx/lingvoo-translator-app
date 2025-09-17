package ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.Error
import ru.mareanexx.core.utils.common.ErrorType
import ru.mareanexx.feature_translate.data.mapper.toWithFavorite
import ru.mareanexx.feature_translate.data.mapper.toWord
import ru.mareanexx.feature_translate.data.remote.dto.TranslateWordRequest
import ru.mareanexx.feature_translate.domain.entity.WordTranslationWithFavorite
import ru.mareanexx.feature_translate.domain.usecase.CheckInFavoritesUseCase
import ru.mareanexx.feature_translate.domain.usecase.GetTranslationUseCase
import ru.mareanexx.feature_translate.domain.usecase.SwitchIsFavoritesUseCase
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.event.TranslationEvent
import ru.mareanexx.feature_translate.presentation.screen.translate.viewmodel.state.TranslationUiState
import javax.inject.Inject


@OptIn(FlowPreview::class)
@HiltViewModel
class TranslationViewModel @Inject constructor (
    private val getTranslationUseCase: GetTranslationUseCase,
    private val checkInFavoritesUseCase: CheckInFavoritesUseCase,
    private val switchIsFavoritesUseCase: SwitchIsFavoritesUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow<TranslationUiState>(TranslationUiState.Init)
    val uiState: StateFlow<TranslationUiState> get() = _uiState

    private val _eventFlow = MutableSharedFlow<TranslationEvent>()
    val eventFlow: SharedFlow<TranslationEvent> get() = _eventFlow

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> get() = _searchText

    init {
        viewModelScope.launch {
            _searchText
                .debounce(700)
                .filter { it.isNotBlank() }
                .collectLatest { text ->
                    translate()
                }
        }
    }

    // state changers
    private fun setSuccessState(data: WordTranslationWithFavorite) = _uiState.update { TranslationUiState.Success(data) }
    private fun setLoadingState() = _uiState.update { TranslationUiState.Loading }
    private fun setInitState() = _uiState.update { TranslationUiState.Init }
    private fun setNoTranslationState() = _uiState.update { TranslationUiState.NoTranslation }

    // event emitters
    fun showErrorToast(error: Error) {
        viewModelScope.launch { _eventFlow.emit(TranslationEvent.ShowErrorToast(error)) }
        setInitState()
    }
    fun showNoInternetConnectionDialog() {
        viewModelScope.launch { _eventFlow.emit(TranslationEvent.ShowNoConnectionDialog) }
        setInitState()
    }
    fun showNotImplementedToast() =
        viewModelScope.launch { _eventFlow.emit(TranslationEvent.ShowNotImplementedToast) }
    fun copyToClipboard(text: String) =
        viewModelScope.launch { _eventFlow.emit(TranslationEvent.CopyToClipboard(text)) }

    // onChanges
    fun onTextChanged(value: String) {
        _searchText.update { value }
        if (_searchText.value == "") {
            setInitState()
        } else {
            setLoadingState()
        }
    }

    fun retry() = viewModelScope.launch { translate() }
    fun clearInput() {
        _searchText.update { "" }
        setInitState()
    }

    private suspend fun translate() {
        try {
            val word = TranslateWordRequest(text = _searchText.value)
            val baseResult = getTranslationUseCase(word)
            when(baseResult) {
                is BaseResult.Error -> {
                    when(baseResult.error.type) {
                        ErrorType.NoInternetConnection -> showNoInternetConnectionDialog()
                        ErrorType.NoTranslation -> setNoTranslationState()
                        else -> showErrorToast(baseResult.error)
                    }
                }
                is BaseResult.Success -> {
                    val isFavorite = withContext(Dispatchers.Default) {
                        checkInFavoritesUseCase(baseResult.data)
                    }
                    setSuccessState(baseResult.data.toWithFavorite(isFavorite))
                }
            }
        } catch (e: Exception) { showErrorToast(Error(message = e.message, type = ErrorType.UnknownError)) }
    }

    fun switchFavorite() {
        viewModelScope.launch {
            if (_uiState.value is TranslationUiState.Success) {
                val data = (_uiState.value as TranslationUiState.Success).data
                val isAddOperation = !data.isFavorite
                val baseResult = switchIsFavoritesUseCase(
                    word = data.toWord(),
                    isAddOperation = isAddOperation
                )
                when(baseResult) {
                    is BaseResult.Error<*> -> {
                        showErrorToast(Error(type = ErrorType.UnknownError))
                        setInitState()
                    }
                    is BaseResult.Success<*> -> setSuccessState(data.copy(isFavorite = isAddOperation))
                }
            }
        }
    }
}
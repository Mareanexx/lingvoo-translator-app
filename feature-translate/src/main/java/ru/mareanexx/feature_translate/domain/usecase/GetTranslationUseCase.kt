package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.feature_translate.data.remote.dto.TranslateWordRequest
import ru.mareanexx.feature_translate.domain.repository.TranslationRepository
import javax.inject.Inject

class GetTranslationUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(word: TranslateWordRequest) =
        repository.getTranslatedWord(word)
}
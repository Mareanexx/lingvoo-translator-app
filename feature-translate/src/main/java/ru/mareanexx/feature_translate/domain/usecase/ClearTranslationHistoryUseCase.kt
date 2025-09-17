package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.feature_translate.domain.repository.TranslationHistoryRepository
import javax.inject.Inject

class ClearTranslationHistoryUseCase @Inject constructor(
    private val historyRepository: TranslationHistoryRepository
) {
    suspend operator fun invoke() = historyRepository.clearAll()
}
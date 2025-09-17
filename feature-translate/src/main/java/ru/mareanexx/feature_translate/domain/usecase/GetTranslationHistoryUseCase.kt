package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.feature_translate.domain.repository.TranslationHistoryRepository
import javax.inject.Inject

class GetTranslationHistoryUseCase @Inject constructor(
    private val historyRepository: TranslationHistoryRepository
) {
    operator fun invoke() = historyRepository.get()
}
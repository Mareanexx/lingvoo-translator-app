package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.repository.TranslationHistoryRepository
import javax.inject.Inject

class DeleteHistoryItemUseCase @Inject constructor(
    private val historyRepository: TranslationHistoryRepository
) {
    suspend operator fun invoke(toDelete: DeleteTranslationRequest) =
        historyRepository.delete(toDelete)
}
package ru.mareanexx.feature_translate.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.entity.TranslationHistoryItem

interface TranslationHistoryRepository {
    fun get() : Flow<List<TranslationHistoryItem>>
    suspend fun delete(toDelete: DeleteTranslationRequest) : BaseResult<Unit, Unit>
    suspend fun clearAll()
    suspend fun addNew(word: WordTranslation): BaseResult<Unit, Unit>
}
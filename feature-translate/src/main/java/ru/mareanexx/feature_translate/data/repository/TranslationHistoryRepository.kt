package ru.mareanexx.feature_translate.data.repository

import kotlinx.coroutines.flow.Flow
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.feature_translate.data.local.dao.TranslationHistoryDao
import ru.mareanexx.feature_translate.data.mapper.toHistoryItem
import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.entity.TranslationHistoryItem
import ru.mareanexx.feature_translate.domain.repository.TranslationHistoryRepository
import javax.inject.Inject

class TranslationHistoryRepository @Inject constructor(
    private val translationHistoryDao: TranslationHistoryDao
): TranslationHistoryRepository {
    override suspend fun get(): Flow<List<TranslationHistoryItem>> = translationHistoryDao.get()

    override suspend fun delete(toDelete: DeleteTranslationRequest): BaseResult<Unit, Unit> {
        val resultRows = translationHistoryDao.deleteById(toDelete.id)
        return if (resultRows == 0) {
            BaseResult.Error(Unit)
        } else {
            BaseResult.Success(Unit)
        }
    }

    override suspend fun addNew(word: WordTranslation): BaseResult<Unit, Unit> {
        val resultId = translationHistoryDao.insert(word.toHistoryItem())
        return if (resultId == -1L) {
            BaseResult.Error(Unit)
        } else {
            BaseResult.Success(Unit)
        }
    }
}
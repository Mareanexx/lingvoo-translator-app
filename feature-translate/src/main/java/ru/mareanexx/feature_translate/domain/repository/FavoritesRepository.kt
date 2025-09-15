package ru.mareanexx.feature_translate.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation

interface FavoritesRepository {
    suspend fun get(): Flow<List<FavoriteTranslation>>
    suspend fun delete(toDelete: DeleteTranslationRequest): BaseResult<Unit, Unit>
    suspend fun addNew(word: WordTranslation): BaseResult<Unit, Unit>
}
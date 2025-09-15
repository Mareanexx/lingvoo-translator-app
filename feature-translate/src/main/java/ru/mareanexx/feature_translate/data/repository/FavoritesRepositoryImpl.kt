package ru.mareanexx.feature_translate.data.repository

import kotlinx.coroutines.flow.Flow
import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.core.utils.common.BaseResult
import ru.mareanexx.feature_translate.data.local.dao.FavoritesDao
import ru.mareanexx.feature_translate.data.mapper.toFavorites
import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation
import ru.mareanexx.feature_translate.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesDao: FavoritesDao
): FavoritesRepository {
    override suspend fun get(): Flow<List<FavoriteTranslation>> = favoritesDao.get()

    override suspend fun delete(toDelete: DeleteTranslationRequest): BaseResult<Unit, Unit> {
        val deleteResult = favoritesDao.deleteById(toDelete.id)
        return if (deleteResult == 0) {
            BaseResult.Error(Unit)
        } else {
            BaseResult.Success(Unit)
        }
    }

    override suspend fun addNew(word: WordTranslation): BaseResult<Unit, Unit> {
        val resultId = favoritesDao.insert(word.toFavorites())
        return if (resultId == -1L) {
            BaseResult.Error(Unit)
        } else {
            BaseResult.Success(Unit)
        }
    }
}
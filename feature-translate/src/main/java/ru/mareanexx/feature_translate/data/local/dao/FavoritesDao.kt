package ru.mareanexx.feature_translate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mareanexx.feature_translate.data.local.entity.FavoriteTranslationEntity
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation

@Dao
interface FavoritesDao {
    @Query("SELECT id, original, translation, date FROM favorites")
    fun get(): Flow<List<FavoriteTranslation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTranslation: FavoriteTranslationEntity): Long

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id)")
    suspend fun existsById(id: Long): Boolean
}
package ru.mareanexx.feature_translate.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mareanexx.feature_translate.data.local.entity.TranslationHistoryEntity
import ru.mareanexx.feature_translate.domain.entity.TranslationHistoryItem

@Dao
interface TranslationHistoryDao {
    @Query("SELECT id, original, translation, date FROM history")
    fun get(): Flow<List<TranslationHistoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyItem: TranslationHistoryEntity): Long

    @Query("DELETE FROM history WHERE id = :id")
    suspend fun deleteById(id: Long): Int

    @Query("DELETE FROM history")
    suspend fun clearAll()
}
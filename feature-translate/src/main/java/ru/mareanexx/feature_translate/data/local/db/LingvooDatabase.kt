package ru.mareanexx.feature_translate.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mareanexx.feature_translate.data.local.dao.FavoritesDao
import ru.mareanexx.feature_translate.data.local.dao.TranslationHistoryDao
import ru.mareanexx.feature_translate.data.local.entity.FavoriteTranslationEntity
import ru.mareanexx.feature_translate.data.local.entity.TranslationHistoryEntity

@Database(
    entities = [
        TranslationHistoryEntity::class,
        FavoriteTranslationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LingvooDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao
    abstract fun favoritesDao(): FavoritesDao
}
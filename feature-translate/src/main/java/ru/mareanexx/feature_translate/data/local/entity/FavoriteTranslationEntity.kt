package ru.mareanexx.feature_translate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mareanexx.core.utils.common.Translation
import java.time.OffsetDateTime

@Entity(tableName = "favorites")
data class FavoriteTranslationEntity(
    @PrimaryKey
    override val id: Long,
    override val original: String,
    override val translation: String,
    val date: OffsetDateTime
) : Translation
package ru.mareanexx.feature_translate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.mareanexx.core.utils.common.Translation
import ru.mareanexx.feature_translate.data.local.converters.OffsetDateTimeConverter
import java.time.OffsetDateTime

@Entity(tableName = "favorites")
@TypeConverters(OffsetDateTimeConverter::class)
data class FavoriteTranslationEntity(
    @PrimaryKey
    override val id: Long,
    override val original: String,
    override val translation: String,
    val date: OffsetDateTime
) : Translation
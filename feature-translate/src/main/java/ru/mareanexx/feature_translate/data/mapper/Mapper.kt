package ru.mareanexx.feature_translate.data.mapper

import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.feature_translate.data.local.entity.FavoriteTranslationEntity
import ru.mareanexx.feature_translate.data.local.entity.TranslationHistoryEntity
import java.time.LocalDate
import java.time.OffsetDateTime

fun WordTranslation.toFavorites() = FavoriteTranslationEntity(
    id = id,
    original = original,
    translation = translation,
    date = OffsetDateTime.now()
)

fun WordTranslation.toHistoryItem() = TranslationHistoryEntity(
    id = id,
    original = original,
    translation = translation,
    date = LocalDate.now()
)
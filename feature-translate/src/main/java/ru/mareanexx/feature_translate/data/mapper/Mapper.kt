package ru.mareanexx.feature_translate.data.mapper

import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.feature_translate.data.local.entity.FavoriteTranslationEntity
import ru.mareanexx.feature_translate.data.local.entity.TranslationHistoryEntity
import ru.mareanexx.feature_translate.data.remote.dto.DeleteTranslationRequest
import ru.mareanexx.feature_translate.domain.entity.FavoriteTranslation
import ru.mareanexx.feature_translate.domain.entity.HistoryWithFavorite
import ru.mareanexx.feature_translate.domain.entity.WordTranslationWithFavorite
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

fun WordTranslation.toWithFavorite(isFavorite: Boolean) = WordTranslationWithFavorite(
    id = id,
    original = original,
    translation = translation,
    isFavorite = isFavorite
)

fun WordTranslationWithFavorite.toWord() = WordTranslation(
    id = id,
    original = original,
    translation = translation
)

fun WordTranslation.toDelete() = DeleteTranslationRequest(
    id = id
)

fun FavoriteTranslation.toDeleteRequest() = DeleteTranslationRequest(
    id = id
)

fun HistoryWithFavorite.toDeleteRequest() = DeleteTranslationRequest(
    id = id
)

fun HistoryWithFavorite.toWord() = WordTranslation(
    id = id,
    original = original,
    translation = translation
)
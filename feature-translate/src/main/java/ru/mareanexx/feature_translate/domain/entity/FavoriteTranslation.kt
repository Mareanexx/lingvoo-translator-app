package ru.mareanexx.feature_translate.domain.entity

import ru.mareanexx.core.utils.common.Translation
import java.time.OffsetDateTime

data class FavoriteTranslation(
    override val id: Long,
    override val original: String,
    override val translation: String,
    val date: OffsetDateTime
) : Translation
package ru.mareanexx.feature_translate.domain.entity

import ru.mareanexx.core.utils.common.Favorite
import ru.mareanexx.core.utils.common.Translation

data class WordTranslationWithFavorite(
    override val id: Long,
    override val original: String,
    override val translation: String,
    override val isFavorite: Boolean
): Translation, Favorite
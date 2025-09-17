package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.core.utils.common.WordTranslation
import ru.mareanexx.feature_translate.domain.repository.FavoritesRepository
import javax.inject.Inject

class SwitchIsFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(word: WordTranslation, isAddOperation: Boolean) =
        favoritesRepository.switchIsFavorite(word, isAddOperation)
}
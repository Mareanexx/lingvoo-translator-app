package ru.mareanexx.feature_translate.domain.usecase

import ru.mareanexx.feature_translate.domain.repository.FavoritesRepository
import javax.inject.Inject

class ClearAllFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke() =
        favoritesRepository.clearTable()
}
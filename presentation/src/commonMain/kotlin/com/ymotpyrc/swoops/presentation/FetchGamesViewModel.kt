package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.usecases.FetchRecoverableGamesUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FetchGamesUseCase

class FetchGamesViewModel(
    private val fetchGamesUseCase: FetchGamesUseCase,
    private val fetchRecoverableGamesUseCase: FetchRecoverableGamesUseCase,
) {

    suspend fun start() {
        fetchGamesUseCase.invoke()
//        fetchRecoverableGamesUseCase.invoke()
    }
}
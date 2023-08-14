package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.games.usecases.FetchRecoverableGamesUseCase
import com.j12a6.swoops.domain.games.usecases.FetchGamesUseCase

class FetchGamesViewModel(
    private val fetchGamesUseCase: FetchGamesUseCase,
    private val fetchRecoverableGamesUseCase: FetchRecoverableGamesUseCase,
) {

    suspend fun start() {
        fetchGamesUseCase.invoke()
        fetchRecoverableGamesUseCase.invoke()
    }
}
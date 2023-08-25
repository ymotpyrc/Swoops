package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.games.usecases.CheckGamesUseCase

class CheckGamesViewModel(
    private val checkGamesUseCase: CheckGamesUseCase,
) {

    fun start() {
        checkGamesUseCase.invoke()
    }
}
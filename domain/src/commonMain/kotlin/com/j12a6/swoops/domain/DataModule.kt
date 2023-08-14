package com.j12a6.swoops.domain

import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.players.PlayerRepository

interface DataModule {

    fun provideGameRepository() : GameRepository
    fun providePlayerRepository(): PlayerRepository
}
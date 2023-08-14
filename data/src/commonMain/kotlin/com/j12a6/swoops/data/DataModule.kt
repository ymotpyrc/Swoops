package com.j12a6.swoops.data

import com.j12a6.swoops.domain.DataModule
import com.j12a6.swoops.domain.games.GameRepository
import com.j12a6.swoops.domain.players.PlayerRepository

class DefaultDataModule : DataModule {

    override fun provideGameRepository(): GameRepository = providingGameRepository()

    override fun providePlayerRepository(): PlayerRepository = providingPlayerRepository()
}
package com.ymotpyrc.swoops.scripts

import com.ymotpyrc.swoops.data.DefaultDataModule
import com.ymotpyrc.swoops.domain.DomainModule
import kotlinx.coroutines.runBlocking

val dataModule = DefaultDataModule()
val domain = DomainModule(dataModule)

fun main(args: Array<String>) {

    when (args.first()) {
        "fetch_games" -> {
            val fetchGamesUseCase = domain.provideFetchGamesUseCase()
            runBlocking { fetchGamesUseCase.invoke() }
        }
        "recover_games" -> {
            val fetchRecoverableGamesUseCase = domain.provideFetchRecoverableGamesUseCase()
            runBlocking { fetchRecoverableGamesUseCase.invoke() }
        }
        "export_players_stats" -> {
            val exportUseCase = domain.provideExportPlayersStatsUseCase()
            exportUseCase.invoke()
        }
        "export_players_stats_percentiles" -> {
            val exportUseCase = domain.provideExportPlayersStatsPercentilesUseCase()
            exportUseCase.invoke()
        }
        else -> throw IllegalArgumentException("Must pass a parameter")
    }
}

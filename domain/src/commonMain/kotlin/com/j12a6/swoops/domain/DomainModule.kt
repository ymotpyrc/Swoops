package com.j12a6.swoops.domain

import com.j12a6.swoops.domain.games.usecases.FetchRecoverableGamesUseCase
import com.j12a6.swoops.domain.games.usecases.FetchGamesUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnDateUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnFreeAgentsUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnPlayersUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnTeamMatchUpsUseCase
import com.j12a6.swoops.domain.games.usecases.FilterGamesOnTeamUseCase
import com.j12a6.swoops.domain.games.usecases.GetGameIdsToExcludeUseCase
import com.j12a6.swoops.domain.games.usecases.GetGamesByIdsUseCase
import com.j12a6.swoops.domain.games.usecases.GetGamesUseCase
import com.j12a6.swoops.domain.players.usecases.ExportPlayersToDbUseCase
import com.j12a6.swoops.domain.players.usecases.FilterOnPlayerNumbersUseCase
import com.j12a6.swoops.domain.players.usecases.FilterOnSpecificStatsUseCase
import com.j12a6.swoops.domain.players.usecases.GetPlayersAfterSkillsUpdateUseCase
import com.j12a6.swoops.domain.players.usecases.GetPlayersStatsUseCase
import com.j12a6.swoops.domain.players.usecases.GetPlayersUseCase


class DomainModule(private val dataModule: DataModule) {

    fun provideFetchGamesUseCase(): FetchGamesUseCase =
        FetchGamesUseCase(
            provideGetGameIdsToExcludeUseCase(),
            dataModule.provideGameRepository()
        )

    fun provideFetchGamesByIdsUseCase(): FetchRecoverableGamesUseCase =
        FetchRecoverableGamesUseCase(
            provideGetGameIdsToExcludeUseCase(),
            dataModule.provideGameRepository()
        )

    fun provideGetPlayersUseCase(): GetPlayersUseCase =
        GetPlayersUseCase(dataModule.providePlayerRepository())

    fun provideGetPlayersAfterSkillsUpdateUseCase(): GetPlayersAfterSkillsUpdateUseCase =
        GetPlayersAfterSkillsUpdateUseCase()

    fun provideExportPlayersToDbUseCase(): ExportPlayersToDbUseCase =
        ExportPlayersToDbUseCase(
            provideGetPlayersAfterSkillsUpdateUseCase(),
            dataModule.providePlayerRepository(),
        )

    fun provideFilterOnPlayerNumbersUseCase(): FilterOnPlayerNumbersUseCase =
        FilterOnPlayerNumbersUseCase()

//fun provideFilterOnInterestingPlayersUseCase(): FilterOnInterestingPlayersUseCase =
// FilterOnInterestingPlayersUseCase()

    fun provideGetGamesUseCase(): GetGamesUseCase =
        GetGamesUseCase(dataModule.provideGameRepository())

    fun provideGetGamesByIdsUseCase(): GetGamesByIdsUseCase =
        GetGamesByIdsUseCase(dataModule.provideGameRepository())

    fun provideGetGameIdsToExcludeUseCase(): GetGameIdsToExcludeUseCase =
        GetGameIdsToExcludeUseCase(dataModule.provideGameRepository())

    fun provideFilterGamesOnPlayersUseCase(): FilterGamesOnPlayersUseCase =
        FilterGamesOnPlayersUseCase()

    fun provideFilterGamesOnTeamMatchUpsUseCase(): FilterGamesOnTeamMatchUpsUseCase =
        FilterGamesOnTeamMatchUpsUseCase()

    fun provideFilterGamesOnTeamUseCase(): FilterGamesOnTeamUseCase = FilterGamesOnTeamUseCase()

    fun provideFilterGamesOnFreeAgentsUseCase(): FilterGamesOnFreeAgentsUseCase =
        FilterGamesOnFreeAgentsUseCase()

    fun provideFilterGamesOnDateUseCase(): FilterGamesOnDateUseCase = FilterGamesOnDateUseCase()

    fun provideGetPlayerStatsUseCase(): GetPlayersStatsUseCase = GetPlayersStatsUseCase()

    fun provideFilterOnSpecificStatsUseCase(): FilterOnSpecificStatsUseCase =
        FilterOnSpecificStatsUseCase()
}

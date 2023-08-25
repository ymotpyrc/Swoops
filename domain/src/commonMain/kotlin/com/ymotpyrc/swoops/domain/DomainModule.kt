package com.ymotpyrc.swoops.domain

import com.ymotpyrc.swoops.domain.games.usecases.CheckGamesUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FetchGamesUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FetchRecoverableGamesUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnDateUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnFreeAgentsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnPlayersUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnTeamMatchUpsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnTeamUseCase
import com.ymotpyrc.swoops.domain.games.usecases.FilterGamesOnTeamsConfrontationUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGameIdsToExcludeUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesByIdsUseCase
import com.ymotpyrc.swoops.domain.games.usecases.GetGamesUseCase
import com.ymotpyrc.swoops.domain.players.usecases.ExportPlayersToDbUseCase
import com.ymotpyrc.swoops.domain.players.usecases.FetchPlayersUseCase
import com.ymotpyrc.swoops.domain.players.usecases.FilterOnPlayerNumbersUseCase
import com.ymotpyrc.swoops.domain.players.usecases.FilterOnSpecificStatsUseCase
import com.ymotpyrc.swoops.domain.players.usecases.GetPlayersAfterSkillsUpdateUseCase
import com.ymotpyrc.swoops.domain.players.usecases.GetPlayersUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.ComputePlayersStatsUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.ExportPlayersStatsPercentilesUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.ExportPlayersStatsUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.GetPlayersStatsPercentilesUseCase
import com.ymotpyrc.swoops.domain.stats.usecases.GetPlayersStatsUseCase

class DomainModule(private val dataModule: DataModule) {

    fun provideFetchGamesUseCase(): FetchGamesUseCase =
        FetchGamesUseCase(
            provideGetGameIdsToExcludeUseCase(),
            dataModule.provideGameRepository(),
        )

    fun provideCheckGamesUseCase(): CheckGamesUseCase =
        CheckGamesUseCase(
            provideGetGameIdsToExcludeUseCase(),
            dataModule.provideGameRepository(),
        )

    fun provideFetchPlayersUseCase(): FetchPlayersUseCase =
        FetchPlayersUseCase(dataModule.providePlayerRepository())

    fun provideFetchRecoverableGamesUseCase(): FetchRecoverableGamesUseCase =
        FetchRecoverableGamesUseCase(
            provideGetGameIdsToExcludeUseCase(),
            dataModule.provideGameRepository(),
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

    fun provideFilterGamesOnTeamConfrontation(): FilterGamesOnTeamsConfrontationUseCase =
        FilterGamesOnTeamsConfrontationUseCase()

    fun provideFilterGamesOnFreeAgentsUseCase(): FilterGamesOnFreeAgentsUseCase =
        FilterGamesOnFreeAgentsUseCase()

    fun provideFilterGamesOnDateUseCase(): FilterGamesOnDateUseCase = FilterGamesOnDateUseCase()

    fun provideGetPlayerStatsUseCase(): GetPlayersStatsUseCase =
        GetPlayersStatsUseCase(dataModule.providePlayersStatsRepository())

    fun provideGetPlayersPercentilesStatsUseCase(): GetPlayersStatsPercentilesUseCase =
        GetPlayersStatsPercentilesUseCase(dataModule.providePlayersStatsRepository())

    fun provideFilterOnSpecificStatsUseCase(): FilterOnSpecificStatsUseCase =
        FilterOnSpecificStatsUseCase()

    fun provideComputePlayersStatsUseCase(): ComputePlayersStatsUseCase = ComputePlayersStatsUseCase()

    fun provideExportPlayersStatsUseCase(): ExportPlayersStatsUseCase =
        ExportPlayersStatsUseCase(
            provideComputePlayersStatsUseCase(),
            provideGetGamesUseCase(),
            provideFilterGamesOnFreeAgentsUseCase(),
            provideFilterGamesOnTeamUseCase(),
            dataModule.providePlayersStatsRepository(),
        )

    fun provideExportPlayersStatsPercentilesUseCase(): ExportPlayersStatsPercentilesUseCase =
        ExportPlayersStatsPercentilesUseCase(
            dataModule.providePlayersStatsRepository(),
        )
}

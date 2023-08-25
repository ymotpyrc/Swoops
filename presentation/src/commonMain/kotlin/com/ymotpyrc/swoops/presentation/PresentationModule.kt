package com.ymotpyrc.swoops.presentation

import com.ymotpyrc.swoops.domain.DomainModule
import kotlinx.coroutines.CoroutineScope

class PresentationModule(private val domainModule: DomainModule) {

    fun provideFetchGamesViewModel(): FetchGamesViewModel =
        FetchGamesViewModel(
            fetchGamesUseCase = domainModule.provideFetchGamesUseCase(),
            fetchRecoverableGamesUseCase = domainModule.provideFetchRecoverableGamesUseCase(),
        )

    fun provideCheckGamesViewModel(): CheckGamesViewModel =
        CheckGamesViewModel(
            checkGamesUseCase = domainModule.provideCheckGamesUseCase(),
        )

    fun provideFetchPlayersViewModel(): FetchPlayersViewModel =
        FetchPlayersViewModel(
            //fetchPlayersUseCase = domainModule.provideFetchPlayersUseCase(),
        )

    fun providePlayerStatsViewModel(coroutineScope: CoroutineScope): PlayerStatsViewModel =
        PlayerStatsViewModel(
            getGamesUseCase = domainModule.provideGetGamesUseCase(),
            getGamesByIdsUseCase = domainModule.provideGetGamesByIdsUseCase(),
            filterGamesOnPlayers = domainModule.provideFilterGamesOnPlayersUseCase(),
            filterGamesOnFreeAgentsUseCase = domainModule.provideFilterGamesOnFreeAgentsUseCase(),
            filterGamesOnDateUseCase = domainModule.provideFilterGamesOnDateUseCase(),
            getPlayersStatsUseCase = domainModule.provideGetPlayerStatsUseCase(),
            getPlayersStatsPercentilesUseCase = domainModule.provideGetPlayersPercentilesStatsUseCase(),
            playerStatsUiMapper = providePlayerStatsUiMapper(),
            coroutineScope = coroutineScope,
        )

    fun provideComparePlayersStatsViewModel(): ComparePlayersStatsViewModel =
        ComparePlayersStatsViewModel(
            getPlayersStatsUseCase = domainModule.provideGetPlayerStatsUseCase(),
            playerStatsUiMapper = providePlayerStatsUiMapper(),
        )

    fun provideTeamOppositionsViewModel(): TeamOppositionsViewModel =
        TeamOppositionsViewModel(
            getGamesUseCase = domainModule.provideGetGamesUseCase(),
            filterGamesOnTeamUseCase = domainModule.provideFilterGamesOnTeamUseCase(),
        )

    private fun providePlayerStatsUiMapper(): PlayerStatsUiMapper = PlayerStatsUiMapper()
}

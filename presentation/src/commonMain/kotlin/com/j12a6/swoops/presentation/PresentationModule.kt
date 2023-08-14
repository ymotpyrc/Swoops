package com.j12a6.swoops.presentation

import com.j12a6.swoops.domain.DomainModule


class PresentationModule(private val domainModule: DomainModule) {

    fun provideFetchGamesViewModel(): FetchGamesViewModel =
        FetchGamesViewModel(
            fetchGamesUseCase = domainModule.provideFetchGamesUseCase(),
            fetchRecoverableGamesUseCase = domainModule.provideFetchGamesByIdsUseCase(),
        )

    fun providePlayerStatsViewModel(): PlayerStatsViewModel =
        PlayerStatsViewModel(
            getGamesUseCase = domainModule.provideGetGamesUseCase(),
            filterGamesOnFreeAgentsUseCase = domainModule.provideFilterGamesOnFreeAgentsUseCase(),
            filterGamesOnDateUseCase = domainModule.provideFilterGamesOnDateUseCase(),
            getPlayersStatsUseCase = domainModule.provideGetPlayerStatsUseCase(),
            playerStatsUiMapper = providePlayerStatsUiMapper(),
        )

    fun provideComparePlayersStatsViewModel(): ComparePlayersStatsViewModel =
        ComparePlayersStatsViewModel(
            getGamesUseCase = domainModule.provideGetGamesUseCase(),
            getPlayersStatsUseCase = domainModule.provideGetPlayerStatsUseCase(),
            playerStatsUiMapper = providePlayerStatsUiMapper(),
        )

//fun provideTeamOppositionsViewModel(): TeamOppositionsViewModel =
//    TeamOppositionsViewModel(
//        getGamesUseCase = domainModule.provideGetGamesUseCase(),
//        getPlayersStatsUseCase = domainModule.provideFilterGamesOnTeamUseCase(),
//        playerStatsUiMapper =
//    )

    private fun providePlayerStatsUiMapper(): PlayerStatsUiMapper = PlayerStatsUiMapper()
}

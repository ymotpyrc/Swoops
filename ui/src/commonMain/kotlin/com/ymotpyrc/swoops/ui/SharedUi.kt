package com.ymotpyrc.swoops.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.ymotpyrc.swoops.presentation.PresentationModule

@Composable
fun SharedUi(presentationModule: PresentationModule) {
    val coroutineScope = rememberCoroutineScope()
//    val mainScreenViewModel = MainScreenViewModel(scope)
//    val state: MainScreenUiModel = mainScreenViewModel.state.collectAsState().value

//    when (val type = Screen.ShowPlayerStats) {
//        Screen.FetchGames -> {
//    val fetchGamesViewModel = presentationModule.provideFetchGamesViewModel()
//    coroutineScope.launch {
//        fetchGamesViewModel.start()
//    }
//        }
//        Screen.CheckGames -> {
//            val checkGamesViewModel = presentationModule.provideCheckGamesViewModel()
//            scope.launch {
//                checkGamesViewModel.start()
//            }
//        }
//        Screen.FetchPlayers -> {
//            val fetchPlayersViewModel = presentationModule.provideFetchPlayersViewModel()
//            scope.launch {
//                fetchPlayersViewModel.start()
//            }
//        }
//        Screen.ShowPlayerStats -> {
    PlayerScreen(presentationModule, coroutineScope)
//        }
//        Screen.ShowTeamStats -> {
//            val teamOppositionsViewModel = presentationModule.provideTeamOppositionsViewModel()
//            scope.launch {
//                val oppositions = teamOppositionsViewModel.start(TeamId(id = 95))
//                oppositions.forEach { println(it) }
//            }
//        }
//    }
}

enum class Screen { FetchGames, CheckGames, FetchPlayers, ShowPlayerStats, ShowTeamStats }

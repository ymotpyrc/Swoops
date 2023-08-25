package com.ymotpyrc.swoops.presentation

class MainScreenMapper {

    fun mapToMainScreenUiModel(
        childScreen: ChildScreenUiModel,
    ): MainScreenUiModel = MainScreenUiModel(
        title = createTitle(childScreen),
        child = childScreen,
    )
}

private fun createTitle(childScreen: ChildScreenUiModel): String = when (childScreen) {
    is ChildScreenUiModel.Exit -> "Exit"
    is ChildScreenUiModel.FetchGames -> "Fetch Games"
    is ChildScreenUiModel.Player -> "Player"
}

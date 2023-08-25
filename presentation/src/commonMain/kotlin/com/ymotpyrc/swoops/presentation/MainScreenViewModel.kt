package com.ymotpyrc.swoops.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainScreenViewModel(
    private val scope: CoroutineScope,
):LinkHandler {

    private val mapper: MainScreenMapper = MainScreenMapper()

    private val childState = MutableStateFlow<ChildScreenUiModel>(ChildScreenUiModel.Player)

    val state: StateFlow<MainScreenUiModel> = childState
        .map(mapper::mapToMainScreenUiModel)
        .stateIn(
            scope = scope,
            started = SharingStarted.Lazily,
            initialValue = mapper.mapToMainScreenUiModel(ChildScreenUiModel.Player)
        )

    override fun handleLink(link: Link) {
        when (link) {
            Link.FetchGames -> childState.update { ChildScreenUiModel.FetchGames }
            Link.OpenPlayer -> childState.update { ChildScreenUiModel.Player }
        }
    }
}

sealed interface Link{
    object FetchGames : Link
    object OpenPlayer : Link
}

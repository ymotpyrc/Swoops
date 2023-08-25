package com.ymotpyrc.swoops.presentation

sealed interface ChildScreenUiModel {
    object Exit : ChildScreenUiModel
    object FetchGames : ChildScreenUiModel
    object Player : ChildScreenUiModel
}
package com.ymotpyrc.swoops.app.desktop

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.ymotpyrc.swoops.data.DefaultDataModule
import com.ymotpyrc.swoops.domain.DomainModule
import com.ymotpyrc.swoops.presentation.PresentationModule
import com.ymotpyrc.swoops.ui.SharedUi

val dataModule = DefaultDataModule()
val domainModule = DomainModule(dataModule)


fun main() = application {
    val windowState = rememberWindowState()
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Swoops Stats",
    ) {
        SharedUi(presentationModule = PresentationModule(domainModule))
    }
    LaunchedEffect(Unit) {
        windowState.size = DpSize(1600.dp, 1000.dp)
    }
}

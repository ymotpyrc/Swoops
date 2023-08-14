package com.j12a6.swoops

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.j12a6.swoops.data.DefaultDataModule
import com.j12a6.swoops.domain.DomainModule
import com.j12a6.swoops.presentation.PresentationModule
import com.j12a6.swoops.ui.SharedUi

val dataModule = DefaultDataModule()
val domainModule = DomainModule(dataModule)


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        SharedUi(presentationModule = PresentationModule(domainModule))
    }
}

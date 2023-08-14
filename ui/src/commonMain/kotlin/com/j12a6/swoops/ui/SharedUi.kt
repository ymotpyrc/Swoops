package com.j12a6.swoops.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.j12a6.swoops.presentation.PresentationModule

@Composable
fun SharedUi(presentationModule: PresentationModule) {
    LaunchedEffect("a") {
        val fetchGamesViewModel = presentationModule.provideFetchGamesViewModel()
        fetchGamesViewModel.start()
    }
    Box(modifier = Modifier.fillMaxSize().background(color = Color.Cyan))
}
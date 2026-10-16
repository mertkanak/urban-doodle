package com.example.grokapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

// TODO 1: Separate into Stateful container (HomeRoute) and Stateless composable (HomeScreen)
// HomeRoute(viewModel) -> HomeScreen(uiState, onRetry)

// TODO 2: In HomeScreen, handle exhaustive `when (uiState)` WITHOUT `else`:
// - HomeUiState.Loading -> Show CircularProgressIndicator
// - HomeUiState.Ready -> Show Profile Card + Announcement List
// - HomeUiState.Error -> Show Error message + "Tekrar dene" Button calling onRetry

// TODO 3: For Profile Card, experiment with Modifier order:
// Compare: background -> padding -> border -> clip VS padding -> background -> clip -> border
// Observe the visual differences for your PR description!

// TODO 4: Add @Preview composables for Loading, Ready, and Error states

@Composable
fun Homescreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModel.isim)
    }
}

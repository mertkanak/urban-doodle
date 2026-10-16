package com.example.grokapp.ui.home

import com.example.grokapp.dto.Announcement
import com.example.grokapp.dto.Profile

sealed interface HomeUiState {
    data object Loading : HomeUiState
    // TODO: Update Ready state parameters to hold Profile and List<Announcement> instead of String and List<String>
    data class Ready(val userName: String, val items: List<String>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

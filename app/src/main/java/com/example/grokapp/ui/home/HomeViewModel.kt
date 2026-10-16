package com.example.grokapp.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.grokapp.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    // TODO: Inject HomeRepository (or pass as constructor parameter)
) : ViewModel() {
    private val args = savedStateHandle.toRoute<Routes.HOME>()

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val isim: String = args.isim

    init {
        // TODO: Call loadData() when ViewModel is created
    }

    // TODO: Implement loadData() function:
    // 1. Set _uiState to HomeUiState.Loading
    // 2. Launch coroutine with viewModelScope.launch
    // 3. Log start timestamp (e.g., System.currentTimeMillis())
    // 4. Run repository.getProfile and repository.getAnnouncements IN PARALLEL using async { ... }
    // 5. Await both results (profileDeferred.await(), announcementsDeferred.await())
    // 6. Log end timestamp and calculated duration (must be ~1.5 seconds)
    // 7. Update _uiState to HomeUiState.Ready(profile, announcements)
    // 8. Catch exceptions and set _uiState to HomeUiState.Error(message)
}

package com.trackerblocker.sportsapp.ui

import androidx.lifecycle.ViewModel
import com.trackerblocker.sportsapp.data.SportUiState
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.model.Sport
import kotlinx.coroutines.flow.*

class SportViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        SportUiState(
            sports = SportsData.getSportsData(),
            selectedSport = SportsData.defaultSport
        )
    )
    val uiState: StateFlow<SportUiState> = _uiState.asStateFlow()

    fun setSport(sport: Sport) {
        _uiState.update { currentState -> currentState.copy(selectedSport = sport) }
    }
}
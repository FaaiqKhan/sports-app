package com.trackerblocker.sportsapp.data

import com.trackerblocker.sportsapp.model.Sport

data class SportUiState(
    val sports: List<Sport>,
    val selectedSport: Sport
)
package com.trackerblocker.sportsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.trackerblocker.sportsapp.data.SportsData

enum class SportsScreen {
    START,
    DETAILS
}

@Composable
fun SportsScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {
    SportsList(sports = SportsData.getSportsData(), modifier = modifier, onClick = onClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SportsScreen.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SportsScreen.START.name) {
                SportsScreen(onClick = { navController.navigate(SportsScreen.DETAILS.name) })
            }
            composable(route = SportsScreen.DETAILS.name) {
                SportDetails(sport = SportsData.defaultSport)
            }
        }
    }
}
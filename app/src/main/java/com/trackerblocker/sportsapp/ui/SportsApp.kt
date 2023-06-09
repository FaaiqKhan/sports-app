package com.trackerblocker.sportsapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.utils.UiUtils

enum class SportsScreens(@StringRes val title: Int) {
    START(title = R.string.list_fragment_label),
    DETAILS(title = R.string.detail_fragment_label)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsApp(windowSize: WindowWidthSizeClass) {
    val viewModel: SportViewModel = viewModel()
    val navController: NavHostController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = SportsScreens.valueOf(
        value = backStackEntry?.destination?.route ?: SportsScreens.START.name
    )

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> UiUtils.SportContentType.LIST_ONLY

        WindowWidthSizeClass.Expanded -> UiUtils.SportContentType.LIST_AND_DETAILS

        else -> UiUtils.SportContentType.LIST_ONLY
    }

    Scaffold(
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = SportsScreens.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SportsScreens.START.name) {
                SportsScreen(
                    uiState = uiState,
                    contentType = contentType,
                    onClick = { sportId ->
                        val sport = SportsData.getSportsData().find { it.id == sportId }
                        viewModel.setSport(sport!!)
                        if (contentType == UiUtils.SportContentType.LIST_ONLY) {
                            navController.navigate(SportsScreens.DETAILS.name)
                        }
                    }
                )
            }
            composable(route = SportsScreens.DETAILS.name) {
                SportDetails(uiState = uiState, contentType = contentType)
            }
        }
    }
}
package com.trackerblocker.sportsapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme

enum class SportsScreens(@StringRes val title: Int) {
    START(title = R.string.list_fragment_label),
    DETAILS(title = R.string.detail_fragment_label)
}

@Composable
fun SportsScreen(onClick: () -> Unit) {
    SportsList(
        sports = SportsData.getSportsData(),
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            .padding(top = dimensionResource(id = R.dimen.padding_small)),
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = SportsScreens.valueOf(
        value = backStackEntry?.destination?.route ?: SportsScreens.START.name
    )

    Scaffold(
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SportsScreens.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = SportsScreens.START.name) {
                SportsScreen(onClick = { navController.navigate(SportsScreens.DETAILS.name) })
            }
            composable(route = SportsScreens.DETAILS.name) {
                SportDetails(sport = SportsData.defaultSport)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    currentScreen: SportsScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SportsScreenPreview() {
    SportsAppTheme {
        SportsScreen {

        }
    }
}
package com.trackerblocker.sportsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportUiState
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme
import com.trackerblocker.sportsapp.utils.UiUtils

@Composable
fun SportsScreen(
    uiState: SportUiState,
    contentType: UiUtils.SportContentType,
    onClick: (Int) -> Unit
) {
    if (contentType == UiUtils.SportContentType.LIST_ONLY) {
        SportsList(
            sports = SportsData.getSportsData(),
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                .padding(top = dimensionResource(id = R.dimen.padding_small)),
            onClick = { onClick(it) }
        )
    } else {
        SportsListAndDetails(
            uiState = uiState,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                .padding(top = dimensionResource(id = R.dimen.padding_small)),
            onClick = { onClick(it) },
            contentType = contentType
        )
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
        SportsScreen(
            uiState = SportUiState(
                sports = SportsData.getSportsData(),
                selectedSport = SportsData.defaultSport
            ), contentType = UiUtils.SportContentType.LIST_ONLY
        ) {}
    }
}
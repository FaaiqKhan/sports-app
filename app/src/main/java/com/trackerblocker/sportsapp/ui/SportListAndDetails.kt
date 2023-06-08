package com.trackerblocker.sportsapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportUiState
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.ui.common.SportCart
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme
import com.trackerblocker.sportsapp.utils.UiUtils

@Composable
fun SportsListAndDetails(
    uiState: SportUiState,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    contentType: UiUtils.SportContentType
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            items(count = uiState.sports.size) { index ->
                SportCart(
                    sport = uiState.sports[index],
                    onClick = { onClick(it) }
                )
            }
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_medium)))
        SportDetails(uiState = uiState, modifier = Modifier.weight(1.3f), contentType = contentType)
    }
}

@Preview(name = "Light theme", showBackground = true, widthDp = 1000)
@Preview(
    name = "Dark theme",
    showBackground = true,
    widthDp = 1000,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun SportsListAndDetailsPreview() {
    SportsAppTheme {
        SportsListAndDetails(
            uiState = SportUiState(
                sports = SportsData.getSportsData(),
                selectedSport = SportsData.defaultSport
            ),
            contentType = UiUtils.SportContentType.LIST_AND_DETAILS,
            onClick = {}
        )
    }
}
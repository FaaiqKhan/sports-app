package com.trackerblocker.sportsapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportUiState
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.ui.common.SportCardImage
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme
import com.trackerblocker.sportsapp.utils.UiUtils

@Composable
fun SportDetails(
    uiState: SportUiState,
    modifier: Modifier = Modifier,
    contentType: UiUtils.SportContentType
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
    ) {
        SportCardImage(
            sport = uiState.selectedSport,
            modifier = Modifier.fillMaxWidth(),
            showContent = true,
            contentType = contentType
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(id = uiState.selectedSport.sportDetails),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_detail_content_horizontal))
                .verticalScroll(scrollState)
        )
    }
}

@Preview(name = "Light theme", showBackground = true)
@Preview(name = "Dark theme", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SportDetailsPreview() {
    SportsAppTheme {
        SportDetails(
            uiState = SportUiState(
                sports = SportsData.getSportsData(),
                selectedSport = SportsData.defaultSport
            ),
            contentType = UiUtils.SportContentType.LIST_ONLY
        )
    }
}
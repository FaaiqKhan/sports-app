package com.trackerblocker.sportsapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.model.Sport
import com.trackerblocker.sportsapp.ui.common.SportCardImage
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme

@Composable
fun SportDetails(sport: Sport, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        SportCardImage(sport = sport, modifier = Modifier.fillMaxWidth(), showContent = true)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        Text(
            text = stringResource(id = sport.sportDetails),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_detail_content_horizontal)
            )
        )
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SportDetailsPreview() {
    SportsAppTheme {
        SportDetails(sport = SportsData.defaultSport)
    }
}
package com.trackerblocker.sportsapp.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.model.Sport
import com.trackerblocker.sportsapp.ui.common.SportCart
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme

@Composable
fun SportsList(sports: List<Sport>, modifier: Modifier = Modifier, onClick: (Int) -> Unit) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen.padding_small)
        )
    ) {
        items(count = sports.size) { index ->
            SportCart(
                sport = sports[index],
                onClick = { onClick(it) }
            )
        }
    }
}

@Preview(name = "Light theme")
@Preview(name = "Dark theme", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SportsListPreview() {
    SportsAppTheme {
        SportsList(
            sports = SportsData.getSportsData(), modifier = Modifier.clip(
                shape = RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.card_corner_radius),
                    bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
                )
            )
        ) {}
    }
}
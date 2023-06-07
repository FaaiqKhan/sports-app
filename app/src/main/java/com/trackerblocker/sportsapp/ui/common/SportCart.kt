package com.trackerblocker.sportsapp.ui.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.data.SportsData
import com.trackerblocker.sportsapp.model.Sport
import com.trackerblocker.sportsapp.ui.theme.SportsAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SportCart(sport: Sport, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.card_elevation)
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(id = R.dimen.card_image_height))
        ) {
            SportCardImage(sport = sport)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    ),
                verticalArrangement = Arrangement.spacedBy(space = 4.dp)
            ) {
                Text(
                    text = stringResource(id = sport.titleResourceId),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(id = sport.subtitleResourceId),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.weight(1f))
                Row {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.player_count_caption,
                            count = sport.playerCount,
                            sport.playerCount
                        ),
                        style = MaterialTheme.typography.displaySmall,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (sport.olympic) {
                        Text(
                            text = stringResource(id = R.string.olympic_caption),
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light theme", showBackground = false)
@Preview(name = "Dark theme", showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SportCartPreview() {
    val sport = SportsData.defaultSport
    SportsAppTheme {
        SportCart(sport = sport)
    }
}
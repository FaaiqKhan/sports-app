package com.trackerblocker.sportsapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import com.trackerblocker.sportsapp.R
import com.trackerblocker.sportsapp.model.Sport

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SportCardImage(sport: Sport, modifier: Modifier = Modifier, showContent: Boolean = false) {
    val imageResourceId = if (showContent) sport.sportsImageBanner else sport.imageResourceId
    val contentScale = if (showContent) ContentScale.FillWidth else ContentScale.Fit
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier.height(dimensionResource(id = R.dimen.card_image_height)),
        )
        if (showContent) {
            Column(
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.card_image_height))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                            0f,
                            350f
                        )
                    ),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    text = stringResource(id = sport.titleResourceId),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
                        .padding(bottom = dimensionResource(id = R.dimen.padding_small))
                ) {
                    Text(
                        text = pluralStringResource(
                            id = R.plurals.player_count_caption,
                            count = sport.playerCount,
                            sport.playerCount
                        ),
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (sport.olympic) {
                        Text(
                            text = stringResource(id = R.string.olympic_caption),
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
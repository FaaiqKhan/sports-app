package com.trackerblocker.sportsapp.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.trackerblocker.sportsapp.R

@Composable
fun SportCardImage(@DrawableRes image: Int, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = image), contentDescription = null,
            modifier = modifier
                .height(dimensionResource(id = R.dimen.card_image_height))
                .clip(
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(id = R.dimen.card_corner_radius),
                        bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
                    )
                )
        )
    }
}
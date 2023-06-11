package com.trackerblocker.sportsapp

import com.trackerblocker.sportsapp.model.Sport

object DataStore {
    val sports = listOf(
        Sport(
            id = 3,
            titleResourceId = R.string.basketball,
            subtitleResourceId = R.string.sports_list_subtitle,
            playerCount = 5,
            olympic = true,
            imageResourceId = R.drawable.ic_basketball_square,
            sportsImageBanner = R.drawable.ic_basketball_banner,
            sportDetails = R.string.sport_detail_text
        ),
        Sport(
            id = 2,
            titleResourceId = R.string.badminton,
            subtitleResourceId = R.string.sports_list_subtitle,
            playerCount = 1,
            olympic = true,
            imageResourceId = R.drawable.ic_badminton_square,
            sportsImageBanner = R.drawable.ic_badminton_banner,
            sportDetails = R.string.sport_detail_text
        )
    )
}
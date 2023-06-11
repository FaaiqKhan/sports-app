package com.trackerblocker.sportsapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.trackerblocker.sportsapp.ui.SportsApp
import com.trackerblocker.sportsapp.ui.SportsScreens
import org.junit.*

class SportsAppNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupSportsNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            SportsApp(windowSize = WindowWidthSizeClass.Compact, navController = navController)
        }
    }

    @Test
    fun sportsNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(SportsScreens.START.name)
    }

    @Test
    fun sportsNavHost_verifyBackNavigationNotShownOnStartScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun sportsNavHost_clickOnSport() {
        val sport = DataStore.sports.first()
        composeTestRule.onNodeWithTag(sport.id.toString()).performClick()
        navController.assertCurrentRouteName(SportsScreens.DETAILS.name)

        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertExists()
    }
}
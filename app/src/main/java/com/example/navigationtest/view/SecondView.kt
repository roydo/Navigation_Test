package com.example.navigationtest.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SecondView(
    onNextButtonClicked: () -> Unit,
    toHomeView: () -> Unit,
    modifier: Modifier = Modifier
) {
    CommonView(
        modifier = modifier,
        positionString = "Second",
        toNextView = onNextButtonClicked,
        toHomeView = toHomeView,
        backgroundColor = Color.Yellow
        )
}
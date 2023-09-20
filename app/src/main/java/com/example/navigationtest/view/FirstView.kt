package com.example.navigationtest.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FirstView(
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
    toHomeView: () -> Unit
) {
    CommonView(
        modifier = modifier,
        positionString = "First",
        toNextView = onNextButtonClicked,
        toHomeView = toHomeView,
        backgroundColor = Color.Cyan
    )
}
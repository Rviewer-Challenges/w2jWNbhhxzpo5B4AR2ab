package com.p4r4d0x.hollowminds.presenter.welcome.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.common.HollowButton
import com.p4r4d0x.hollowminds.presenter.common.HollowText
import com.p4r4d0x.hollowminds.presenter.common.HorizontalHollowDivider


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeLayout(onContinue: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.welcome_background),
            contentDescription = "Welcome background"
        )
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            HollowText(
                modifier = Modifier
                    .width(300.dp)
                    .height(180.dp)
                    , textResource = R.string.welcome_description
            )
            HorizontalHollowDivider()
            HorizontalHollowDivider()
            HollowButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    ,
                textResource = R.string.btn_continue
            ) {
                onContinue.invoke()
            }
        }

    }

}
package com.p4r4d0x.hollowminds.presenter.welcome.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.common.HollowButton
import com.p4r4d0x.hollowminds.presenter.common.HollowText
import com.p4r4d0x.hollowminds.presenter.common.HorizontalHollowDivider
import java.util.*


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

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(200.dp)
                .fillMaxWidth()
                .padding(top = 60.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(
                    id = R.string.app_name
                ).uppercase(Locale.getDefault()),
                fontSize = 35.sp,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSecondary
            )
        }

        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HollowText(
                modifier = Modifier
                    .width(300.dp)
                    .height(180.dp), textResource = R.string.welcome_description
            )
            HorizontalHollowDivider(20)
            HollowButton(
                textResource = R.string.btn_continue
            ) {
                onContinue.invoke()
            }
        }

    }

}
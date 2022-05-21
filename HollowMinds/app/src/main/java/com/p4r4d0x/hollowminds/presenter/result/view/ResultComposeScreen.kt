package com.p4r4d0x.hollowminds.presenter.result.view

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultLayout(
    wonGame: Boolean,
    matchNumber: Int,
    totalNumber: Int,
    onRetry: () -> Unit,
    onExit: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.result_background),
            contentDescription = "Result background"
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
                    id = if (wonGame) {
                        R.string.result_win_title
                    } else {
                        R.string.result_lose_title
                    }
                ),
                fontSize = 30.sp,
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onSecondary
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HollowText(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp),
                textResource = if (wonGame) {
                    R.string.result_win
                } else {
                    R.string.result_lose
                },
                auxStringValue = matchNumber,
                auxStringValue2 = totalNumber

            )
            HorizontalHollowDivider(20)
            HollowButton(
                textResource = R.string.btn_retry
            ) {
                onRetry()
            }
            HorizontalHollowDivider(10)
            HollowButton(
                textResource = R.string.btn_exit
            ) {
                onExit()
            }
        }

    }


}
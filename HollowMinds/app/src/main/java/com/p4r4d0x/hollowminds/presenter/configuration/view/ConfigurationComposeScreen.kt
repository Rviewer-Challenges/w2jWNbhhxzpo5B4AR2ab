package com.p4r4d0x.hollowminds.presenter.configuration.view

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
import com.p4r4d0x.hollowminds.domain.bo.GameSize
import com.p4r4d0x.hollowminds.presenter.common.HollowButton
import com.p4r4d0x.hollowminds.presenter.common.HollowText
import com.p4r4d0x.hollowminds.presenter.common.HorizontalHollowDivider
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConfigurationLayout(viewModel: ConfigurationViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.configuration_background),
            contentDescription = "Configuration background"
        )

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HollowText(
                modifier = Modifier
                    .width(300.dp)
                    .height(60.dp), textResource = R.string.configuration_description
            )
            HorizontalHollowDivider(50)
            HollowButton(
                textResource = R.string.btn_game_4_4
            ) {
                viewModel.selectGameSize(GameSize.FourXFour)
            }
            HorizontalHollowDivider(5)
            HollowButton(
                textResource = R.string.btn_game_4_5
            ) {
                viewModel.selectGameSize(GameSize.FourXFive)
            }
            HorizontalHollowDivider(5)
            HollowButton(
                textResource = R.string.btn_game_5_6
            ) {
                viewModel.selectGameSize(GameSize.FiveXSix)
            }
        }

    }


}
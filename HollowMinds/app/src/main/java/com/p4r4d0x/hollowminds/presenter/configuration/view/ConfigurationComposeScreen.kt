package com.p4r4d0x.hollowminds.presenter.configuration.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.GameSize
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConfigurationLayout(viewModel: ConfigurationViewModel) {

    Row(modifier = Modifier.fillMaxHeight(),verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                onClick = { viewModel.selectGameSize(GameSize.FourXFour) }
            ) {
                Text(text = stringResource(id = R.string.btn_game_4_4))
            }

            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                onClick = { viewModel.selectGameSize(GameSize.FourXFive) }
            ) {
                Text(text = stringResource(id = R.string.btn_game_4_5))
            }

            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                onClick = { viewModel.selectGameSize(GameSize.FiveXSix) }
            ) {
                Text(text = stringResource(id = R.string.btn_game_5_6))
            }
        }
    }
}
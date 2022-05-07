package com.p4r4d0x.hollowminds.presenter.game.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.presenter.common.HollowButton
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel


@Composable
fun GameLayout(viewModel: GameViewModel, spanValue: Int, onReset: () -> Unit) {

    val time = viewModel.time.observeAsState()


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.game_background),
            contentDescription = "Game background"
        )
        Text(modifier = Modifier.align(Alignment.TopEnd), color = Color.White, text = time.value?:"00:00")

        GameGrid(viewModel, spanValue, Modifier.align(Alignment.Center))
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            HollowButton(
                modifier = Modifier
                    .height(40.dp)
                    .width(150.dp), textResource = R.string.btn_game_reset
            ) {
                onReset.invoke()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameGrid(viewModel: GameViewModel, spanValue: Int, modifier: Modifier = Modifier) {
    val data: List<CharacterCardData> = viewModel.characterCardsData.collectAsState().value
    LazyVerticalGrid(
        modifier = modifier,
        cells = GridCells.Fixed(spanValue),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(items = data) { _, item ->
            Card(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .padding(4.dp),
                backgroundColor = Color.White
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Image(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp),
                        painter = painterResource(id = item.characterImage),
                        contentDescription = "Character ${item.characterName} image "
                    )
                    Text(
                        text = item.characterName,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }

    }
}
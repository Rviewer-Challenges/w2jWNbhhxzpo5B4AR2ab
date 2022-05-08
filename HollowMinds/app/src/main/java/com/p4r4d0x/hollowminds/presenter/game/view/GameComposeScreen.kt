package com.p4r4d0x.hollowminds.presenter.game.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.presenter.common.HollowButton
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.theme.GreyLightTransparent

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
        Text(
            modifier = Modifier.align(Alignment.TopEnd),
            color = Color.White,
            text = time.value ?: "00:00"
        )

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
            GameCard(item)
        }
    }
}

@Composable
fun GameCard(item: CharacterCardData) {

    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(2.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable {
                    rotated = !rotated
                },
            border = BorderStroke(0.05.dp,MaterialTheme.colors.onSecondary),
            backgroundColor = GreyLightTransparent
        ) {
            if (rotated) {
                CharacterCardSide(item, rotated, rotation, animateBack, animateFront)
            } else {
                BackCardSide(rotated, rotation, animateBack, animateFront)
            }
        }
    }
}

@Composable
fun BackCardSide(rotated: Boolean, rotation: Float, animateBack: Float, animateFront: Float) {
    Image(
        modifier = Modifier
            .padding(5.dp)
            .graphicsLayer {
                alpha = if (rotated) animateBack else animateFront
                rotationY = rotation
            },
        painter = painterResource(id = R.drawable.back_card),
        contentDescription = "Back card"
    )
}

@Composable
fun CharacterCardSide(
    item: CharacterCardData,
    rotated: Boolean,
    rotation: Float,
    animateBack: Float,
    animateFront: Float
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Image(
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .align(CenterHorizontally)
                .graphicsLayer {
                    alpha = if (rotated) animateBack else animateFront
                    rotationY = rotation
                },
            painter = painterResource(id = item.characterImage),
            contentDescription = "Character ${item.characterName} image "
        )
        Text(
            text = item.characterName,
            fontSize = 11.sp,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer {
                    alpha = if (rotated) animateBack else animateFront
                    rotationY = rotation
                }
        )
    }
}


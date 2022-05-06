package com.p4r4d0x.hollowminds.presenter.game.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import java.util.*


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameLayout(viewModel: GameViewModel, spanValue:Int) {

    val data: List<CharacterCardData> = viewModel.characterCardsData.collectAsState().value
    LazyVerticalGrid(
        cells = GridCells.Fixed(spanValue),
        contentPadding = PaddingValues(8.dp)
    ) {
        itemsIndexed(items = data) { _, item ->
            Card(
                modifier = Modifier.width(100.dp).height(100.dp).padding(4.dp),
                backgroundColor = Color.White
            ) {
                Column(verticalArrangement = Arrangement.Center){
                    Image(modifier =  Modifier.width(70.dp).height(70.dp), painter = painterResource(id = item.characterImage), contentDescription = "" )
                    Text(
                        text = item.characterName,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }
        }

    }
}
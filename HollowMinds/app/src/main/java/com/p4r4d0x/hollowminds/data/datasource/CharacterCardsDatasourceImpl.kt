package com.p4r4d0x.hollowminds.data.datasource

import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData

class CharacterCardsDatasourceImpl : CharacterCardsDatasource {

    private val dataList = listOf(
        CharacterCardData("Ball", R.drawable.ball),
        CharacterCardData("Hollow Knight", R.drawable.chained_hollow_night),
        CharacterCardData("City Defender", R.drawable.city_defender),
        CharacterCardData("Cloth", R.drawable.cloth),
        CharacterCardData("Cornifer", R.drawable.cornifer),
        CharacterCardData("Dreak Knight", R.drawable.dream_knight),
        CharacterCardData("Elderbug", R.drawable.elderbug),
        CharacterCardData("Grey Mourner", R.drawable.grey_mourner),
        CharacterCardData("Holow Knight", R.drawable.hollow_knight),
        CharacterCardData("Holow Knight", R.drawable.holoww_knight),
        CharacterCardData("Hornet", R.drawable.hornet),
        CharacterCardData("Leg Eater", R.drawable.leg_eater),
        CharacterCardData("Mantis", R.drawable.mantis),
        CharacterCardData("Moss", R.drawable.moss),
        CharacterCardData("Nailsmith", R.drawable.nailsmith),
        CharacterCardData("Nosk", R.drawable.nosk),
        CharacterCardData("Palace Defender", R.drawable.palace_defender),
        CharacterCardData("Quirrel", R.drawable.quirrel),
        CharacterCardData("Shadow", R.drawable.shadow),
        CharacterCardData("White Defender", R.drawable.white_defender),
        CharacterCardData("White Knight", R.drawable.white_knight),
        CharacterCardData("Zote", R.drawable.zote)
    )

    override fun getCharacterCards(cardsNumber: Int): List<CharacterCardData> {
        return dataList
            .sortedBy { Math.random() }
            .takeIf { cardsNumber > 0 && it.size >= cardsNumber }
            ?.subList(0, cardsNumber) ?: emptyList()
    }
}
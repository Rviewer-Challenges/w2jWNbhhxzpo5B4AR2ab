package com.p4r4d0x.hollowminds.data.datasource

import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData

class CharacterCardsDatasourceImpl : CharacterCardsDatasource {

    private val dataList = listOf(
        CharacterCardData("Watcher", R.drawable.watcher),
        CharacterCardData("H. Knight", R.drawable.chained_hollow_night),
        CharacterCardData("Defender", R.drawable.city_defender),
        CharacterCardData("Cloth", R.drawable.cloth),
        CharacterCardData("Cornifer", R.drawable.cornifer),
        CharacterCardData("Galien", R.drawable.galien),
        CharacterCardData("Elderbug", R.drawable.elderbug),
        CharacterCardData("Mourner", R.drawable.grey_mourner),
        CharacterCardData("The knight", R.drawable.the_knight),
        CharacterCardData("Hornet", R.drawable.hornet),
        CharacterCardData("Leg Eater", R.drawable.leg_eater),
        CharacterCardData("Mantis", R.drawable.mantis),
        CharacterCardData("Moss", R.drawable.moss),
        CharacterCardData("Nailsmith", R.drawable.nailsmith),
        CharacterCardData("Nosk", R.drawable.nosk),
        CharacterCardData("Quirrel", R.drawable.quirrel),
        CharacterCardData("Shadow", R.drawable.shadow),
        CharacterCardData("W. Defender", R.drawable.white_defender),
        CharacterCardData("F. Knight", R.drawable.false_knight),
        CharacterCardData("Zote", R.drawable.zote)
    )

    override fun getCharacterCards(cardsNumber: Int): List<CharacterCardData> {

        val cardList = dataList
            .sortedBy { Math.random() }
            .takeIf { cardsNumber > 0 && it.size >= cardsNumber  }
            ?.subList(0, cardsNumber)

        return cardList?.let{ data->
            data.toMutableList().apply {
                addAll(data)
            }.sortedBy { Math.random() }
        } ?: run{
            emptyList()
        }

    }
}
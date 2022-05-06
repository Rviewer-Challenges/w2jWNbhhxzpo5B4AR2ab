package com.p4r4d0x.hollowminds.data.datasource

import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData

interface CharacterCardsDatasource {

    fun getCharacterCards(cardsNumber:Int) : List<CharacterCardData>

}
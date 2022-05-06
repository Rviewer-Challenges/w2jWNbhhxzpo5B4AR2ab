package com.p4r4d0x.hollowminds.domain

import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData

interface CharacterCardsRepository {

    fun getCharacterCards(cardsNumber:Int) : List<CharacterCardData>
}
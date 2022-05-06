package com.p4r4d0x.hollowminds.data

import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasource
import com.p4r4d0x.hollowminds.domain.CharacterCardsRepository

class CharacterCardsRepositoryImpl(private val datasource: CharacterCardsDatasource) :
    CharacterCardsRepository {

    override fun getCharacterCards(differentItems: Int) = datasource.getCharacterCards(differentItems)
}
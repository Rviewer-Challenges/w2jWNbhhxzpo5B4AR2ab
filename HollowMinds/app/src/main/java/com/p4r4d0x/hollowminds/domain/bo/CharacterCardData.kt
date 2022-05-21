package com.p4r4d0x.hollowminds.domain.bo

data class CharacterCardData(
    val characterName: String,
    val characterImage: Int,
    val matched: Boolean = false,
    val selected: Boolean = false
)
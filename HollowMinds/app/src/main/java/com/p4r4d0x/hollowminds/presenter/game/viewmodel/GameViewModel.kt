package com.p4r4d0x.hollowminds.presenter.game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class GameViewModel(private val getCharacterCardsUseCase: GetCharacterCardsUseCase) : ViewModel() {

    private val _characterCardsData = MutableStateFlow<List<CharacterCardData>>(emptyList())
    val characterCardsData: MutableStateFlow<List<CharacterCardData>>
        get() = _characterCardsData

    fun getCharacterCardsData(cardsNumber:Int){
        getCharacterCardsUseCase.invoke(viewModelScope,GetCharacterCardsUseCase.Params(cardsNumber)){characterList->
            _characterCardsData.value = characterList
        }
    }
}
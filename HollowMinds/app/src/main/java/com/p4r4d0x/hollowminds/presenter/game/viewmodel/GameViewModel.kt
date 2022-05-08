package com.p4r4d0x.hollowminds.presenter.game.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.hollowminds.domain.Utils.COUNTDOWN_INTERVAL
import com.p4r4d0x.hollowminds.domain.Utils.TIME_COUNTDOWN
import com.p4r4d0x.hollowminds.domain.Utils.formatTime
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.getFrom
import com.p4r4d0x.hollowminds.domain.setMachValues
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(private val getCharacterCardsUseCase: GetCharacterCardsUseCase) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private var firstSelectedCard: Pair<Int, CharacterCardData>? = null

    private val _characterCardsData =
        MutableLiveData<MutableMap<Int, CharacterCardData>>(mutableMapOf())
    val characterCardsData: MutableLiveData<MutableMap<Int, CharacterCardData>>
        get() = _characterCardsData

    private val _charactersLoaded = MutableLiveData<Boolean>()
    val charactersLoaded: MutableLiveData<Boolean>
        get() = _charactersLoaded

    private val _pairsMatched = MutableLiveData<Int>()
    val pairsMatched: MutableLiveData<Int>
        get() = _pairsMatched

    private val _totalPairs = MutableLiveData<Int>()
    val totalPairs: MutableLiveData<Int>
        get() = _totalPairs

    private val _time = MutableLiveData<String>()
    val time: MutableLiveData<String>
        get() = _time

    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished: MutableLiveData<Boolean>
        get() = _timerFinished

    private val _wonGame = MutableLiveData<Boolean>()
    val wonGame: MutableLiveData<Boolean>
        get() = _wonGame

    fun getCharacterCardsData(cardsNumber: Int) {
        getCharacterCardsUseCase.invoke(
            viewModelScope,
            GetCharacterCardsUseCase.Params(cardsNumber)
        ) { characterList ->
            _characterCardsData.value =
                characterList.mapIndexed { index, data -> index to data }.toMap().toMutableMap()
            _charactersLoaded.value = true
        }
    }

    fun startTimer() {
        _timerFinished.value = false
        countDownTimer = object : CountDownTimer(TIME_COUNTDOWN, COUNTDOWN_INTERVAL) {
            override fun onTick(millisRemaining: Long) {
                _time.value = millisRemaining.formatTime()
            }

            override fun onFinish() {
                countDownTimer?.cancel()
                _timerFinished.value = true
            }
        }.start()
    }

    fun setItemSelected(index: Int) {
        _characterCardsData.value?.let { map ->
            map[index]?.let { cardData ->
                map[index] = cardData.copy(selected = true)
            }
        }
    }

    fun itemRevealed(index: Int) {
        if (firstSelectedCard == null) {
            _characterCardsData.getFrom(index)?.let { characterCardData ->
                firstSelectedCard = index to characterCardData
            }

        } else {
            val secondSelectedCard = _characterCardsData.getFrom(index)
            firstSelectedCard?.let { fsc ->
                secondSelectedCard?.let { ssc ->
                    if (fsc.second.characterName == ssc.characterName) {
                        _characterCardsData.setMachValues(true,fsc.first, index)
                    } else {
                        viewModelScope.launch {
                            delay(1000)
                            _characterCardsData.setMachValues(false,fsc.first, index)
                        }
                    }
                }
            }
            firstSelectedCard = null

        }
    }

    fun checkGameStatus(){
        _characterCardsData.value?.let{ cardsData->
            val pairsNumber = cardsData.filter { it.value.matched }.count()/2
            val totalPairs = cardsData.count()/2
            _pairsMatched.value = pairsNumber
            _totalPairs.value = totalPairs
            _wonGame.value = pairsNumber ==totalPairs

        }
    }

}

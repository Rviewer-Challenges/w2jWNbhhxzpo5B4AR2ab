package com.p4r4d0x.hollowminds.presenter.game.viewmodel

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.hollowminds.domain.Utils.COUNTDOWN_INTERVAL
import com.p4r4d0x.hollowminds.domain.Utils.MATCH_PROCESS_DURATION
import com.p4r4d0x.hollowminds.domain.Utils.TIMER_COUNTDOWN
import com.p4r4d0x.hollowminds.domain.Utils.formatTime
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(private val getCharacterCardsUseCase: GetCharacterCardsUseCase) : ViewModel() {

    private var countDownTimer: CountDownTimer? = null

    private var firstSelectedCard: Pair<Int, CharacterCardData>? = null

    var characterCardsData by mutableStateOf<List<CharacterCardData>>(listOf())

    private val _charactersLoaded = MutableLiveData<Boolean>()
    val charactersLoaded: MutableLiveData<Boolean>
        get() = _charactersLoaded

    private val _pairsMatched = MutableLiveData<Int>()
    val pairsMatched: MutableLiveData<Int>
        get() = _pairsMatched

    private val _processingPair = MutableLiveData(false)
    val processingPair: MutableLiveData<Boolean>
        get() = _processingPair

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

    fun checkGameStatus() {
        val pairsNumber = characterCardsData.filter { it.matched }.count() / 2
        val totalPairs = characterCardsData.count() / 2
        _pairsMatched.value = pairsNumber
        _totalPairs.value = totalPairs
        _wonGame.value = pairsNumber == totalPairs
    }

    fun getCharacterCardsData(cardsNumber: Int) {
        getCharacterCardsUseCase.invoke(
            viewModelScope,
            GetCharacterCardsUseCase.Params(cardsNumber)
        ) { characterList ->
            characterCardsData = characterList
            _pairsMatched.value = 0
            _totalPairs.value = characterList.size / 2
            _charactersLoaded.value = true
        }
    }

    fun itemRevealed(index: Int) {
        if (firstSelectedCard == null) {
            characterCardsData[index].let { characterCardData ->
                firstSelectedCard = index to characterCardData
            }
        } else {
            val secondSelectedCard = characterCardsData[index]
            firstSelectedCard?.let { fsc ->
                secondSelectedCard.let { ssc ->
                    if (fsc.second.characterName == ssc.characterName) {
                        setItemInList(fsc.first, selected = true, matched = true)
                        setItemInList(index, selected = true, matched = true)
                    } else {
                        viewModelScope.launch {
                            _processingPair.value = true
                            delay(MATCH_PROCESS_DURATION)
                            setItemInList(fsc.first, selected = false, matched = false)
                            setItemInList(index, selected = false, matched = false)
                            _processingPair.value = false
                        }
                    }
                }
            }
            firstSelectedCard = null
        }
    }

    fun startTimer() {
        _timerFinished.value = false
        countDownTimer = object : CountDownTimer(TIMER_COUNTDOWN, COUNTDOWN_INTERVAL) {
            override fun onTick(millisRemaining: Long) {
                _time.value = millisRemaining.formatTime()
            }

            override fun onFinish() {
                countDownTimer?.cancel()
                _timerFinished.value = true
            }
        }.start()
    }

    fun setItemInList(index: Int, selected: Boolean, matched: Boolean) {
        val tmpList = characterCardsData.toMutableList()
        tmpList[index] = tmpList[index].copy(selected = selected, matched = matched)
        characterCardsData = tmpList
    }

}



package com.p4r4d0x.hollowminds.presenter.game.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class GameViewModel(private val getCharacterCardsUseCase: GetCharacterCardsUseCase) : ViewModel() {

    companion object {
        //time to countdown - 1hr - 60secs
        const val TIME_COUNTDOWN = 10000L
        const val COUNTDOWN_INTERVAL = 1000L
        private const val TIME_FORMAT = "%02d:%02d"
    }

    private var countDownTimer: CountDownTimer? = null

    //convert time to milli seconds
    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )


    private val _characterCardsData = MutableStateFlow<List<CharacterCardData>>(emptyList())
    val characterCardsData: MutableStateFlow<List<CharacterCardData>>
        get() = _characterCardsData

    private val _charactersLoaded = MutableLiveData<Boolean>()
    val charactersLoaded: MutableLiveData<Boolean>
        get() = _charactersLoaded

    private val _time = MutableLiveData<String>()
    val time: MutableLiveData<String>
        get() = _time

    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished: MutableLiveData<Boolean>
        get() = _timerFinished

    fun getCharacterCardsData(cardsNumber: Int) {
        getCharacterCardsUseCase.invoke(
            viewModelScope,
            GetCharacterCardsUseCase.Params(cardsNumber)
        ) { characterList ->
            _characterCardsData.value = characterList
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
}
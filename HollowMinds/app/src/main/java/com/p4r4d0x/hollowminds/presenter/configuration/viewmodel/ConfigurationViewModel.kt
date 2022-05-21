package com.p4r4d0x.hollowminds.presenter.configuration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.p4r4d0x.hollowminds.domain.bo.GameSize

class ConfigurationViewModel : ViewModel() {

    private val _gameSize = MutableLiveData<GameSize>()
    val gameSize: MutableLiveData<GameSize>
        get() = _gameSize

    fun selectGameSize(gameSize: GameSize) {
        _gameSize.value = gameSize
    }

}
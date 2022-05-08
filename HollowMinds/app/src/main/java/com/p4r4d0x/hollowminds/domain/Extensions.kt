package com.p4r4d0x.hollowminds.domain

import androidx.lifecycle.MutableLiveData
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData

fun MutableLiveData<MutableMap<Int, CharacterCardData>>.getFrom(index: Int) =
    this.value?.let { it[index] }

fun MutableLiveData<MutableMap<Int, CharacterCardData>>.setMachValues(matched:Boolean,firsItemIndex: Int, secondItemIndex:Int) {
    this.value?.let { map ->
        map[firsItemIndex]?.let{
            map[firsItemIndex] = it.copy(matched = matched, selected = matched)
        }
        map[secondItemIndex]?.let{
            map[secondItemIndex] = it.copy(matched = matched, selected = matched)
        }
    }
}

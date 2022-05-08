package com.p4r4d0x.hollowminds

import androidx.lifecycle.MutableLiveData
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.getFrom
import com.p4r4d0x.hollowminds.domain.setMachValues
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {
    private val characterCard1 = CharacterCardData("Name 1", 1)
    private val characterCard2 = CharacterCardData("Name 2", 2)
    private val characterCard3 = CharacterCardData("Name 2", 2)

    private val liveData = MutableLiveData(
        mutableMapOf(
            0 to characterCard1,
            1 to characterCard2,
            2 to characterCard3
        )
    )

    @Test
    fun `test livedata get from`() {
        Assert.assertEquals(characterCard2, liveData.getFrom(1))
    }

    @Test
    fun `test livedata set mach values`() {
        val sutLiveData = liveData

        sutLiveData.setMachValues(true,1,2)

        val expectedResult = MutableLiveData(
            mutableMapOf(
                0 to characterCard1,
                1 to characterCard2.copy(selected = true, matched = true),
                2 to characterCard3.copy(selected = true, matched = true)
            )
        )
        Assert.assertEquals(expectedResult.value,sutLiveData.value )
    }

    @Test
    fun `test livedata set not mach values`() {
        val sutLiveData = liveData

        sutLiveData.setMachValues(false,1,2)

        val expectedResult = MutableLiveData(
            mutableMapOf(
                0 to characterCard1,
                1 to characterCard2.copy(selected = false, matched = false),
                2 to characterCard3.copy(selected = false, matched = false)
            )
        )
        Assert.assertEquals(expectedResult.value,sutLiveData.value )
    }
}
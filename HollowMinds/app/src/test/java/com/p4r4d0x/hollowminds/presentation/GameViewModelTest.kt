package com.p4r4d0x.hollowminds.presentation

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.utils.*
import io.mockk.every
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApplication::class, sdk = [Build.VERSION_CODES.P])
class GameViewModelTest : KoinBaseTest(testViewmodelModule, testUsecasesModules) {

    companion object {
        const val CHARACTER_1_NAME = "TestName1"
        const val CHARACTER_2_NAME = "TestName2"
    }

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getCharacterCardsUseCase: GetCharacterCardsUseCase by inject()

    private lateinit var viewModelSUT: GameViewModel

    private val cardData = listOf(
        CharacterCardData(CHARACTER_1_NAME, 1),
        CharacterCardData(CHARACTER_2_NAME, 2),
        CharacterCardData(CHARACTER_1_NAME, 1),
        CharacterCardData(CHARACTER_2_NAME, 2)
    )

    private val cardMap = mutableMapOf(
        0 to CharacterCardData(CHARACTER_1_NAME, 1),
        1 to CharacterCardData(CHARACTER_2_NAME, 2),
        2 to CharacterCardData(CHARACTER_1_NAME, 1),
        3 to CharacterCardData(CHARACTER_2_NAME, 2)
    )

    @Before
    fun setUp() {
        viewModelSUT = GameViewModel(getCharacterCardsUseCase)
    }

    @Test
    fun `test get character cards data`() {
        invokeGetCharacterCardsData()

        viewModelSUT.getCharacterCardsData(2)

        val obtainedData = viewModelSUT.characterCardsData.value
        Assert.assertEquals(cardMap, obtainedData)
    }

    @Test
    fun `test start timer`() {
        viewModelSUT.startTimer()

        Assert.assertFalse(viewModelSUT.timerFinished.getOrAwaitValue())
    }

    @Test
    fun `test set item selected`() {
        invokeGetCharacterCardsData()

        viewModelSUT.setItemSelected(0)

        cardMap[0]?.let { cardMap[0] = it.copy(selected = true) }
        Assert.assertEquals(cardMap, viewModelSUT.characterCardsData.value)
    }

    @Test
    fun `test item revealed picked same item`() {
        invokeGetCharacterCardsData()

        viewModelSUT.itemRevealed(0)
        viewModelSUT.itemRevealed(2)

        cardMap[0]?.let { cardMap[0] = it.copy(matched = true, selected = true) }
        cardMap[2]?.let { cardMap[2] = it.copy(matched = true, selected = true) }
        Assert.assertEquals(cardMap, viewModelSUT.characterCardsData.value)
    }

    @Test
    fun `test item revealed picked different item`() = coroutinesTestRule.runBlockingTest {
        invokeGetCharacterCardsData()

        viewModelSUT.itemRevealed(0)
        viewModelSUT.itemRevealed(1)

        Assert.assertEquals(cardMap, viewModelSUT.characterCardsData.value)
    }

    private fun invokeGetCharacterCardsData() {
        val dataResult = slot<(List<CharacterCardData>?) -> Unit>()
        every {
            getCharacterCardsUseCase.invoke(
                scope = any(),
                params = any(),
                result = capture(dataResult)
            )
        } answers {
            dataResult.captured(cardData)
        }

        viewModelSUT.getCharacterCardsData(2)
    }

}
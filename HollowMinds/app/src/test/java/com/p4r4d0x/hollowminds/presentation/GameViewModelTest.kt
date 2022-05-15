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

    @Before
    fun setUp() {
        viewModelSUT = GameViewModel(getCharacterCardsUseCase)
    }

    @Test
    fun `test check game status game lose`() {
        invokeGetCharacterCardsData()

        viewModelSUT.checkGameStatus()

        Assert.assertEquals(0, viewModelSUT.pairsMatched.getOrAwaitValue())
        Assert.assertEquals(2, viewModelSUT.totalPairs.getOrAwaitValue())
        Assert.assertFalse(viewModelSUT.wonGame.getOrAwaitValue())
    }

    @Test
    fun `test check game status game won`() {
        invokeGetCharacterCardsData()
        Assert.assertEquals(0, viewModelSUT.movements.getOrAwaitValue())

        viewModelSUT.itemRevealed(0)
        viewModelSUT.itemRevealed(2)
        viewModelSUT.checkGameStatus()
        Assert.assertEquals(1, viewModelSUT.pairsMatched.getOrAwaitValue())
        Assert.assertEquals(2, viewModelSUT.totalPairs.getOrAwaitValue())
        Assert.assertEquals(1, viewModelSUT.movements.getOrAwaitValue())

        Assert.assertFalse(viewModelSUT.wonGame.getOrAwaitValue())

        viewModelSUT.itemRevealed(1)
        viewModelSUT.itemRevealed(3)
        viewModelSUT.checkGameStatus()
        Assert.assertEquals(2, viewModelSUT.pairsMatched.getOrAwaitValue())
        Assert.assertEquals(2, viewModelSUT.totalPairs.getOrAwaitValue())
        Assert.assertEquals(2, viewModelSUT.movements.getOrAwaitValue())
        Assert.assertTrue(viewModelSUT.wonGame.getOrAwaitValue())
    }

    @Test
    fun `test get character cards data`() {
        invokeGetCharacterCardsData()

        val obtainedData = viewModelSUT.characterCardsData

        Assert.assertEquals(cardData, obtainedData)
        Assert.assertEquals(0, viewModelSUT.pairsMatched.getOrAwaitValue())
        Assert.assertEquals(obtainedData.size / 2, viewModelSUT.totalPairs.getOrAwaitValue())
    }

    @Test
    fun `test item revealed picked same item`() {
        invokeGetCharacterCardsData()

        viewModelSUT.itemRevealed(0)
        viewModelSUT.itemRevealed(2)

        val expectedList = cardData.toMutableList()
        expectedList[0] = expectedList[0].copy(matched = true, selected = true)
        expectedList[2] = expectedList[2].copy(matched = true, selected = true)
        Assert.assertEquals(expectedList, viewModelSUT.characterCardsData)
    }

    @Test
    fun `test item revealed picked different item`() = coroutinesTestRule.runBlockingTest {
        invokeGetCharacterCardsData()

        viewModelSUT.itemRevealed(0)
        viewModelSUT.itemRevealed(1)

        Assert.assertEquals(cardData, viewModelSUT.characterCardsData)
    }

    @Test
    fun `test start timer`() {
        viewModelSUT.startTimer()

        Assert.assertFalse(viewModelSUT.timerFinished.getOrAwaitValue())
    }

    @Test
    fun `test set item selected`() {
        invokeGetCharacterCardsData()

        viewModelSUT.setItemInList(0, selected = true, matched = false)

        val expectedList = cardData.toMutableList()
        expectedList[0] = expectedList[0].copy(matched = false, selected = true)
        Assert.assertEquals(expectedList, viewModelSUT.characterCardsData)
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
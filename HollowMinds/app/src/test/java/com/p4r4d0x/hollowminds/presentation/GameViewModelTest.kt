package com.p4r4d0x.hollowminds.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.utils.*
import android.os.Build
import androidx.lifecycle.viewModelScope
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.MockKAnnotations
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

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getCharacterCardsUseCase: GetCharacterCardsUseCase by inject()

    private lateinit var viewModelSUT: GameViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelSUT = GameViewModel(getCharacterCardsUseCase)
    }

    @Test
    fun `test survey view model check log reported today`() = coroutinesTestRule.runBlockingTest {
        val dataResult = slot<(List<CharacterCardData>?) -> Unit>()
        val cardData = listOf(
            CharacterCardData("TestName1", 1),
            CharacterCardData("TestName2", 2)
        )
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

        val obtainedData = viewModelSUT.characterCardsData.value
        Assert.assertEquals(cardData,obtainedData)
    }

}
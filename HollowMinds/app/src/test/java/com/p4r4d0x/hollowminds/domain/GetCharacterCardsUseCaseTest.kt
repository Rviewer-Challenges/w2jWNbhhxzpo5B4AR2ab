package com.p4r4d0x.hollowminds.domain

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import com.p4r4d0x.hollowminds.utils.CoroutinesTestRule
import com.p4r4d0x.hollowminds.utils.KoinBaseTest
import com.p4r4d0x.hollowminds.utils.KoinTestApplication
import com.p4r4d0x.hollowminds.utils.testRepositoriesModule
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.inject
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApplication::class, sdk = [Build.VERSION_CODES.P])
class GetCharacterCardsUseCaseTest : KoinBaseTest(testRepositoriesModule) {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var useCase: GetCharacterCardsUseCase

    private val repository: CharacterCardsRepository by inject()

    companion object {
        private const val DIFFERENT_ITEMS = 4
    }

    @Before
    fun setUp() {
        useCase = GetCharacterCardsUseCase(repository)
    }

    @Test
    fun `test get character cards valid value`() {
        val cardList = listOf(CharacterCardData("name 1 ",1),CharacterCardData("name 2 ",2))
        coEvery { repository.getCharacterCards(DIFFERENT_ITEMS) } returns cardList

        runBlocking {
            useCase.invoke(params = GetCharacterCardsUseCase.Params(DIFFERENT_ITEMS)) {
                Assert.assertEquals(cardList, it )
            }
        }

        coVerify { repository.getCharacterCards(DIFFERENT_ITEMS) }
    }

}
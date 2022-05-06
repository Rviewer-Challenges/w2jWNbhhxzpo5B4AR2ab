package com.p4r4d0x.hollowminds.repository

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.hollowminds.data.CharacterCardsRepositoryImpl
import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasource
import com.p4r4d0x.hollowminds.domain.CharacterCardsRepository
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import com.p4r4d0x.hollowminds.utils.KoinBaseTest
import com.p4r4d0x.hollowminds.utils.KoinTestApplication
import com.p4r4d0x.hollowminds.utils.testDatasourcesModule
import com.p4r4d0x.hollowminds.utils.testRepositoriesModule
import io.mockk.every
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.koin.test.inject
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApplication::class, sdk = [Build.VERSION_CODES.P])
class CharacterCardsRepositoryTest : KoinBaseTest(testRepositoriesModule, testDatasourcesModule) {

    private val characterCardsDatasource: CharacterCardsDatasource by inject()

    private lateinit var repository: CharacterCardsRepository

    @Before
    fun setUp() {
        repository = CharacterCardsRepositoryImpl(characterCardsDatasource)
    }

    @Test
    fun `test get character cards`() {
        val characterCardList = listOf(
            CharacterCardData("TestName1", 1),
            CharacterCardData("TestName2", 2)
        )
        every {
            characterCardsDatasource.getCharacterCards(2)
        } returns characterCardList

        val obtainedCardList = repository.getCharacterCards(2)

        verify { characterCardsDatasource.getCharacterCards(2) }
        Assertions.assertEquals(characterCardList, obtainedCardList)
    }
}
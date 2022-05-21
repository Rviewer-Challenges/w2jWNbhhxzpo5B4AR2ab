package com.p4r4d0x.hollowminds.data

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasource
import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasourceImpl
import com.p4r4d0x.hollowminds.utils.KoinBaseTest
import com.p4r4d0x.hollowminds.utils.KoinTestApplication
import com.p4r4d0x.hollowminds.utils.testDatasourcesModule
import com.p4r4d0x.hollowminds.utils.testRepositoriesModule
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApplication::class, sdk = [Build.VERSION_CODES.P])
class CharacterCardsDatasourceTest : KoinBaseTest(testRepositoriesModule, testDatasourcesModule) {

    private lateinit var datasource: CharacterCardsDatasource

    @Before
    fun setUp() {
        datasource = CharacterCardsDatasourceImpl()
    }

    @Test
    fun `test get character cards valid value`() {
        val obtainedCards = datasource.getCharacterCards(8)

        Assertions.assertEquals(16, obtainedCards.size)
        obtainedCards.forEach { cardData ->
            Assertions.assertEquals(
                2,
                obtainedCards.count { it.characterName == cardData.characterName })
        }
    }

    @Test
    fun `test get character cards invalid value negative`() {
        val obtainedCards = datasource.getCharacterCards(-1)

        Assertions.assertEquals(0, obtainedCards.size)
    }

    @Test
    fun `test get character cards invalid value too big`() {
        val obtainedCards = datasource.getCharacterCards(30)

        Assertions.assertEquals(0, obtainedCards.size)
    }
}
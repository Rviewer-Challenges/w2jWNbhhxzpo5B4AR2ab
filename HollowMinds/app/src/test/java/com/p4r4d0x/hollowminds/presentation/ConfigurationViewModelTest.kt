package com.p4r4d0x.hollowminds.presentation

import android.os.Build
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.hollowminds.domain.bo.GameSize
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel
import com.p4r4d0x.hollowminds.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(application = KoinTestApplication::class, sdk = [Build.VERSION_CODES.P])
class ConfigurationViewModelTest : KoinBaseTest(testViewmodelModule, testUsecasesModules) {

    private lateinit var viewModelSUT: ConfigurationViewModel

    @Before
    fun setUp() {
        viewModelSUT = ConfigurationViewModel()
    }

    @Test
    fun `test get character cards data`() {
        viewModelSUT.selectGameSize(GameSize.FiveXSix)

        Assert.assertEquals(GameSize.FiveXSix, viewModelSUT.gameSize.getOrAwaitValue())
    }

}
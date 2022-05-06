package com.p4r4d0x.hollowminds.utils

import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasource
import com.p4r4d0x.hollowminds.domain.CharacterCardsRepository
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.presenter.result.viewmodel.ResultViewModel
import com.p4r4d0x.hollowminds.presenter.welcome.viewmodel.WelcomeViewModel
import io.mockk.mockk
import org.koin.dsl.module

val testViewmodelModule = module {
    factory { mockk<ConfigurationViewModel>() }
    factory { mockk<GameViewModel>() }
    factory { mockk<ResultViewModel>() }
    factory { mockk<WelcomeViewModel>() }
}

val testUsecasesModules = module {
    factory { mockk<GetCharacterCardsUseCase>() }
}

val testRepositoriesModule = module {
    factory { mockk<CharacterCardsRepository>() }
}

val testDatasourcesModule = module {
    factory { mockk<CharacterCardsDatasource>() }
}


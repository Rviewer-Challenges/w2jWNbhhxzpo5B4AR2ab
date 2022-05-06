package com.p4r4d0x.hollowminds.di

import com.p4r4d0x.hollowminds.data.CharacterCardsRepositoryImpl
import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasource
import com.p4r4d0x.hollowminds.data.datasource.CharacterCardsDatasourceImpl
import com.p4r4d0x.hollowminds.domain.CharacterCardsRepository
import com.p4r4d0x.hollowminds.domain.usecases.GetCharacterCardsUseCase
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.presenter.result.viewmodel.ResultViewModel
import com.p4r4d0x.hollowminds.presenter.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { ResultViewModel() }
    viewModel { GameViewModel(get()) }
    viewModel { ConfigurationViewModel() }
    viewModel { WelcomeViewModel() }
}

val usecasesModules = module {
    factory { GetCharacterCardsUseCase(get()) }
}

val repositoriesModule = module {
    factory<CharacterCardsRepository> { CharacterCardsRepositoryImpl(get()) }
}

val datasourcesModule = module {
    factory<CharacterCardsDatasource> { CharacterCardsDatasourceImpl() }
}


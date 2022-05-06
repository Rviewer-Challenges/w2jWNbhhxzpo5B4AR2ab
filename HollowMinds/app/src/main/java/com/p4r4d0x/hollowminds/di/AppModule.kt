package com.p4r4d0x.hollowminds.di

import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.presenter.result.viewmodel.ResultViewModel
import com.p4r4d0x.hollowminds.presenter.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { ResultViewModel() }
    viewModel { GameViewModel() }
    viewModel { ConfigurationViewModel() }
    viewModel { WelcomeViewModel() }
}

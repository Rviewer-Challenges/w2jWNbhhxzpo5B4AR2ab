package com.p4r4d0x.hollowminds.presenter

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.GameSize
import com.p4r4d0x.hollowminds.presenter.configuration.view.ConfigurationFragmentDirections
import java.security.InvalidParameterException

enum class FragmentScreen { Welcome, Configuration, Game, Result }

fun Fragment.navigate(from: FragmentScreen,to: FragmentScreen,gameSize: GameSize =GameSize.FourXFive) {
    val navController = findNavController()
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (from) {
        FragmentScreen.Welcome -> {
            when (to) {
                FragmentScreen.Configuration -> navController.navigate(R.id.action_welcomeFragment_to_configurationFragment)
                else -> {}
            }
        }
        FragmentScreen.Configuration -> {
            when (to) {
                FragmentScreen.Game -> {
                    val action = ConfigurationFragmentDirections.actionConfigurationFragmentToGameFragment().setGameSizeValue(gameSize)
                    navController.navigate(action)
                }
                else -> {}
            }
        }

        FragmentScreen.Game -> {
            when (to) {
                FragmentScreen.Result -> navController.navigate(R.id.action_gameFragment_to_resultFragment)
                FragmentScreen.Configuration -> navController.navigate(R.id.action_gameFragment_to_configurationFragment)

                else -> {}
            }
        }
        FragmentScreen.Result -> {
            when (to) {
                FragmentScreen.Configuration -> navController.navigate(R.id.action_resultFragment_to_configurationFragment)
                else -> {}
            }
        }
    }
}

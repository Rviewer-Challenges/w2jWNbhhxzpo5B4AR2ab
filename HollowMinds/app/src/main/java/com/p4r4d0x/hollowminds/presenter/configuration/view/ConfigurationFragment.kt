package com.p4r4d0x.hollowminds.presenter.configuration.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.FragmentScreen
import com.p4r4d0x.hollowminds.presenter.configuration.viewmodel.ConfigurationViewModel
import com.p4r4d0x.hollowminds.presenter.navigate
import org.koin.android.ext.android.inject

class ConfigurationFragment : Fragment() {

    private val viewModel: ConfigurationViewModel by inject()

    private fun observeViewModel() {
        with(viewModel) {
            gameSize.observe(viewLifecycleOwner) { gameSize ->
                navigate(FragmentScreen.Configuration, FragmentScreen.Game, gameSize)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.welcome_fragment
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setContent {
                ConfigurationLayout(viewModel)
            }
        }
    }
}
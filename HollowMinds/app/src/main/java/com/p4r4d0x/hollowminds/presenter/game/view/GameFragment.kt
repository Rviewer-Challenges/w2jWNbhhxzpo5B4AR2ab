package com.p4r4d0x.hollowminds.presenter.game.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import org.koin.android.ext.android.inject

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by inject()

    private fun observeViewModel() {
        with(viewModel) {

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

            }
        }
    }
}
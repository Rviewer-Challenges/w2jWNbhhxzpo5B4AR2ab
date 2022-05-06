package com.p4r4d0x.hollowminds.presenter.welcome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.domain.bo.GameSize
import com.p4r4d0x.hollowminds.presenter.FragmentScreen
import com.p4r4d0x.hollowminds.presenter.navigate
import com.p4r4d0x.hollowminds.presenter.welcome.viewmodel.WelcomeViewModel
import org.koin.android.ext.android.inject

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by inject()

    private fun observeViewModel() {
        with(viewModel) {

        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
        navigate(FragmentScreen.Welcome, FragmentScreen.Configuration)

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
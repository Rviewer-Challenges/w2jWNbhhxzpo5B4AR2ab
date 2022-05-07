package com.p4r4d0x.hollowminds.presenter.result.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.FragmentScreen
import com.p4r4d0x.hollowminds.presenter.game.view.GameFragmentArgs
import com.p4r4d0x.hollowminds.presenter.navigate
import com.p4r4d0x.hollowminds.theme.HollowMindsTheme

class ResultFragment : Fragment() {

    private val args: ResultFragmentArgs by navArgs()

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
                HollowMindsTheme {
                    ResultLayout(
                        args.wonGame,
                        args.matchNumber,
                        onExit = {
                            activity?.finish()
                        },
                        onRetry = {
                            navigate(FragmentScreen.Result, FragmentScreen.Configuration)
                        })
                }
            }
        }
    }
}
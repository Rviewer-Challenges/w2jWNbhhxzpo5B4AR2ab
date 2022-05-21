package com.p4r4d0x.hollowminds.presenter.game.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.p4r4d0x.hollowminds.R
import com.p4r4d0x.hollowminds.presenter.FragmentScreen
import com.p4r4d0x.hollowminds.presenter.game.viewmodel.GameViewModel
import com.p4r4d0x.hollowminds.presenter.navigate
import com.p4r4d0x.hollowminds.theme.HollowMindsTheme
import org.koin.android.ext.android.inject

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by inject()

    private val args: GameFragmentArgs by navArgs()

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
                    GameLayout(viewModel, args.gameSizeValue.spanValue) {
                        navigate(FragmentScreen.Game, FragmentScreen.Configuration)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeViewModel()
        viewModel.getCharacterCardsData(args.gameSizeValue.differentItems)
    }

    private fun observeViewModel() {
        with(viewModel) {
            charactersLoaded.observe(viewLifecycleOwner) {
                startTimer()
            }
            timerFinished.observe(viewLifecycleOwner) { timerFinished ->
                if (timerFinished) {
                    navigate(
                        from = FragmentScreen.Game,
                        to = FragmentScreen.Result,
                        wonGame = false,
                        matchNumber = pairsMatched.value?:0,
                        totalPairs = totalPairs.value?:0
                    )
                }
            }
            wonGame.observe(viewLifecycleOwner){ wonGame->
                if(wonGame){
                    navigate(
                        from = FragmentScreen.Game,
                        to = FragmentScreen.Result,
                        wonGame = true,
                        matchNumber = pairsMatched.value?:0,
                        totalPairs = totalPairs.value?:0
                    )
                }
            }
        }
    }
}
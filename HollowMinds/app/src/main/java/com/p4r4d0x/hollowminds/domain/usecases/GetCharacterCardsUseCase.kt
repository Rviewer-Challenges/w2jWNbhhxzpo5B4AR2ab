package com.p4r4d0x.hollowminds.domain.usecases

import com.p4r4d0x.hollowminds.domain.CharacterCardsRepository
import com.p4r4d0x.hollowminds.domain.bo.CharacterCardData
import kotlinx.coroutines.*

class GetCharacterCardsUseCase(private val characterCardRepository: CharacterCardsRepository) {

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(
        scope: CoroutineScope = GlobalScope,
        params:Params,
        result: (List<CharacterCardData>) -> Unit
    ) {
        val job = scope.async(Dispatchers.Default) {
            characterCardRepository.getCharacterCards(params.differentItems)
        }
        scope.launch(Dispatchers.Main) {
            result( job.await())
        }
    }

    class Params(val differentItems:Int)
}
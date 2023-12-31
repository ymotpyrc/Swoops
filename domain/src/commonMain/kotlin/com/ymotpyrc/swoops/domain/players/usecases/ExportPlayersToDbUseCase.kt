package com.ymotpyrc.swoops.domain.players.usecases

import com.ymotpyrc.swoops.domain.players.PlayerRepository

class ExportPlayersToDbUseCase(
    private val getPlayerAfterSkillsUpdateUseCase: GetPlayersAfterSkillsUpdateUseCase,
    private val playerRepository: PlayerRepository,
) {

    suspend fun invoke() {
//        val players = playerRepository.getPlayers()
        val players = playerRepository.getPlayersFromHtml()
        val updatedPlayers = getPlayerAfterSkillsUpdateUseCase.invoke(players)
        playerRepository.storePlayersIntoDb(updatedPlayers)
    }
}

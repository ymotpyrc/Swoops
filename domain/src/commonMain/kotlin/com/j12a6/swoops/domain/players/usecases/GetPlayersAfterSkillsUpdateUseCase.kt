package com.j12a6.swoops.domain.players.usecases

import com.j12a6.swoops.domain.players.models.Player
import com.j12a6.swoops.domain.players.models.SkillDetail
import com.j12a6.swoops.domain.players.models.SkillName
import com.j12a6.swoops.domain.players.models.SkillRank

class GetPlayersAfterSkillsUpdateUseCase {

    fun invoke(players: List<Player>): List<Player> = players.map(::updatePlayerSkills)

    private fun updatePlayerSkills(player: Player): Player {
        val notTopSkills = getSkillsWithoutTopSkills(player.skills, player.topSkills)
        val highestVisibleScoreSkillName = notTopSkills.maxByOrNull { it.value.score }!!.key
        val skillsToUpdate = player.skills.toMutableMap()
        updateMinScoresOfTopSkills(
            skillsToUpdate,
            player.topSkills[SkillRank.TOP_1]!!,
            player.topSkills[SkillRank.TOP_2]!!,
            player.topSkills[SkillRank.TOP_3]!!,
            highestVisibleScoreSkillName,
        )
        updateMaxScoresOfTopSkills(
            skillsToUpdate,
            player.topSkills[SkillRank.TOP_1]!!,
            player.topSkills[SkillRank.TOP_2]!!,
            player.topSkills[SkillRank.TOP_3]!!,
        )
        updateMaxScores(skillsToUpdate, player.topSkills)
        return player.copy(skills = skillsToUpdate)
    }

    private fun getSkillsWithoutTopSkills(
        skills: Map<SkillName, SkillDetail>,
        topSkills: Map<SkillRank, SkillName>,
    ): Map<SkillName, SkillDetail> =
        skills.filterNot { (skillName, _) -> topSkills.values.contains(skillName) }

    private fun updateMinScoresOfTopSkills(
        skills: MutableMap<SkillName, SkillDetail>,
        top1SkillName: SkillName,
        top2SkillName: SkillName,
        top3SkillName: SkillName,
        highestVisibleScoreSkillName: SkillName,
    ) {
        listOf(
            highestVisibleScoreSkillName,
            top3SkillName,
            top2SkillName,
            top1SkillName,
        ).zipWithNext().forEach { (toCopyFrom, toUpdate) ->
            if (!skills[toUpdate]!!.revealed) {
                skills[toUpdate] = skills[toUpdate]!!.copy(
                    minScore = getScoreOrMinScore(skills[toCopyFrom]!!),
                )
            }
        }
    }

    private fun updateMaxScoresOfTopSkills(
        skills: MutableMap<SkillName, SkillDetail>,
        top1SkillName: SkillName,
        top2SkillName: SkillName,
        top3SkillName: SkillName,
    ) {
        listOf(
            top1SkillName,
            top2SkillName,
            top3SkillName,
        ).zipWithNext().forEach { (toCopyFrom, toUpdate) ->
            if (!skills[toUpdate]!!.revealed) {
                skills[toUpdate] = skills[toUpdate]!!.copy(
                    minScore = getScoreOrMaxScore(skills[toCopyFrom]!!),
                )
            }
        }
    }

    private fun updateMaxScores(
        skills: MutableMap<SkillName, SkillDetail>,
        topSkills: Map<SkillRank, SkillName>,
    ) {
        skills.forEach { (skillName, skill) ->
            if (!skill.revealed && !topSkills.values.contains(skillName)) {
                skills[skillName] = skill.copy(
                    maxScore = getScoreOrMaxScore(skills[topSkills[SkillRank.TOP_3]]!!),
                )
            }
        }
    }

    private fun getScoreOrMinScore(skill: SkillDetail): Float = if (skill.revealed) skill.score else skill.minScore

    private fun getScoreOrMaxScore(skill: SkillDetail): Float = if (skill.revealed) skill.score else skill.maxScore
}
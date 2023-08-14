package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LineupPlayerBoxScoreDto(
    @SerialName("id") val id: Int,
    @SerialName("ast") val assists: Int,
    @SerialName("blk") val blocks: Int,
    @SerialName("drb") val defensiveRebounds: Int,
    @SerialName("fg") val fieldGoals: Int,
    @SerialName("fg_pct") val fieldGoalPercentage: Float,
    @SerialName("fga") val fieldGoalAttempts: Int,
    @SerialName("ft") val freeThrows: Int,
    @SerialName("ft_pct") val freeThrowPercentage: Float,
    @SerialName("fta") val freeThrowAttempts: Int,
    @SerialName("orb") val offensiveRebounds: Int,
    @SerialName("pf") val personalFouls: Int,
    @SerialName("pts") val points: Int,
    @SerialName("stl") val steals: Int,
    @SerialName("three_p") val threePoints: Int,
    @SerialName("three_p_pct") val threePointPercentage: Float,
    @SerialName("three_pa") val threePointAttempts: Int,
    @SerialName("tov") val turnovers: Int,
    @SerialName("trb") val totalRebounds: Int,
    @SerialName("two_p") val twoPoints: Int,
    @SerialName("two_p_pct") val twoPointPercentage: Float,
    @SerialName("two_pa") val twoPointAttempts: Int,
)

package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LineupPlayerStatsDto(
    @SerialName("id") val id: Int,
    @SerialName("team") val team: Int?,
    @SerialName("wins") val wins: Int?,
    @SerialName("losses") val losses: Int?,
    @SerialName("token") val token: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("age") val age: Int,
    @SerialName("star_rating") val starRating: Int,
    @SerialName("g") val g: String?,
    @SerialName("fg") val fieldGoals: String?,
    @SerialName("fga") val fieldGoalAttempts: String?,
    @SerialName("fg_pct") val fieldGoalPercentage: String?,
    @SerialName("three_p") val threePoints: String?,
    @SerialName("three_pa") val threePointAttempts: String?,
    @SerialName("three_p_pct") val threePointPercentage: String?,
    @SerialName("two_p") val twoPoints: String?,
    @SerialName("two_p_pct") val twoPointPercentage: String?,
    @SerialName("two_pa") val twoPointAttempts: String?,
    @SerialName("ft") val freeThrows: String?,
    @SerialName("fta") val freeThrowAttempts: String?,
    @SerialName("ft_pct") val freeThrowPercentage: String?,
    @SerialName("drpg") val defensiveReboundsPerGame: String?,
    @SerialName("orpg") val offensiveReboundsPerGame: String?,
    @SerialName("rpg") val reboundsPerGame: String?,
    @SerialName("apg") val assistsPerGame: String?,
    @SerialName("spg") val stealsPerGame: String?,
    @SerialName("bpg") val blocksPerGame: String?,
    @SerialName("fpg") val foulsPerGame: String?,
    @SerialName("ppg") val pointsPerGame: String?,
    @SerialName("tpg") val turnoversPerGame: String?,
)

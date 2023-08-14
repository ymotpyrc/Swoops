package com.j12a6.swoops.data.games.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsDto(
    @SerialName("lineup_1_score") val lineup1Score: Int,
    @SerialName("lineup_2_score") val lineup2Score: Int,
    @SerialName("lineup_1_box_score") val lineup1BoxScore: LineupBoxScoreDto,
    @SerialName("lineup_1_player_1_box_score") val lineup1player1BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_1_player_2_box_score") val lineup1player2BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_1_player_3_box_score") val lineup1player3BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_1_player_4_box_score") val lineup1player4BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_1_player_5_box_score") val lineup1player5BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_2_box_score") val lineup2BoxScore: LineupBoxScoreDto,
    @SerialName("lineup_2_player_1_box_score") val lineup2player1BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_2_player_2_box_score") val lineup2player2BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_2_player_3_box_score") val lineup2player3BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_2_player_4_box_score") val lineup2player4BoxScore: LineupPlayerBoxScoreDto,
    @SerialName("lineup_2_player_5_box_score") val lineup2player5BoxScore: LineupPlayerBoxScoreDto,
)

@file:OptIn(ExperimentalMaterial3Api::class)

package com.ymotpyrc.swoops.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ymotpyrc.swoops.domain.games.models.PlayerId
import com.ymotpyrc.swoops.domain.stats.models.PlayerPosition
import com.ymotpyrc.swoops.domain.stats.models.PlayerWithPosition
import com.ymotpyrc.swoops.domain.stats.models.Season
import com.ymotpyrc.swoops.presentation.PlayerState
import com.ymotpyrc.swoops.presentation.PlayerStatsUi
import com.ymotpyrc.swoops.presentation.PlayerStatsViewModel
import com.ymotpyrc.swoops.presentation.PresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PlayerScreen(presentationModule: PresentationModule, coroutineScope: CoroutineScope) {
    val playerStatsViewModel = presentationModule.providePlayerStatsViewModel(coroutineScope)
    val viewState = playerStatsViewModel.state.collectAsState()
    All(viewState, playerStatsViewModel, coroutineScope)
}

@Composable
fun All(
    viewState: State<PlayerState>,
    playerStatsViewModel: PlayerStatsViewModel,
    coroutineScope: CoroutineScope,
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .padding(48.dp),
    ) {
        Search(playerStatsViewModel, coroutineScope)
        Spacer(Modifier)
        when (val state = viewState.value) {
            is PlayerState.Data -> Data(state)
            is PlayerState.Loading -> Loading()
        }
    }
}

@Composable
fun Search(
    playerStatsViewModel: PlayerStatsViewModel,
    coroutineScope: CoroutineScope,
) {
    var text by rememberSaveable { mutableStateOf("585") }
    Row {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
        )
        Button(
            onClick = {
                coroutineScope.launch { playerStatsViewModel.search(PlayerId(id = text.toInt())) }
            },
        ) {
            Text(text = "Search", color = Color.White)
        }
    }
}

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Loading")
    }
}

@Composable
fun Data(state: PlayerState.Data) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Spacer(Modifier.height(24.dp))
            Text(state.playerId.id.toString())
            Text("5 stars")
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = "Age: X",
            )
            Spacer(Modifier.height(24.dp))

            val seasons = state.playersStatsUi.keys
                .map { it.season }
                .toSortedSet(compareByDescending {
                    when (it) {
                        is Season.All -> Int.MAX_VALUE
                        is Season.Number -> it.nb
                    }
                })
            seasons.forEach { season ->
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = formatSeason(season),
                )
                StatsTable(state, season)
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun StatsTable(state: PlayerState.Data, season: Season) {
    val headers = Header.values().toList()
    Box(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
    ) {
        Column {
            HeadersRow(headers)
            val positionOrder = getPlayerPositionsOrder()
            state.playersStatsUi
                .filterKeys { playerWithPosition -> playerWithPosition.season == season }
                .toSortedMap(compareBy { positionOrder.indexOf(it.playerPosition) })
                .forEach { (playerWithPosition, playerStat) ->
                    StatsRow(playerWithPosition, playerStat, headers)
                }
        }
    }
}

@Composable
fun HeadersRow(
    headers: List<Header>,
) {
    Row {
        headers.forEach { header ->
            Box(
                modifier = Modifier
                    .width(BOX_WIDTH)
                    .height(BOX_HEIGHT)
                    .border(width = 1.dp, color = Color.Black),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = header.title,
                )
            }
        }
    }
}

@Composable
fun StatsRow(
    playerWithPosition: PlayerWithPosition,
    playerStats: PlayerStatsUi,
    headers: List<Header>,
) {
    Row {
        headers.forEach { header ->
            Box(
                modifier = Modifier
                    .width(BOX_WIDTH)
                    .height(BOX_HEIGHT)
                    .border(width = 1.dp, color = Color.Black),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = when (header) {
                        Header.Position -> formatPosition(playerWithPosition.playerPosition)
                        Header.GamePlayed -> playerStats.played.toString()
                        Header.TrueShooting -> playerStats.trueShootingPercentage.formatToPercentage()
                        Header.PointPerGame -> playerStats.pointsPerGame.formatTo1Decimal()
                        Header.OffensiveReboundsPerGame -> playerStats.offensiveReboundsPerGame.formatTo1Decimal()
                        Header.DefensiveReboundsPerGame -> playerStats.defensiveReboundsPerGame.formatTo1Decimal()
                        Header.ReboundsPerGame -> playerStats.reboundsPerGame.formatTo1Decimal()
                        Header.AssistsPerGame -> playerStats.assistsPerGame.formatTo1Decimal()
                        Header.BlocksPerGame -> playerStats.blocksPerGame.formatTo1Decimal()
                        Header.StealsPerGame -> playerStats.stealsPerGame.formatTo1Decimal()
                        Header.TurnoversPerGame -> playerStats.turnoversPerGame.formatTo1Decimal()
                        Header.FoulsPerGame -> playerStats.foulsPerGame.formatTo1Decimal()
                        Header.FieldGoalPercentage -> playerStats.fieldGoalPercentagePerGame.formatToPercentage()
                        Header.TwoPointsPercentage -> playerStats.twoPointPercentagePerGame.formatToPercentage()
                        Header.ThreePointsPercentage -> playerStats.threePointPercentagePerGame.formatToPercentage()
                        Header.FreeThrowPercentage -> playerStats.freeThrowPercentagePerGame.formatToPercentage()
                        Header.FieldGoalAttemptsPerGame -> playerStats.fieldGoalAttemptsPerGame.formatTo1Decimal()
                        Header.TwoPointsAttemptsPerGame -> playerStats.twoPointAttemptsPerGame.formatTo1Decimal()
                        Header.ThreePointsAttemptsPerGame -> playerStats.threePointAttemptsPerGame.formatTo1Decimal()
                        Header.FreeThrowAttemptsPerGame -> playerStats.freeThrowAttemptsPerGame.formatTo1Decimal()
                    },
                    color = Color.Black,
                )
            }
        }
    }
}

enum class Header(val title: String) {
    Position(title = "POS"),
    GamePlayed(title = "GP"),
    TrueShooting(title = "TS"),
    PointPerGame(title = "PPG"),
    OffensiveReboundsPerGame(title = "ORPG"),
    DefensiveReboundsPerGame(title = "DRPG"),
    ReboundsPerGame(title = "RPG"),
    AssistsPerGame(title = "APG"),
    BlocksPerGame(title = "BPG"),
    StealsPerGame(title = "SPG"),
    TurnoversPerGame(title = "TPG"),
    FoulsPerGame(title = "FPG"),
    FieldGoalPercentage(title = "FG%"),
    TwoPointsPercentage(title = "2P%"),
    ThreePointsPercentage(title = "3P%"),
    FreeThrowPercentage(title = "FT%"),
    FieldGoalAttemptsPerGame(title = "FGA"),
    TwoPointsAttemptsPerGame(title = "2PA"),
    ThreePointsAttemptsPerGame(title = "3PA"),
    FreeThrowAttemptsPerGame(title = "FTA");
}

private val BOX_WIDTH = 48.dp
private val BOX_HEIGHT = 32.dp

fun Float.formatTo1Decimal(): String = "%.1f".format(this)

fun Float.formatToPercentage(): String = "%.0f".format(this * 100) + " %"

fun formatSeason(season: Season): String = when (season) {
    is Season.All -> "All seasons"
    is Season.Number -> "Season ${season.nb}"
}

private fun formatPosition(playerPosition: PlayerPosition): String =
    when (playerPosition) {
        PlayerPosition.Wherever -> "All"
        PlayerPosition.Guard -> "G"
        PlayerPosition.Forward -> "F"
        PlayerPosition.Center -> "C"
        PlayerPosition.One -> "1"
        PlayerPosition.Two -> "2"
        PlayerPosition.Three -> "3"
        PlayerPosition.Four -> "4"
        PlayerPosition.Five -> "5"
    }

private fun getPlayerPositionsOrder(): List<PlayerPosition> = listOf(
    PlayerPosition.One,
    PlayerPosition.Two,
    PlayerPosition.Three,
    PlayerPosition.Four,
    PlayerPosition.Five,
    PlayerPosition.Guard,
    PlayerPosition.Forward,
    PlayerPosition.Center,
    PlayerPosition.Wherever,
)

package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun saveGameState() {
    val gameState = mapOf(
        "gameHistory" to GameResultsService.gameHistory,
        "teamsStorage" to TeamService.teamsStorage,
        "lastIdentifier" to TeamService.identifierFactory.counter
    )
    File("game_state.json").writeText(Json.encodeToString(gameState))
}

fun loadGameState() {
    val gameState = Json.decodeFromString<Map<String, Any>>(File("game_state.json").readText())
    GameResultsService.gameHistory.addAll(gameState["gameHistory"] as List<GameResult>)
    TeamService.teamsStorage.putAll(gameState["teamsStorage"] as Map<Identifier, Team>)
    TeamService.identifierFactory.counter = gameState["lastIdentifier"] as Int
}
typealias Identifier = Int

class IdentifierFactory {
    private var counter = 0

    fun uniqueIdentifier(): Identifier = ++counter
}
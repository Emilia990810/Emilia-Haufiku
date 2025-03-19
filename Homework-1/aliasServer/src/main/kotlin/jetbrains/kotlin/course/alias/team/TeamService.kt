package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory

class TeamService {
    private val identifierFactory = IdentifierFactory()

    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamCount: Int): List<Team> {
        val teams = List(teamCount) {
            val id = identifierFactory.uniqueIdentifier()
            Team(id).also { team -> teamsStorage[id] = team }
        }
        return teams
    }
}

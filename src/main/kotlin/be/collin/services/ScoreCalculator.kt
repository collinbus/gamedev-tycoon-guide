package be.collin.services

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreScore

class ScoreCalculator {
    fun getBestScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>): MutableSet<Game> {
        val games = mutableSetOf<Game>()
        systems.forEach { system ->
            topics.forEach { topic ->
                val game = createGame(system, topic)
            }
        }
        return games
    }

    private fun createGame(system: GenreAudienceItem, topic: GenreAudienceItem): Game {
        val action = system.genreRatings.action.score + topic.genreRatings.action.score
        val adventure = system.genreRatings.action.score + topic.genreRatings.adventure.score
        val rpg = system.genreRatings.action.score + topic.genreRatings.rpg.score
        val simulation = system.genreRatings.action.score + topic.genreRatings.simulation.score
        val strategy = system.genreRatings.action.score + topic.genreRatings.strategy.score
        val casual = system.genreRatings.action.score + topic.genreRatings.casual.score
        return Game(topic, system, GenreScore(action, adventure, rpg, simulation, strategy, casual))
    }
}
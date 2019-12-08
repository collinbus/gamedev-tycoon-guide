package be.collin.services

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem

class GameService(private val calculator: ScoreCalculator) {
    fun getTop5Games(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>): List<Game> {
        val topRatedGames = calculator.calculateScores(topics, systems)
        return topRatedGames.flatMap { it.value }.subList(0,5)
    }
}
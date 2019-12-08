package be.collin.services

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem

class GameService(private val calculator: ScoreCalculator) {

    fun getTop5Games(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>, casualUnlocked: Boolean, targetAudience: Boolean): List<Game> {
        val games = getAllGames(topics,systems,casualUnlocked,targetAudience)
        val size = if(games.size > 5) 5 else games.size
        return games.subList(0,size)
    }

    fun getAllGames(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>, casualUnlocked: Boolean, targetAudience: Boolean): List<Game> {
        val topRatedGames = calculator.calculateScores(topics, systems, casualUnlocked, targetAudience)
        return topRatedGames.flatMap { it.value }
    }
}
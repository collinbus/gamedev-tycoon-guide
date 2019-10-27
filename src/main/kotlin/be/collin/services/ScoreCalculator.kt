package be.collin.services

import be.collin.domain.Game
import be.collin.domain.Genre
import be.collin.domain.GenreLayout
import be.collin.domain.GenreAudienceItem
import java.util.*

class ScoreCalculator {

    fun getBestScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>): MutableSet<Game> {
        val games = mutableSetOf<Game>()
        return games
    }
}
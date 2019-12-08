package be.collin.controllers

import be.collin.domain.GenreAudienceItem
import be.collin.services.ScoreCalculator

class GenerateGameController(private val systems: List<GenreAudienceItem>,
                             private val topics: List<GenreAudienceItem>) {


    private val scoreCalculator = ScoreCalculator()

    fun generateGame() {
        scoreCalculator.calculateScores(systems, topics)
    }
}
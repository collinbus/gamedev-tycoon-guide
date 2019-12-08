package be.collin.controllers

import be.collin.domain.GenreAudienceItem
import be.collin.services.SimpleScoreCalculator

class GenerateGameController(private val systems: List<GenreAudienceItem>,
                             private val topics: List<GenreAudienceItem>) {


    private val scoreCalculator = SimpleScoreCalculator()

    fun generateGame() {
        scoreCalculator.calculateScores(systems, topics)
    }
}
package be.collin.services

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem

interface ScoreCalculator {
    fun calculateScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>, casualUnlocked: Boolean, targetAudience: Boolean): Map<Int, List<Game>>
}
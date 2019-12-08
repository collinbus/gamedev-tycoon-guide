package be.collin.services

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.pcAndG64
import be.collin.tenTopics
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ScoreCalculatorTest {

    companion object {
        val PC_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BEST, Rating.BEST, Rating.WORST)
        val G64_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BETTER, Rating.BEST, Rating.WORSE)
        val DESKTOP_AUDIENCE_RATINGS = AudienceRatings(Rating.GOOD, Rating.BETTER, Rating.BEST)
    }

    @Test
    fun `Should return map of games by score when calculateScores is called`() {
        val service = SimpleScoreCalculator()
        val topics = tenTopics()
        val systems = pcAndG64()

        val games = service.calculateScores(topics, systems)

        assertEquals(27, games[14]?.size)
        assertEquals(30, games[13]?.size)
        assertEquals(11, games[12]?.size)
        assertEquals(12, games[11]?.size)
        assertEquals(4, games[9]?.size)
        assertEquals(8, games[8]?.size)
        assertEquals(8, games[7]?.size)
    }

}
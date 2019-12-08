package be.collin.services

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import junit.framework.TestCase.assertEquals
import org.junit.Ignore
import org.junit.Test

class ScoreCalculatorTest {

    companion object {
        val PC_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BEST, Rating.BEST, Rating.WORST)
        val G64_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BETTER, Rating.BEST, Rating.WORSE)
        val DESKTOP_AUDIENCE_RATINGS = AudienceRatings(Rating.GOOD, Rating.BETTER, Rating.BEST)
    }

    @Test
    fun `Should return map of games by score when calculateScores is called`() {
        val service = ScoreCalculator()
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

    private fun pcAndG64(): List<GenreAudienceItem> =
            listOf(GenreAudienceItem("PC", PC_RATINGS, DESKTOP_AUDIENCE_RATINGS),
                    GenreAudienceItem("G64", G64_RATINGS, DESKTOP_AUDIENCE_RATINGS))

    private fun tenTopics(): List<GenreAudienceItem> =
            listOf(GenreAudienceItem("BUSINESS", GenreRatings(Rating.WORST, Rating.GOOD, Rating.GOOD, Rating.BEST, Rating.BEST, Rating.WORST), AudienceRatings(Rating.BETTER, Rating.BEST, Rating.WORSE)),
                    GenreAudienceItem("DETECTIVE", GenreRatings(Rating.WORST, Rating.BEST, Rating.BEST, Rating.GOOD, Rating.WORST, Rating.BETTER), AudienceRatings(Rating.BETTER, Rating.BEST, Rating.GOOD)),
                    GenreAudienceItem("FANTASY", GenreRatings(Rating.BEST, Rating.BEST, Rating.BEST, Rating.GOOD, Rating.BEST, Rating.WORST), AudienceRatings(Rating.BEST, Rating.BEST, Rating.BEST)),
                    GenreAudienceItem("HORROR", GenreRatings(Rating.BEST, Rating.BEST, Rating.GOOD, Rating.WORST, Rating.WORSE, Rating.GOOD), AudienceRatings(Rating.WORST, Rating.BETTER, Rating.BEST)),
                    GenreAudienceItem("MEDIEVAL", GenreRatings(Rating.BEST, Rating.BEST, Rating.BEST, Rating.GOOD, Rating.BEST, Rating.WORSE), AudienceRatings(Rating.BEST, Rating.BEST, Rating.BETTER)),
                    GenreAudienceItem("PRISON", GenreRatings(Rating.BEST, Rating.BEST, Rating.GOOD, Rating.BEST, Rating.GOOD, Rating.WORST), AudienceRatings(Rating.WORSE, Rating.BETTER, Rating.BEST)),
                    GenreAudienceItem("ROMANCE", GenreRatings(Rating.WORST, Rating.BEST, Rating.GOOD, Rating.BETTER, Rating.WORST, Rating.BETTER), AudienceRatings(Rating.GOOD, Rating.BEST, Rating.BEST)),
                    GenreAudienceItem("SPY", GenreRatings(Rating.BEST, Rating.BEST, Rating.BEST, Rating.GOOD, Rating.WORSE, Rating.GOOD), AudienceRatings(Rating.GOOD, Rating.BETTER, Rating.BEST)),
                    GenreAudienceItem("VAMPIRE", GenreRatings(Rating.BEST, Rating.GOOD, Rating.BEST, Rating.WORST, Rating.WORST, Rating.WORSE), AudienceRatings(Rating.WORSE, Rating.BEST, Rating.BEST)),
                    GenreAudienceItem("SCI-FI", GenreRatings(Rating.BEST, Rating.BEST, Rating.BEST, Rating.BEST, Rating.BEST, Rating.GOOD), AudienceRatings(Rating.GOOD, Rating.BEST, Rating.BEST))
            )

}
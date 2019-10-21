package be.collin.services

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import org.junit.Assert
import org.junit.Test

class ScoreCalculatorTest {

    companion object {
        val PC_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BEST, Rating.BEST, Rating.WORST)
        val G64_RATINGS = GenreRatings(Rating.BETTER, Rating.BEST, Rating.BETTER, Rating.BETTER, Rating.BEST, Rating.WORSE)
        val DESKTOP_RATINGS = AudienceRatings(Rating.GOOD, Rating.BETTER, Rating.BEST)
    }

    @Test
    fun shouldGetBestFiveGames_When_GetTop5IsCalled() {
        val service = ScoreCalculator()
        val topics = listOf<GenreAudienceItem>()
        val systems = pcAndG64()

        val games = service.getBestScores(topics, systems)

        Assert.assertEquals(27, games.size)
    }

    private fun assertGamesInOrder() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun pcAndG64(): List<GenreAudienceItem> =
            listOf(GenreAudienceItem("PC", PC_RATINGS, DESKTOP_RATINGS),
                    GenreAudienceItem("G64", G64_RATINGS, DESKTOP_RATINGS))

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
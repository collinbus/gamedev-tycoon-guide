package be.collin

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.services.SimpleScoreCalculatorTest

fun pcAndG64(): List<GenreAudienceItem> =
        listOf(GenreAudienceItem("PC", SimpleScoreCalculatorTest.PC_RATINGS, SimpleScoreCalculatorTest.DESKTOP_AUDIENCE_RATINGS),
                GenreAudienceItem("G64", SimpleScoreCalculatorTest.G64_RATINGS, SimpleScoreCalculatorTest.DESKTOP_AUDIENCE_RATINGS))

fun tenTopics(): List<GenreAudienceItem> =
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
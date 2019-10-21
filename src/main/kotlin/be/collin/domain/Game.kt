package be.collin.domain

data class Game(val topic: GenreAudienceItem,
                val system: GenreAudienceItem,
                val genreRatings: GenreScore)

package be.collin.domain

data class Game(val topic: GenreAudienceItem,
                val system: GenreAudienceItem,
                val genre: Genre,
                val score: Int)

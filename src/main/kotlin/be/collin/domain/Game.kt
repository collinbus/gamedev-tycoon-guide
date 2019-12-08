package be.collin.domain

data class Game(val topic: GenreAudienceItem,
                val system: GenreAudienceItem,
                val genre: Genre,
                val score: Int) {

    override fun toString(): String {
        return "A $topic $genre game on the $system. Score: $score"
    }
}

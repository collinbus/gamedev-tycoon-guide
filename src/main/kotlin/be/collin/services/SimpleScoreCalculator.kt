package be.collin.services

import be.collin.domain.*

class SimpleScoreCalculator : ScoreCalculator {

    override fun calculateScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>): Map<Int,List<Game>> {
        val gameScores = mutableMapOf<Int,MutableList<Game>>()
        systems.forEach { system ->
            topics.forEach { topic ->
                Genre.values().forEach { genre ->
                    if (genre != Genre.CASUAL) {
                        val score = calculateScore(system, topic, genre)
                        val game = Game(topic, system, genre, score)
                        if (!gameScores.containsKey(score))
                            gameScores[score] = mutableListOf()
                        gameScores[score]!!.add(game)
                    }
                }
            }
        }
        return gameScores.toSortedMap(Comparator.reverseOrder())
    }

    private fun calculateScore(system: GenreAudienceItem, topic: GenreAudienceItem, genre: Genre): Int {
        val systemGenreRating = readGenreRating(genre,system.genreRatings)
        val topicGenreRating = readGenreRating(genre,topic.genreRatings)
        return systemGenreRating + topicGenreRating
    }

    private fun readGenreRating(genre: Genre, genreRatings: GenreRatings): Int {
        return when (genre) {
            Genre.ACTION -> genreRatings.action.score
            Genre.ADVENTURE -> genreRatings.adventure.score
            Genre.CASUAL -> genreRatings.casual.score
            Genre.RPG -> genreRatings.rpg.score
            Genre.SIMULATION -> genreRatings.simulation.score
            Genre.STRATEGY -> genreRatings.strategy.score
        }
    }
}
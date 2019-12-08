package be.collin.services

import be.collin.domain.*

class SimpleScoreCalculator : ScoreCalculator {

    private var targetAudience: Boolean = false
    private var casualUnlocked: Boolean = false

    override fun calculateScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>, casualUnlocked: Boolean, targetAudience: Boolean): Map<Int, List<Game>> {
        this.casualUnlocked = casualUnlocked
        this.targetAudience = targetAudience
        val gameScores = lookupGameCombinations(systems, topics)
        return gameScores.toSortedMap(Comparator.reverseOrder())
    }

    private fun lookupGameCombinations(systems: List<GenreAudienceItem>, topics: List<GenreAudienceItem>): MutableMap<Int, MutableList<Game>> {
        val gameScores = mutableMapOf<Int, MutableList<Game>>()
        systems.forEach { system ->
            topics.forEach { topic ->
                Genre.values().forEach { genre ->
                    handleGameCreation(genre, system, topic, gameScores)
                }
            }
        }
        return gameScores
    }

    private fun handleGameCreation(genre: Genre,
                                   system: GenreAudienceItem,
                                   topic: GenreAudienceItem,
                                   gameScores: MutableMap<Int, MutableList<Game>>) {
        if (genre == Genre.CASUAL && !casualUnlocked)
            return
        if (!targetAudience) {
            addGameToMap(system, topic, genre, gameScores, Audience.EVERYONE)
            return
        }
        Audience.values().forEach { audience ->
            addGameToMap(system, topic, genre, gameScores, audience)
        }
    }

    private fun addGameToMap(system: GenreAudienceItem, topic: GenreAudienceItem, genre: Genre, gameScores: MutableMap<Int, MutableList<Game>>, audience: Audience) {
        val score = calculateScore(system, topic, genre, audience)
        val game = Game(topic, system, genre, audience, score)
        if (!gameScores.containsKey(score))
            gameScores[score] = mutableListOf()
        gameScores[score]!!.add(game)
    }

    private fun calculateScore(system: GenreAudienceItem, topic: GenreAudienceItem, genre: Genre, audience: Audience): Int {
        val systemGenreRating = readGenreRating(genre, system.genreRatings)
        val topicGenreRating = readGenreRating(genre, topic.genreRatings)
        val score = systemGenreRating + topicGenreRating

        if (!targetAudience)
            return score

        val systemAudienceRating = readAudienceRating(audience, system.audienceRatings)
        val topicAudienceRating = readAudienceRating(audience, topic.audienceRatings)
        return score + systemAudienceRating + topicAudienceRating
    }

    private fun readAudienceRating(audience: Audience, ratings: AudienceRatings): Int {
        return when(audience) {
            Audience.YOUNG -> ratings.young.score
            Audience.EVERYONE -> ratings.everyone.score
            Audience.MATURE -> ratings.mature.score
        }
    }

    private fun readGenreRating(genre: Genre, ratings: GenreRatings): Int {
        return when (genre) {
            Genre.ACTION -> ratings.action.score
            Genre.ADVENTURE -> ratings.adventure.score
            Genre.CASUAL -> ratings.casual.score
            Genre.RPG -> ratings.rpg.score
            Genre.SIMULATION -> ratings.simulation.score
            Genre.STRATEGY -> ratings.strategy.score
        }
    }
}
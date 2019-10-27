package be.collin.services

import be.collin.domain.*

class ScoreCalculator {

    fun getBestScores(topics: List<GenreAudienceItem>, systems: List<GenreAudienceItem>): MutableList<Game> {
        val games = mutableListOf<Game>()
        val topScorePerSystem = mutableMapOf<GenreAudienceItem, List<Pair<Genre, Rating>>>()
        val topScorePerTopic = mutableMapOf<GenreAudienceItem, List<Pair<Genre, Rating>>>()

        systems.forEach { system ->
            topScorePerSystem[system] = sortGenres(system.genreRatings)
        }

        topics.forEach { topic ->
            topScorePerTopic[topic] = sortGenres(topic.genreRatings)
        }

        topScorePerSystem.keys.forEach { system ->
            topScorePerTopic.keys.forEach { topic ->
                val systemScores = topScorePerSystem[system]!!
                val topicScores = topScorePerTopic[topic]!!
                //TODO get corresponding genre per system and topic
            }
        }

        return games
    }

    private fun sortGenres(genreRatings: GenreRatings): List<Pair<Genre, Rating>> {
        val genres = mutableListOf(Pair(Genre.ACTION, genreRatings.action), Pair(Genre.RPG, genreRatings.rpg),
                Pair(Genre.ADVENTURE, genreRatings.adventure), Pair(Genre.CASUAL, genreRatings.casual),
                Pair(Genre.SIMULATION, genreRatings.simulation), Pair(Genre.STRATEGY, genreRatings.strategy))

        genres.sortByDescending { pair -> pair.second.score }
        return genres.toList()
    }
}
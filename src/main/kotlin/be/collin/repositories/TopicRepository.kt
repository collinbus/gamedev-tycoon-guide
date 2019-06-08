package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.domain.Topic
import java.util.*

class TopicRepository {

    private val ratings = Rating.values().associateBy(Rating::score)

    fun getTopics(): List<Topic> {
        val topics = mutableListOf<Topic>()
        val scanner = Scanner(javaClass.classLoader.getResourceAsStream("genre-test.csv"))
        scanner.nextLine()
        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            topics.add(readTopic(line))
        }
        return topics
    }

    private fun readTopic(line: String): Topic {
        val fields = line.split(";")
        val name = fields[0]
        val genreRatings = readGenreRatings(fields)
        val audienceRatings = readAudienceRatings(fields)
        return Topic(name, genreRatings, audienceRatings)
    }

    private fun readGenreRatings(fields: List<String>): GenreRatings {
        return GenreRatings(
                ratings.getValue(fields[1].toInt()),
                ratings.getValue(fields[2].toInt()),
                ratings.getValue(fields[3].toInt()),
                ratings.getValue(fields[4].toInt()),
                ratings.getValue(fields[5].toInt()),
                ratings.getValue(fields[6].toInt())
        )
    }

    private fun readAudienceRatings(fields: List<String>): AudienceRatings {
        return AudienceRatings(
                ratings.getValue(fields[7].toInt()),
                ratings.getValue(fields[8].toInt()),
                ratings.getValue(fields[9].toInt())
        )
    }
}
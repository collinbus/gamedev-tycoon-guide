package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.domain.Topic
import be.collin.exceptions.ReadingException
import java.io.InputStream
import java.util.*

class TopicRepository(private val csvFile: InputStream) {

    private val ratings = Rating.values().associateBy(Rating::score)

    fun getTopics(): List<Topic> {
        val topics = mutableListOf<Topic>()
        val scanner = Scanner(csvFile)

        if (scanner.hasNextLine())
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

        if (name == "")
            throw ReadingException(ReadingException.NO_NAME)

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
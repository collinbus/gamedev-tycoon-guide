package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.io.FileReader

class GenreAudienceRepository(private val fileReader: FileReader): RatingRepository() {

    private val ratings = Rating.values().associateBy(Rating::score)

    fun readItems() : List<GenreAudienceItem> {
        val items = mutableListOf<GenreAudienceItem>()
        val lines = fileReader.readFile()
        lines.forEach { line ->
            items.add(readItem(line))
        }
        return items
    }

    private fun readItem(line: String): GenreAudienceItem {
        val (fields, name) = parseFields(line)
        checkForErrors(fields, name)

        val genreRatings = readGenreRatings(fields)
        val audienceRatings = readAudienceRatings(fields)
        return GenreAudienceItem(name, genreRatings, audienceRatings)
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
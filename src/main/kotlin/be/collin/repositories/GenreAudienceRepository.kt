package be.collin.repositories

import be.collin.domain.*
import be.collin.exceptions.ReadingException
import java.io.InputStream
import java.util.*

class GenreAudienceRepository(private val csvFile: InputStream) {

    private val ratings = Rating.values().associateBy(Rating::score)

    fun getItems(): List<GenreAudienceItem> {
        val genreAudienceItems = mutableListOf<GenreAudienceItem>()
        readCsvFile(genreAudienceItems)

        return genreAudienceItems
    }

    private fun readCsvFile(genreAudienceItems: MutableList<GenreAudienceItem>) {
        val scanner = Scanner(csvFile)

        if (scanner.hasNextLine())
            scanner.nextLine()

        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            genreAudienceItems.add(readItem(line))
        }
    }

    private fun readItem(line: String): GenreAudienceItem {
        val (fields, name) = checkForErrors(line)

        val genreRatings = readGenreRatings(fields)
        val audienceRatings = readAudienceRatings(fields)
        return GenreAudienceItem(name, genreRatings, audienceRatings)
    }

    private fun checkForErrors(line: String): Pair<List<String>, String> {
        val fields = line.split(";")
        val name = fields[0]

        if (fields.size != 10)
            throw ReadingException(ReadingException.INSUFFICIENT_COLUMNS)
        if (name == "")
            throw ReadingException(ReadingException.NO_NAME)
        return Pair(fields, name)
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
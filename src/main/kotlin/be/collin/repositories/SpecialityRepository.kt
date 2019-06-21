package be.collin.repositories

import be.collin.domain.Genre
import be.collin.domain.Rating
import be.collin.io.FileReader

class SpecialityRepository(private val fileReader: FileReader): RatingRepository() {

    private val ratings = Rating.values().associateBy(Rating::score)

    fun readItems(): List<Genre> {
        val genres = mutableListOf<Genre>()
        val lines = fileReader.readFile()

        lines.forEach { line ->
            genres.add(readGenre(line))
        }

        return genres
    }

    private fun readGenre(line: String): Genre {
        val (fields, name) = parseFields(line)
        checkForErrors(fields, name)

        return Genre(name,
                ratings.getValue(fields[1].toInt()),
                ratings.getValue(fields[2].toInt()),
                ratings.getValue(fields[3].toInt()),
                ratings.getValue(fields[4].toInt()),
                ratings.getValue(fields[5].toInt()),
                ratings.getValue(fields[6].toInt()),
                ratings.getValue(fields[7].toInt()),
                ratings.getValue(fields[8].toInt()),
                ratings.getValue(fields[9].toInt()))
    }
}
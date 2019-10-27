package be.collin.repositories

import be.collin.domain.Genre
import be.collin.domain.GenreLayout
import be.collin.domain.Rating
import be.collin.io.FileReader
import be.collin.io.parser.CsvFieldParser

class SpecialityRepository(private val fileReader: FileReader) : RatingRepository<GenreLayout> {

    private val ratings = Rating.values().associateBy(Rating::score)

    override fun readItems(): List<GenreLayout> {
        val genres = mutableListOf<GenreLayout>()
        val lines = fileReader.readFile()

        lines.forEach { line ->
            genres.add(readGenre(line))
        }

        return genres
    }

    private fun readGenre(line: String): GenreLayout {
        val (fields, name) = CsvFieldParser.parseFields(line)
        CsvFieldParser.checkForErrors(fields, name)

        return GenreLayout(readGenreName(name),
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

    private fun readGenreName(name: String): Genre {
        return when (name) {
            "ADVENTURE" -> Genre.ADVENTURE
            "CASUAL" -> Genre.CASUAL
            "RPG" -> Genre.RPG
            "SIMULATION" -> Genre.SIMULATION
            "STRATEGY" -> Genre.STRATEGY
            else -> Genre.ACTION
        }
    }
}
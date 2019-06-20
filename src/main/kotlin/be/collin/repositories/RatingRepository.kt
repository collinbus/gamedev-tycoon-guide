package be.collin.repositories

import be.collin.exceptions.ReadingException
import be.collin.io.FileReader

abstract class RatingRepository {
    protected fun parseFields(line: String): Pair<List<String>, String> {
        val fields = line.split(";")
        val name = fields[0]
        return Pair(fields, name)
    }

    protected fun checkForErrors(fields: List<String>, name: String) {
        if (fields.size != 10)
            throw ReadingException(ReadingException.INSUFFICIENT_COLUMNS)
        if (name == "")
            throw ReadingException(ReadingException.NO_NAME)
    }
}

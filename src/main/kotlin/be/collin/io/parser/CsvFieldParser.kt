package be.collin.io.parser

import be.collin.exceptions.ReadingException

class CsvFieldParser {
    companion object {
        fun parseFields(line: String): Pair<List<String>, String> {
            val fields = line.split(";")
            val name = fields[0]
            return Pair(fields, name)
        }

        fun checkForErrors(fields: List<String>, name: String) {
            if (fields.size != 10)
                throw ReadingException(ReadingException.INSUFFICIENT_COLUMNS)
            if (name == "")
                throw ReadingException(ReadingException.NO_NAME)
        }
    }
}

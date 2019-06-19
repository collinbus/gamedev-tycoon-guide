package be.collin.exceptions

import java.lang.Exception

class ReadingException(override val message: String) : Exception() {
    companion object {
        const val INSUFFICIENT_COLUMNS = "A line of the csv file does not contain 10 columns"
        const val NO_NAME = "A line of the csv file does not contain a name"
    }
}

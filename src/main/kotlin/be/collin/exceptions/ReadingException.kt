package be.collin.exceptions

import java.lang.Exception

class ReadingException(override val message: String) : Exception() {
    companion object {
        const val NO_NAME = "The csv file contains no name"
    }
}

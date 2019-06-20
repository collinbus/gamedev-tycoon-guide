package be.collin.io

import java.io.InputStream
import java.util.*

class CsvReader(private val csvFile: InputStream) {

    fun readCsvFile(): List<String> {
        val lines = mutableListOf<String>()
        val scanner = Scanner(csvFile)

        if (scanner.hasNextLine())
            scanner.nextLine()

        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            lines.add(line)
        }
        return lines
    }
}
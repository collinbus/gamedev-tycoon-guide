package be.collin.io

import java.io.InputStream
import java.util.*

class CsvReader(private val csvFile: InputStream) : FileReader {

    override fun readFile(): List<String> {
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
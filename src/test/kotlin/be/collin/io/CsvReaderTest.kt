package be.collin.io

import org.junit.Assert.*
import org.junit.Test

class CsvReaderTest {

    private val correctCSV = javaClass.classLoader.getResourceAsStream("genre-test.csv")

    @Test
    fun shouldLoadCorrectCsv_When_ReadCsvWithValidData() {
        val reader = CsvReader(correctCSV)

        val lines = reader.readFile()

        assertEquals("Airplane;7;1;4;7;7;7;7;7;6",lines[0])
        assertEquals("Music;7;6;1;7;1;7;7;6;5",lines[1])
        assertEquals("Evolution;2;1;1;7;7;1;5;7;2",lines[2])
    }
}
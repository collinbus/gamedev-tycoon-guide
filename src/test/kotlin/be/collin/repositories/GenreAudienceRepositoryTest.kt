package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.exceptions.ReadingException
import be.collin.io.FileReader
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GenreAudienceRepositoryTest {

    companion object {
        val AIRPLANE_TOPIC = GenreAudienceItem("Airplane",
                genreRatings = GenreRatings(Rating.BEST, Rating.WORST, Rating.DONT_CARE,
                        Rating.BEST, Rating.BEST, Rating.BEST),
                audienceRatings = AudienceRatings(Rating.BEST, Rating.BEST, Rating.BETTER))
        val MUSIC_TOPIC = GenreAudienceItem("Music",
                genreRatings = GenreRatings(Rating.BEST, Rating.BETTER, Rating.WORST,
                        Rating.BEST, Rating.WORST, Rating.BEST),
                audienceRatings = AudienceRatings(Rating.BEST, Rating.BETTER, Rating.GOOD))
        val EVOLUTION_TOPIC = GenreAudienceItem("Evolution",
                genreRatings = GenreRatings(Rating.WORSE, Rating.WORST, Rating.WORST,
                        Rating.BEST, Rating.BEST, Rating.WORST),
                audienceRatings = AudienceRatings(Rating.GOOD, Rating.BEST, Rating.WORSE))
    }

    private val correctCsv: List<String> = mutableListOf(
            "Airplane;7;1;4;7;7;7;7;7;6",
            "Music;7;6;1;7;1;7;7;6;5",
            "Evolution;2;1;1;7;7;1;5;7;2")

    @Mock
    private lateinit var csvReader: FileReader
    private lateinit var repository: GenreAudienceRepository

    @Before
    fun setUp() {
        repository = GenreAudienceRepository(csvReader)
    }

    @Test
    fun shouldLoad3Topics_When_GetTopicsInvoked() {
        `when`(csvReader.readFile()).thenReturn(correctCsv)
        val topics = repository.readItems()

        assertEquals(3, topics.size)
    }

    @Test
    fun shouldLoadCorrectTopics_When_GetTopicsInvoked() {
        `when`(csvReader.readFile()).thenReturn(correctCsv)
        val topics = repository.readItems()

        assertEquals(AIRPLANE_TOPIC, topics[0])
        assertEquals(MUSIC_TOPIC, topics[1])
        assertEquals(EVOLUTION_TOPIC, topics[2])
    }

    @Test
    fun shouldThrowExceptionWhen_NameIsNotPresent() {
        `when`(csvReader.readFile()).thenReturn(mutableListOf(";7;1;4;7;7;7;7;7;6"))
        var exception = Exception()
        try {
            repository.readItems()
        } catch (e: ReadingException) {
            exception = e
        }

        assertEquals("A line of the csv file does not contain a name", exception.message)
    }

    @Test
    fun shouldThrowExceptionWhen_ThereAreNot10Columns() {
        `when`(csvReader.readFile()).thenReturn(mutableListOf("Music;7;1;4;7;7;7;7;7"))
        var exception = Exception()
        try {
            repository.readItems()
        } catch (e: ReadingException) {
            exception = e
        }

        assertEquals("A line of the csv file does not contain 10 columns", exception.message)
    }
}
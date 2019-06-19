package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.exceptions.ReadingException
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Exception

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

    private lateinit var repository: GenreAudienceRepository

    private val correctCSV = javaClass.classLoader.getResourceAsStream("genre-test.csv")
    private val wrongNameCSV = javaClass.classLoader.getResourceAsStream("genre-test-name.csv")
    private val wrongCategoriesCSV = javaClass.classLoader.getResourceAsStream("genre-test-categories.csv")

    @Before
    fun setUp() {
        repository = GenreAudienceRepository(correctCSV)
    }

    @Test
    fun shouldLoad3Topics_When_GetTopicsInvoked() {
        val topics = repository.getTopics()

        assertEquals(3,topics.size)
    }

    @Test
    fun shouldLoadCorrectTopics_When_GetTopicsInvoked() {
        val topics = repository.getTopics()

        assertEquals(AIRPLANE_TOPIC,topics[0])
        assertEquals(MUSIC_TOPIC, topics[1])
        assertEquals(EVOLUTION_TOPIC, topics[2])
    }

    @Test
    fun shouldThrowExceptionWhen_NameIsNotPresent() {
        repository = GenreAudienceRepository(wrongNameCSV)

        var exception = Exception()
        try {
            repository.getTopics()
        } catch (e:ReadingException) {
            exception = e
        }

        assertEquals("A line of the csv file does not contain a name", exception.message)
    }

    @Test
    fun shouldThrowExceptionWhen_ThereAreNot10Columns() {
        repository = GenreAudienceRepository(wrongCategoriesCSV)

        var exception = Exception()
        try {
            repository.getTopics()
        } catch (e:ReadingException) {
            exception = e
        }

        assertEquals("A line of the csv file does not contain 10 columns", exception.message)
    }
}
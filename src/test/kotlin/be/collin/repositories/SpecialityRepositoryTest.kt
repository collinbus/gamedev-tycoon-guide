package be.collin.repositories

import be.collin.domain.Genre
import be.collin.domain.Rating
import be.collin.io.FileReader
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SpecialityRepositoryTest {

    companion object {
        val actionGenre = Genre("ACTION",
                Rating.BEST,Rating.BETTER,Rating.WORSE,
                Rating.WORST,Rating.BETTER, Rating.BEST,
                Rating.DONT_CARE,Rating.BEST,Rating.BETTER)
    }

    @Mock
    private lateinit var csvReader: FileReader

    @Test
    fun shouldLoadCorrectRatings_When_readItemsInvoked() {
        `when`(csvReader.readFile()).thenReturn(mutableListOf("ACTION;7;6;2;1;6;7;4;7;6"))
        val repository = SpecialityRepository(csvReader)

        val items = repository.readItems()
        assertEquals(items[0], actionGenre)
    }
}
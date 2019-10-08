package be.collin.services

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.repositories.RatingRepository
import junit.framework.Assert.assertEquals
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(JUnitParamsRunner::class)
class GenreAudienceServiceTest {

    @Mock
    private lateinit var repository: RatingRepository<GenreAudienceItem>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldReturnAllTopicsWhen_GetAllTopicsCalled() {
        val mockedItems = listOf(
                GenreAudienceItem("Topic1", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Topic2", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Topic3", GenreRatings(), AudienceRatings())
        )

        `when`(repository.readItems()).thenReturn(mockedItems)
        val service = GenreAudienceService(repository)

        val items = service.getAllItems()

        assertEquals("Topic1", items[0].name)
        assertEquals("Topic2", items[1].name)
        assertEquals("Topic3", items[2].name)
    }

    @Test
    @Parameters("ai", "Ai", "AI", "aI", "air", "ir")
    fun shouldReturnAirplaneAndAirforce_When_GetItemsContainingWithValidFilter(filter: String) {
        val mockedItems = listOf(
                GenreAudienceItem("Airplane", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Medical", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Airforce", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Zombies", GenreRatings(), AudienceRatings())
        )

        `when`(repository.readItems()).thenReturn(mockedItems)
        val service = GenreAudienceService(repository)

        val items = service.getAllItemsContaining(filter)

        assertEquals("Airplane", items[0].name)
        assertEquals("Airforce", items[1].name)
        assertEquals(2, items.size)
    }
}
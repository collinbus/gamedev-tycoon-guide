package be.collin.services

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreAudienceItem
import be.collin.domain.GenreRatings
import be.collin.repositories.RatingRepository
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopicServiceTest {

    @Mock
    private lateinit var repository: RatingRepository<GenreAudienceItem>

    @Test
    fun shouldReturnAllTopicsWhen_GetAllTopicsCalled() {
        val mockedTopics = listOf(
                GenreAudienceItem("Topic1", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Topic2", GenreRatings(), AudienceRatings()),
                GenreAudienceItem("Topic3", GenreRatings(), AudienceRatings())
        )

        `when`(repository.readItems()).thenReturn(mockedTopics)
        val service = TopicService(repository)

        val topics = service.getAllTopics()

        assertEquals("Topic1", topics[0].name)
        assertEquals("Topic2", topics[1].name)
        assertEquals("Topic3", topics[2].name)
    }
}
package be.collin.repositories

import org.junit.Assert.assertEquals
import org.junit.Test

class TopicRepositoryTest {

    @Test
    fun shouldLoadTopics_When_GetTopicsInvoked() {
        val repository = TopicRepository()

        val topics = repository.getTopics()

        assertEquals(3,topics.size)
    }
}
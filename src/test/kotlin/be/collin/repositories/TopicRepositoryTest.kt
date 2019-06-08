package be.collin.repositories

import be.collin.domain.AudienceRatings
import be.collin.domain.GenreRatings
import be.collin.domain.Rating
import be.collin.domain.Topic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TopicRepositoryTest {

    companion object {
        val AIRPLANE_TOPIC = Topic("Airplane",
                genreRatings = GenreRatings(Rating.BEST, Rating.WORST,Rating.DONT_CARE,
                        Rating.BEST, Rating.BEST, Rating.BEST),
                audienceRatings = AudienceRatings(Rating.BEST, Rating.BEST, Rating.BETTER))
        val MUSIC_TOPIC = Topic("Music",
                genreRatings = GenreRatings(Rating.BEST, Rating.BETTER, Rating.WORST,
                        Rating.BEST, Rating.WORST, Rating.BEST),
                audienceRatings = AudienceRatings(Rating.BEST, Rating.BETTER, Rating.GOOD))
        val EVOLUTION_TOPIC = Topic("Evolution",
                genreRatings = GenreRatings(Rating.WORSE, Rating.WORST, Rating.WORST,
                        Rating.BEST, Rating.BEST, Rating.WORST),
                audienceRatings = AudienceRatings(Rating.GOOD, Rating.BEST, Rating.WORSE))
    }

    private lateinit var topicRepository: TopicRepository

    @Before
    fun setUp() {
        topicRepository = TopicRepository()
    }

    @Test
    fun shouldLoad3Topics_When_GetTopicsInvoked() {
        val repository = topicRepository

        val topics = repository.getTopics()

        assertEquals(3,topics.size)
    }

    @Test
    fun shouldLoadCorrectTopics_When_GetTopicsInvoked() {
        val repository = topicRepository

        val topics = repository.getTopics()

        assertEquals(AIRPLANE_TOPIC,topics[0])
        assertEquals(MUSIC_TOPIC, topics[1])
        assertEquals(EVOLUTION_TOPIC, topics[2])
    }
}
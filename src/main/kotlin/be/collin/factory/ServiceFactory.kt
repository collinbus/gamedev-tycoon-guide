package be.collin.factory

import be.collin.domain.GenreAudienceType
import be.collin.io.CsvReader
import be.collin.repositories.GenreAudienceRepository
import be.collin.services.GenreAudienceService

class ServiceFactory {

    companion object {
        private const val TOPICS = "be/collin/csv/Topics.csv"
        private const val SYSTEMS = "be/collin/csv/Systems.csv"
        private val SOURCE = mapOf(
                Pair(GenreAudienceType.TOPIC,TOPICS),
                Pair(GenreAudienceType.SYSTEM,SYSTEMS)
        )
    }

    fun newGenreAudienceService(genreAudienceType: GenreAudienceType): GenreAudienceService {
        val topicInput = javaClass.classLoader.getResourceAsStream(SOURCE[genreAudienceType])!!
        return GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
    }

}

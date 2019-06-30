package be.collin.services

import be.collin.domain.GenreAudienceItem
import be.collin.repositories.RatingRepository

class GenreAudienceService(private val repository: RatingRepository<GenreAudienceItem>) {

    fun getAllTopics() : List<GenreAudienceItem> {
        return repository.readItems()
    }

}
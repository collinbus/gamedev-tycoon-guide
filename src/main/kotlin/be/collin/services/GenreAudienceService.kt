package be.collin.services

import be.collin.domain.GenreAudienceItem
import be.collin.repositories.RatingRepository

class GenreAudienceService(private val repository: RatingRepository<GenreAudienceItem>) {

    private val items: List<GenreAudienceItem> = repository.readItems()

    fun getAllItems() : List<GenreAudienceItem> {
        return items
    }

    fun getAllItemsContaining(nameContent: String): List<GenreAudienceItem> {
        return items.filter { item -> item.name.toLowerCase().contains(nameContent.toLowerCase()) }
    }

}
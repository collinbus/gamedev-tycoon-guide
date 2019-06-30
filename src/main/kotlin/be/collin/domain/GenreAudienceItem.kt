package be.collin.domain

data class GenreAudienceItem(val name: String,
                             val genreRatings: GenreRatings,
                             val audienceRatings: AudienceRatings) {

    override fun toString(): String {
        return name
    }
}
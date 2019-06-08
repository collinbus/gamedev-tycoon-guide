package be.collin.domain

data class Topic(var name:String = "",
                 var genreRatings: GenreRatings = GenreRatings(),
                 var audienceRatings: AudienceRatings = AudienceRatings())

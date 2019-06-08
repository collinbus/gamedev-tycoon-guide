package be.collin.domain

data class AudienceRatings(var young: Rating = Rating.DONT_CARE,
                           var everyone: Rating = Rating.DONT_CARE,
                           var mature: Rating = Rating.DONT_CARE)
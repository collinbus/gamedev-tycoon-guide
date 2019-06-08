package be.collin.domain

data class GenreRatings(var action: Rating = Rating.DONT_CARE,
                        var adventure: Rating = Rating.DONT_CARE,
                        var rpg: Rating = Rating.DONT_CARE,
                        var simulation: Rating = Rating.DONT_CARE,
                        var strategy: Rating = Rating.DONT_CARE,
                        var casual: Rating = Rating.DONT_CARE)

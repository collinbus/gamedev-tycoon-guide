package be.collin.domain

enum class Rating(val score: Int) {
    WORST(1),
    WORSE(2),
    BAD(3),
    DONT_CARE(4),
    GOOD(5),
    BETTER(6),
    BEST(7)
}
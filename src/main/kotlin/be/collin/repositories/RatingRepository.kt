package be.collin.repositories

interface RatingRepository<T> {
    fun readItems(): List<T>
}

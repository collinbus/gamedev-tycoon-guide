package be.collin.repositories

import be.collin.domain.Topic
import java.util.*

class TopicRepository {
    fun getTopics(): List<Topic> {
        val topics = mutableListOf<Topic>()
        val scanner = Scanner(javaClass.classLoader.getResourceAsStream("genre-test.csv"))
        scanner.nextLine()
        while (scanner.hasNext()) {
            val line = scanner.nextLine()
            topics.add(readTopic(line))
        }
        return topics
    }

    private fun readTopic(line: String): Topic {
        return Topic()
    }
}
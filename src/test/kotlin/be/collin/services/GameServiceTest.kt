package be.collin.services

import be.collin.domain.Audience
import be.collin.domain.Game
import be.collin.domain.Genre
import be.collin.pcAndG64
import be.collin.tenTopics
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameServiceTest {

    @Mock
    private lateinit var calculator: ScoreCalculator

    companion object {
        const val TARGET_AUDIENCE = true
        const val CASUAL_UNLOCKED = true
    }

    @Test
    fun `Should return list of 5 games when map contains multiple keys and 7 values`() {
        val service = GameService(calculator)
        `when`(calculator.calculateScores(anyList(), anyList(), anyBoolean(), anyBoolean())).thenReturn(games())

        val games = service.getTop5Games(listOf(), listOf(), CASUAL_UNLOCKED, TARGET_AUDIENCE)

        assertEquals(5, games.size)
    }

    @Test
    fun `Should return empty list when no items are selected`() {
        val service = GameService(calculator)
        `when`(calculator.calculateScores(anyList(), anyList(), anyBoolean(), anyBoolean())).thenReturn(mapOf())

        val games = service.getTop5Games(listOf(), listOf(), CASUAL_UNLOCKED, TARGET_AUDIENCE)

        assertEquals(0, games.size)
    }

    @Test
    fun `Should return list with 7 games when getAllItems is called`() {
        val service = GameService(calculator)
        `when`(calculator.calculateScores(anyList(), anyList(), anyBoolean(), anyBoolean())).thenReturn(games())

        val games = service.getAllGames(listOf(), listOf(), CASUAL_UNLOCKED, TARGET_AUDIENCE)

        assertEquals(7, games.size)
    }

    private fun games(): Map<Int, List<Game>> {
        val topics = tenTopics()
        val systems = pcAndG64()
        return sortedMapOf(
                Pair(5, listOf(
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 5),
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 5),
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 5),
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 5),
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 5)
                        )),
                Pair(4, listOf(
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 4),
                        Game(topics[0], systems[0], Genre.RPG, Audience.EVERYONE, 4)
                ))
        )
    }
}
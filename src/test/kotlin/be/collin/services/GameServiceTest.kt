package be.collin.services

import be.collin.domain.Game
import be.collin.domain.Genre
import be.collin.pcAndG64
import be.collin.tenTopics
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyList
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameServiceTest {

    @Mock
    private lateinit var calculator: ScoreCalculator

    @Test
    fun `Should return list of 5 games when map contains multiple keys and 7 values`() {
        val service = GameService(calculator)
        `when`(calculator.calculateScores(anyList(), anyList(), true, true)).thenReturn(games())

        val games = service.getTop5Games(listOf(), listOf(), true, true)

        assertEquals(5, games.size)
    }

    @Test
    fun `Should return empty list when no items are selected`() {
        val service = GameService(calculator)
        `when`(calculator.calculateScores(anyList(), anyList(), true, true)).thenReturn(mapOf())

        val games = service.getTop5Games(listOf(), listOf(), true, true)

        assertEquals(0, games.size)
    }

    private fun games(): Map<Int, List<Game>> {
        val topics = tenTopics()
        val systems = pcAndG64()
        return sortedMapOf(
                Pair(5, listOf(
                        Game(topics[0],systems[0],Genre.RPG,5),
                        Game(topics[0],systems[0],Genre.RPG,5),
                        Game(topics[0],systems[0],Genre.RPG,5),
                        Game(topics[0],systems[0],Genre.RPG,5),
                        Game(topics[0],systems[0],Genre.RPG,5)
                        )),
                Pair(4, listOf(
                        Game(topics[0],systems[0],Genre.RPG,4),
                        Game(topics[0],systems[0],Genre.RPG,4)
                ))
        )
    }
}
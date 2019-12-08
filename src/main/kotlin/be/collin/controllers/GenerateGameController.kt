package be.collin.controllers

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem
import be.collin.services.GameService
import be.collin.services.SimpleScoreCalculator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView

class GenerateGameController(private val systems: List<GenreAudienceItem>,
                             private val topics: List<GenreAudienceItem>) {

    @FXML
    lateinit var games: ListView<Game>

    private val gameService = GameService(SimpleScoreCalculator())

    fun generateGame() {
        val top5Games = gameService.getTop5Games(topics, systems)
        games.items = FXCollections.observableArrayList(top5Games)
    }
}
package be.collin.controllers

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem
import be.collin.services.GameService
import be.collin.services.SimpleScoreCalculator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.CheckBox
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.stage.Stage
import java.net.URL
import java.util.*

class GenerateGameController(private val systems: List<GenreAudienceItem>,
                             private val topics: List<GenreAudienceItem>,
                             private val gameGenerationCallback: MainController.GameGenerationCallback)
    :Initializable {

    @FXML
    lateinit var games: ListView<Game>
    @FXML
    lateinit var casualUnlocked: CheckBox
    @FXML
    lateinit var targetAudience: CheckBox

    private val gameService = GameService(SimpleScoreCalculator())

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        games.selectionModel.selectionMode = SelectionMode.SINGLE
    }

    fun generateGame() {
        val allGames = gameService.getAllGames(topics, systems, casualUnlocked.isSelected, targetAudience.isSelected)
        games.items = FXCollections.observableArrayList(allGames)
    }

    fun exitGameGeneration() {
        val selectedGame = games.selectionModel.selectedItem
        if (selectedGame != null)
            gameGenerationCallback.gameGenerated(selectedGame)

        val stage = games.scene.window as Stage
        stage.close()
    }
}
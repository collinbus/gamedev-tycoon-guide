package be.collin.controllers

import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem
import be.collin.views.NodeFactory
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.util.Callback
import java.net.URL
import java.util.*

class MainController(private val nodeFactory: NodeFactory) : Initializable {

    @FXML
    lateinit var topics:ListView<GenreAudienceItem>
    @FXML
    lateinit var systems:ListView<GenreAudienceItem>
    @FXML
    lateinit var history:ListView<Game>

    private val addTopicsStage: Stage = Stage()
    private val addSystemsStage: Stage = Stage()
    private val generateGameStage: Stage = Stage()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initAddItemsStage(topics, addTopicsStage, "be/collin/csv/Topics.csv")
        initAddItemsStage(systems, addSystemsStage, "be/collin/csv/Systems.csv")
        generateGameStage.initModality(Modality.APPLICATION_MODAL)
    }

    private fun initAddItemsStage(items: ListView<GenreAudienceItem>, stage: Stage, source: String) {
        val screen = nodeFactory.newAddItemsStage(items, source)
        stage.scene = Scene(screen)
        stage.initModality(Modality.APPLICATION_MODAL)
    }

    fun openAddTopicsScreen() {
        addTopicsStage.showAndWait()
    }

    fun openAddSystemsScreen() {
        addSystemsStage.showAndWait()
    }
    fun openGenerateGameScreen() {
        val loader = FXMLLoader(javaClass.classLoader.getResource("be/collin/views/generate_game.fxml"))
        loader.controllerFactory = Callback {
            GenerateGameController(systems.items, topics.items, GameGenerationCallback(history))
        }
        val screen = loader.load<Parent>()
        generateGameStage.scene = Scene(screen)
        generateGameStage.showAndWait()
    }

    interface GenreAudienceCallback {
        fun onDataSelected(items: List<GenreAudienceItem>)
    }

    class SelectedItemCallback(private val items: ListView<GenreAudienceItem>) : GenreAudienceCallback {
        override fun onDataSelected(items: List<GenreAudienceItem>) {
            this.items.items = FXCollections.observableList(items)
        }
    }

    class GameGenerationCallback(private val items: ListView<Game>) {
        fun gameGenerated(game: Game) {
            items.items.add(game)
        }
    }
}
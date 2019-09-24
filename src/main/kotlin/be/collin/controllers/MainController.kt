package be.collin.controllers

import be.collin.domain.GenreAudienceItem
import be.collin.io.CsvReader
import be.collin.repositories.GenreAudienceRepository
import be.collin.services.GenreAudienceService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ListView
import javafx.stage.Modality
import javafx.stage.Stage
import java.net.URL
import java.util.*

class MainController: Initializable {

    @FXML
    lateinit var topics:ListView<GenreAudienceItem>
    @FXML
    lateinit var systems:ListView<GenreAudienceItem>

    private val addTopicsStage: Stage = Stage()

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initializeLists()
        initTopicsStage()
    }

    private fun initTopicsStage() {
        val loader = FXMLLoader(javaClass.classLoader.getResource("be/collin/views/add_topics.fxml"))
        val screen = loader.load<Parent>()
        loader.getController<AddTopicsController>().init(TopicDataSelectedCallback(topics))
        addTopicsStage.scene = Scene(screen)
        addTopicsStage.initModality(Modality.APPLICATION_MODAL)
    }

    private fun initializeLists() {
        val systemInput = javaClass.classLoader.getResourceAsStream("be/collin/csv/Systems.csv")!!
        val systemService = GenreAudienceService(GenreAudienceRepository(CsvReader(systemInput)))
        systems.items = FXCollections.observableList(systemService.getAllTopics())
    }

    fun openAddTopicsScreen() {
        addTopicsStage.showAndWait()
    }

    interface GenreAudienceCallback {
        fun onDataSelected(items: List<GenreAudienceItem>)
    }

    class TopicDataSelectedCallback(private val topics: ListView<GenreAudienceItem>) : GenreAudienceCallback {
        override fun onDataSelected(items: List<GenreAudienceItem>) {
            topics.items = FXCollections.observableList(items)
        }
    }
}
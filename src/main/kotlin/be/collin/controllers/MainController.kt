package be.collin.controllers

import be.collin.domain.GenreAudienceItem
import be.collin.io.CsvReader
import be.collin.repositories.GenreAudienceRepository
import be.collin.services.GenreAudienceService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import java.net.URL
import java.util.*

class MainController: Initializable {

    @FXML
    lateinit var topics:ListView<GenreAudienceItem>
    @FXML
    lateinit var systems:ListView<GenreAudienceItem>

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        val topicInput = javaClass.classLoader.getResourceAsStream("csv/Topics.csv")
        val topicService = GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
        topics.items = FXCollections.observableList(topicService.getAllTopics())

        val systemInput = javaClass.classLoader.getResourceAsStream("csv/Systems.csv")
        val systemService = GenreAudienceService(GenreAudienceRepository(CsvReader(systemInput)))
        systems.items = FXCollections.observableList(systemService.getAllTopics())
    }
}
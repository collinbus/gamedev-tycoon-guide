package be.collin.controllers

import be.collin.controllers.MainController.TopicDataSelectedCallback
import be.collin.domain.GenreAudienceItem
import be.collin.io.CsvReader
import be.collin.repositories.GenreAudienceRepository
import be.collin.services.GenreAudienceService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.stage.Stage

import java.net.URL
import java.util.ResourceBundle

class AddTopicsController : Initializable {

    @FXML
    private lateinit var topics: ListView<GenreAudienceItem>

    private lateinit var dataSelectedCallback: TopicDataSelectedCallback

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initTopics()
    }

    private fun initTopics() {
        val topicInput = javaClass.classLoader.getResourceAsStream("be/collin/csv/Topics.csv")!!
        val topicService = GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
        topics.items = FXCollections.observableList(topicService.getAllTopics())
        topics.selectionModel.selectionMode = SelectionMode.MULTIPLE
    }

    fun addTopicsAndCloseStage() {
        dataSelectedCallback.onDataSelected(topics.selectionModel.selectedItems)
        val currentStage = (topics.scene.window as Stage)
        currentStage.close()
    }

    fun init(topicDataSelectedCallback: TopicDataSelectedCallback) {
        this.dataSelectedCallback = topicDataSelectedCallback
    }
}

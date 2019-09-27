package be.collin.controllers

import be.collin.controllers.MainController.GenreAudienceCallback
import be.collin.controllers.MainController.SelectedItemCallback
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
    private lateinit var items: ListView<GenreAudienceItem>

    private lateinit var dataSelectedItemCallback: GenreAudienceCallback

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        initTopics()
    }

    private fun initTopics() {
        val topicInput = javaClass.classLoader.getResourceAsStream("be/collin/csv/Topics.csv")!!
        val topicService = GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
        items.items = FXCollections.observableList(topicService.getAllTopics())
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
    }

    fun addItemsAndCloseStage() {
        dataSelectedItemCallback.onDataSelected(items.selectionModel.selectedItems)
        val currentStage = (items.scene.window as Stage)
        currentStage.close()
    }

    fun init(selectedItemItemCallback: GenreAudienceCallback) {
        this.dataSelectedItemCallback = selectedItemItemCallback
    }
}

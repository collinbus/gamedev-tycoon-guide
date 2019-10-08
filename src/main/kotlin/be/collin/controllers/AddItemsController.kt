package be.collin.controllers

import be.collin.controllers.MainController.GenreAudienceCallback
import be.collin.domain.GenreAudienceItem
import be.collin.io.CsvReader
import be.collin.repositories.GenreAudienceRepository
import be.collin.services.GenreAudienceService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.stage.Stage

class AddItemsController {

    @FXML
    private lateinit var items: ListView<GenreAudienceItem>

    private lateinit var dataSelectedItemCallback: GenreAudienceCallback

    private fun initItemList(source: String) {
        val topicInput = javaClass.classLoader.getResourceAsStream(source)!!
        val itemsService = GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
        items.items = FXCollections.observableList(itemsService.getAllTopics())
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
    }

    fun addItemsAndCloseStage() {
        dataSelectedItemCallback.onDataSelected(items.selectionModel.selectedItems)
        val currentStage = (items.scene.window as Stage)
        currentStage.close()
    }

    fun init(selectedItemItemCallback: GenreAudienceCallback, source: String) {
        initItemList(source)
        this.dataSelectedItemCallback = selectedItemItemCallback
    }
}

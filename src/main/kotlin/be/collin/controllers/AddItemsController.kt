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
import javafx.scene.control.TextField
import javafx.stage.Stage

class AddItemsController {

    @FXML
    private lateinit var items: ListView<GenreAudienceItem>
    @FXML
    private lateinit var filter: TextField

    private lateinit var dataSelectedItemCallback: GenreAudienceCallback
    private lateinit var itemsService: GenreAudienceService

    private fun initItemList(source: String) {
        val topicInput = javaClass.classLoader.getResourceAsStream(source)!!
        itemsService = GenreAudienceService(GenreAudienceRepository(CsvReader(topicInput)))
        populateItems(itemsService.getAllItems())
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
    }

    private fun populateItems(items: List<GenreAudienceItem>) {
        this.items.items = FXCollections.observableList(items)
    }

    fun addItemsAndCloseStage() {
        dataSelectedItemCallback.onDataSelected(items.selectionModel.selectedItems)
        val currentStage = (items.scene.window as Stage)
        currentStage.close()
    }

    fun init(selectedItemItemCallback: GenreAudienceCallback, source: String) {
        initItemList(source)
        this.dataSelectedItemCallback = selectedItemItemCallback
        filter.textProperty().addListener { _, _, newValue ->
            if (newValue == "")
                populateItems(itemsService.getAllItems())
            else
                populateItems(itemsService.getAllItemsContaining(newValue))
        }
    }
}

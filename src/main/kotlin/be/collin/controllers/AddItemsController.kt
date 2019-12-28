package be.collin.controllers

import be.collin.controllers.MainController.*
import be.collin.domain.GenreAudienceItem
import be.collin.services.GenreAudienceService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.SelectionMode
import javafx.scene.control.TextField
import javafx.stage.Stage
import java.net.URL
import java.util.*

class AddItemsController(private val dataSelectedItemCallback: SelectedItemCallback,
                         private val itemsService: GenreAudienceService)
    : Initializable {

    @FXML
    private lateinit var items: ListView<GenreAudienceItem>
    @FXML
    private lateinit var filter: TextField

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        initItemList()
        setupFilter()
    }

    fun addItemsAndCloseStage() {
        dataSelectedItemCallback.onDataSelected(items.selectionModel.selectedItems)
        val currentStage = (items.scene.window as Stage)
        currentStage.close()
    }

    private fun setupFilter() {
        filter.textProperty().addListener { _, _, newValue ->
            if (newValue == "")
                populateItems(itemsService.getAllItems())
            else
                populateItems(itemsService.getAllItemsContaining(newValue))
        }
    }

    private fun initItemList() {
        populateItems(itemsService.getAllItems())
        items.selectionModel.selectionMode = SelectionMode.MULTIPLE
    }

    private fun populateItems(items: List<GenreAudienceItem>) {
        this.items.items = FXCollections.observableList(items)
    }
}

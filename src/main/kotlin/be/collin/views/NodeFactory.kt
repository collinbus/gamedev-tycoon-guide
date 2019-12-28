package be.collin.views

import be.collin.controllers.AddItemsController
import be.collin.controllers.MainController.SelectedItemCallback
import be.collin.domain.GenreAudienceItem
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.ListView
import javafx.util.Callback

class NodeFactory {

    companion object {
        private const val ADD_ITEMS_URL = "be/collin/views/add_items.fxml"
    }

    fun newAddItemsStage(items: ListView<GenreAudienceItem>, source: String): Parent? {
        val loader = FXMLLoader(javaClass.classLoader.getResource(ADD_ITEMS_URL))
        loader.controllerFactory = Callback {
            AddItemsController(SelectedItemCallback(items), source)
        }
        return loader.load<Parent>()
    }
}
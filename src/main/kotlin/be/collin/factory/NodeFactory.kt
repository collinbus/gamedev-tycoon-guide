package be.collin.factory

import be.collin.controllers.AddItemsController
import be.collin.controllers.GenerateGameController
import be.collin.controllers.MainController.GameGenerationCallback
import be.collin.controllers.MainController.SelectedItemCallback
import be.collin.domain.Game
import be.collin.domain.GenreAudienceItem
import be.collin.services.GenreAudienceService
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.ListView
import javafx.util.Callback

class NodeFactory {

    companion object {
        private const val ADD_ITEMS_URL = "be/collin/views/add_items.fxml"
    }

    fun newAddItemsStage(items: ListView<GenreAudienceItem>, itemsService: GenreAudienceService): Parent? {
        val loader = FXMLLoader(javaClass.classLoader.getResource(ADD_ITEMS_URL))
        loader.controllerFactory = Callback {
            AddItemsController(SelectedItemCallback(items), itemsService)
        }
        return loader.load<Parent>()
    }

    fun newGenerateGameStage(systems: List<GenreAudienceItem>, topics: List<GenreAudienceItem>, history: ListView<Game>): Parent? {
        val loader = FXMLLoader(javaClass.classLoader.getResource("be/collin/views/generate_game.fxml"))
        loader.controllerFactory = Callback {
            GenerateGameController(systems, topics, GameGenerationCallback(history))
        }
        return loader.load<Parent>()
    }
}
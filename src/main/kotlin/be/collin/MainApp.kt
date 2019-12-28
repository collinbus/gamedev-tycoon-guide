package be.collin

import be.collin.controllers.MainController
import be.collin.factory.ServiceFactory
import be.collin.views.NodeFactory
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import javafx.util.Callback

class MainApp: Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java)
        }

        private const val APP_TITLE = "Game Dev Tycoon GUIDE"
    }

    private val appIcon = Image(javaClass.getResourceAsStream("icons/icon.png"))
    private val mainSceneUrl = javaClass.classLoader.getResource("be/collin/views/main.fxml")

    override fun start(primaryStage: Stage) {
        primaryStage.width = 800.0
        primaryStage.height = 600.0
        primaryStage.icons.add(appIcon)
        primaryStage.title = APP_TITLE
        val root = loadRootNode()
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }

    private fun loadRootNode(): Parent? {
        val loader = FXMLLoader(mainSceneUrl)
        loader.controllerFactory = Callback {
            MainController(NodeFactory(), ServiceFactory())
        }
        return loader.load<Parent>()
    }
}

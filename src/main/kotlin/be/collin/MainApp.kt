package be.collin;

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp: Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(MainApp::class.java)
        }
    }

    override fun start(primaryStage: Stage) {
        primaryStage.width = 800.0
        primaryStage.height = 600.0
        val root = FXMLLoader.load<Parent>(javaClass.classLoader.getResource("be/collin/views/main.fxml"))
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}

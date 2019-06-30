package be.collin.controllers

import be.collin.domain.GenreAudienceItem
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

    }

}
package be.collin.controllers

import be.collin.domain.Game
import javafx.fxml.FXML
import javafx.scene.text.Text

class ProposalBoxController {

    @FXML
    lateinit var title: Text
    @FXML
    lateinit var topic: Text
    @FXML
    lateinit var genre: Text
    @FXML
    lateinit var system: Text
    @FXML
    lateinit var audience: Text

    fun updateGame(game: Game) {
        topic.text = "Topic: ${game.topic}"
        genre.text = "Genre: ${game.genre}"
        system.text = "System: ${game.system}"
        audience.text = "Audience: ${game.audience}"
    }
}

package be.collin.views

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.VBox
import javafx.scene.text.Text


class ProposalBox : VBox() {

    var titleText: String = ""; set(value) {
        field = value
        title.text = value
    }

    @FXML
    private lateinit var title: Text

    init {
        val fxmlLoader = FXMLLoader(javaClass.classLoader.getResource("be/collin/views/proposal_box.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }
}
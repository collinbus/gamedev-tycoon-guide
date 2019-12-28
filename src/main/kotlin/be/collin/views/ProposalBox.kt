package be.collin.views

import be.collin.controllers.ProposalBoxController
import javafx.fxml.FXMLLoader
import javafx.scene.layout.VBox


class ProposalBox : VBox() {

    private var controller: ProposalBoxController? = null

    var titleText: String = ""
        set(value) {
            controller?.title?.text = value
            field = value
        }

    init {
        val fxmlLoader = FXMLLoader(javaClass.classLoader.getResource("be/collin/views/proposal_box.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.load<Any>()
        controller = fxmlLoader.getController()
    }
}
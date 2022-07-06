package controller.sign;

import Util.Utill;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignController {
    public AnchorPane signInContex;

    public void initialize() throws IOException {
        Utill.loadUi(signInContex,"sign/SignIn.fxml");
    }
}

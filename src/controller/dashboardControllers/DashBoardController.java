package controller.dashboardControllers;

import Util.Utill;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {
    public AnchorPane WorkingContex;
    public AnchorPane dashBordContex;

    public void initialize() throws IOException {
        Utill.loadUi(WorkingContex,"dashboard/Manage.fxml");
    }

    public void closeMouseOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) dashBordContex.getScene().getWindow();
        stage.close();
    }

    public void logOutMouseClickOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage=(Stage) dashBordContex.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/sign/Sign.fxml"))));
        stage.centerOnScreen();
    }

    public void reservwComputerOnaction(ActionEvent actionEvent) throws IOException {
        Utill.loadUi(WorkingContex,"dashboard/Manage.fxml");
    }

    public void ManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Utill.loadUi(WorkingContex,"dashboard/Details.fxml");
    }

    public void ManagePackageOnAction(ActionEvent actionEvent) {
    }

    public void ManageItemOnAction(ActionEvent actionEvent) {
    }

    public void ManageSuplyOnAction(ActionEvent actionEvent) {
    }

    public void ManageRepairOnAction(ActionEvent actionEvent) {

    }
}

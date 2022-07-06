package controller.sign;

import Util.CrudUtil;
import Util.Utill;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInController {
    public AnchorPane signInContex;
    public JFXTextField txtPassword;
    public JFXTextField txtusername;
    public JFXPasswordField txtpasswordfield;
    public ImageView vision;
    public ImageView visioff;
    public Label lblDateTime;

    public void initialize() throws IOException {

        visioff.setVisible(false);
        txtPassword.setVisible(false);

    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        Utill.loadUi(signInContex,"sign/SignUp.fxml");
    }

    public void entermouseOnaction(MouseEvent mouseEvent) {
        visioff.setVisible(true);
        txtpasswordfield.setVisible(false);
        txtPassword.setVisible(true);
        txtPassword.setText(txtpasswordfield.getText());
    }

    public void exitemouseonaction(MouseEvent mouseEvent) {
        visioff.setVisible(false);
        txtpasswordfield.setVisible(true);
        txtPassword.setVisible(false);
    }

    public void SignInOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
     /*   Stage stag=(Stage) signInContex.getScene().getWindow();
        stag.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/dashboard/DashBoard.fxml"))));
        stag.centerOnScreen();*/


        if (txtusername.getText().matches("^[A-Z][a-z]*$")){
            if (txtpasswordfield.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,12}$")){

                String username = txtusername.getText();

                String password = "  ";

                ResultSet user = CrudUtil.execute("SELECT * FROM admin WHERE name like'"+username+"%'");

                while (user.next()){

                    password = user.getString(5);
                }

                if (txtpasswordfield.getText().equals(password)){

                    Stage stage=(Stage) signInContex.getScene().getWindow();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/dashboard/DashBoard.fxml"))));
                    stage.centerOnScreen();

                    Utill.notifycateconfrm(username+"   Sign In Successful","SIGN IN");
                }else {
                    Utill.notifycate("********** Password Is Not Match","ERROR");
                }


            }else {
                if (txtpasswordfield.getText().equals("")){

                    Utill.notifycate("First Fill Password ","ERROR");
                }else {
                    Utill.notifycate("************   Not Valid Password","ERROR");
                }
            }
        }else {
            if (txtusername.getText().equals("")){

                Utill.notifycate("First Fill User Name ","ERROR");
            }else {
                Utill.notifycate(txtusername.getText()+"   Not Valid Password Make Strong one","ERROR");
            }
        }
    }
}

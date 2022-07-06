package controller.sign;

import Util.CrudUtil;
import Util.Utill;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpController {
    public AnchorPane signUpContex;
    public JFXTextField txtFullName;
    public JFXTextField txtEmail;
    public JFXTextField txtContact;
    public JFXTextField txtPassword;
    public JFXPasswordField txtpasswordfield;
    public ImageView vision;
    public ImageView visioff;

    public void initialize() throws IOException {

        visioff.setVisible(false);
        txtPassword.setVisible(false);

    }

    public void signInOnAction(ActionEvent actionEvent) throws IOException {
        Utill.loadUi(signUpContex,"sign/SignIn.fxml");
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

    public void SignUpOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        if (txtFullName.getText().matches("^[A-Z][a-z]*[ ][A-Z][a-z]*$")){
            if (txtEmail.getText().matches("^([A-z\\d.]{3,})@(gmail|yahoo|Outlook|Inbox|iCloud|Mail|AOL|Zoho)(.com|.co.uk)$")){
                if (txtContact.getText().matches("^(\\+|0)(94|[1-9]{2,3})(-| |)([0-9]{7}|[0-9]{2} [0-9]{7})$")){
                    if (txtpasswordfield.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,12}$")){

                        String id= Utill.iDIncrement("Admin","admin_id");
                        String name = txtFullName.getText();
                        String email = txtEmail.getText();
                        String contact = txtContact.getText();
                        String password = txtpasswordfield.getText();
                        if (CrudUtil.execute("INSERT INTO admin VALUES (?,?,?,?,?)",id,name,email,contact,password)){
                            Utill.loadUi(signUpContex,"sign/SignIn.fxml");
                            Utill.notifycateconfrm("SignUp Successful","SIGNUP");
                        }
                    }else {
                        if (txtpasswordfield.getText().equals("")){

                            Utill.notifycate("First Fill Password ","ERROR");
                        }else {
                            Utill.notifycate("************   Not Valid Password Make Strong one","ERROR");
                        }
                    }
                }else {
                    if (txtContact.getText().equals("")){

                        Utill.notifycate("First Fill Contact ","ERROR");
                    }else {
                        Utill.notifycate(txtContact.getText()+ "   Not Valid","ERROR");
                    }
                }




            }else {
                if (txtEmail.getText().equals("")){

                    Utill.notifycate("First Fill Email ","ERROR");
                }else {
                    Utill.notifycate(txtEmail.getText()+ "   Not Valid","ERROR");
                }
            }


        }else {
            if (txtFullName.getText().equals("")){

                Utill.notifycate("First Fill User Name ","ERROR");
            }else {
                Utill.notifycate(txtFullName.getText()+ "   Not Valid","ERROR");
            }
        }
    }
}

package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.microfinance.util.Navigation;
import lk.ijse.microfinance.util.Routers;

import java.io.IOException;

public class LoginFormController {

    private static int status;
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtUserNameSignUp;
    public TextField txtPasswordSignUp;
    public AnchorPane ancLoginForm;
    public JFXTextField txtUserName;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
       /* Stage stage=(Stage) ancLoginForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/microfinance/view/AdminBackground.fxml"))));
*/
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
         if(userName.contains("admin") && password.contains("1234")){
             Navigation.navigation(Routers.ADMINBACKGROUND, ancLoginForm);
             new Alert(Alert.AlertType.CONFIRMATION, "Login Successfully").show();
         }else if(userName.contains("manager") && password.contains("1234")){
             Navigation.navigation(Routers.MANAGERBACKGROUND, ancLoginForm);
             new Alert(Alert.AlertType.CONFIRMATION, "Login Successfully").show();
         }else{
             new Alert(Alert.AlertType.WARNING, "Incorrect username or password!").show();
         }
    }

    public void btnCreateAccountOpenForm(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) ancLoginForm.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/microfinance/view/CreateAccountAndLogin.fxml"))));
    }

    public void btnaAdminLoginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.ADMINBACKGROUND, ancLoginForm);
    }

    public void btnManagerLoginOnAction(ActionEvent actionEvent) throws IOException {
       Navigation.navigation(Routers.MANAGERBACKGROUND, ancLoginForm);
    }
}

package lk.ijse.microfinance.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.microfinance.util.Navigation;
import lk.ijse.microfinance.util.Routers;

import java.io.IOException;

public class CreateAccountAndLoginController {
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtUserNameSignUp;
    public TextField txtPasswordSignUp;
    public TextField txtConfirmPasswordSignUp;
    public AnchorPane ancCreateAccountAndLogin;

    public void btnCreateAccountOnAction(ActionEvent actionEvent) throws IOException {
        /*Stage stage=(Stage) ancCreateAccountAndLogin.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/microfinance/view/AdminBackground.fxml"))));
*/
        Navigation.navigation(Routers.ADMINBACKGROUND,ancCreateAccountAndLogin);
    }

    public void btnLoginNowOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.LOGIN,ancCreateAccountAndLogin);
    }
}

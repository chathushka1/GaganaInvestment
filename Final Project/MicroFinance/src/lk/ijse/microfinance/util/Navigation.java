package lk.ijse.microfinance.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane ancBackground;
    public static void navigation(Routers routers,AnchorPane ancBackground) throws IOException {
        Navigation.ancBackground=ancBackground;
        Navigation.ancBackground.getChildren().clear();
        Stage window=(Stage)Navigation.ancBackground.getScene().getWindow();

        switch (routers){
            case ADMINBACKGROUND:
                window.setTitle("Background");
                initUi("AdminBackground.fxml");
                break;

            case DEBTOR:
                window.setTitle("Debtor");
                initUi("DebtorForm.fxml");
                break;

            case EMPLOYEE:
                window.setTitle("Employee");
                initUi("EmployeeForm.fxml");
                break;

            case GUARANTOR:
                window.setTitle("Guarantor");
                initUi("GuarantorForm.fxml");
                break;

            case LOGIN:
                window.setTitle("Login");
                initUi("LoginForm.fxml");
                break;

            case PAYMENT:
                window.setTitle("Payment");
                initUi("Payment.fxml");
                break;

            case REPORT:
                window.setTitle("Report");
                initUi("Report.fxml");
                break;

            case MANAGERBACKGROUND:
                window.setTitle("ManagerBackground");
                initUi("ManagerBackgrond.fxml");
                break;

            case LOAN:
                window.setTitle("Loan");
                initUi("Loan.fxml");
                break;

            case ABOUT:
                window.setTitle("About");
                initUi("About.fxml");
                break;

            case GUARANTEEITEM:
                window.setTitle("GuaranteeItem");
                initUi("GuaranteeItem.fxml");
                break;


        }
    }
    private static void initUi(String location) throws IOException {
        Navigation.ancBackground.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/microfinance/view/"+location)));
    }
}

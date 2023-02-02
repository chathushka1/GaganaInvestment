package lk.ijse.microfinance.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.util.Navigation;
import lk.ijse.microfinance.util.Routers;

import java.io.IOException;

public class AdminBackgroundController {
    private static int status ;
    public AnchorPane ancMainAnchorPane;
    public Label lblDescribeWho;
    public AnchorPane ancAdminBackground;

    public void btnDebtorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.DEBTOR,ancMainAnchorPane);
    }

    public void btnLoanOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.LOAN,ancMainAnchorPane);
    }

    public void btnGuarantorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.GUARANTOR,ancMainAnchorPane);
    }

    public void btnGuaranteeItemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.GUARANTEEITEM,ancMainAnchorPane);
    }

    public void btnPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.PAYMENT,ancMainAnchorPane);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.EMPLOYEE,ancMainAnchorPane);
    }

    public void btnReportOnActiom(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.REPORT,ancMainAnchorPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.LOGIN,ancAdminBackground);
    }

    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.ABOUT,ancMainAnchorPane);
    }
}



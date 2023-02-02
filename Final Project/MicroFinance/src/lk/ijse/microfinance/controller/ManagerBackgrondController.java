package lk.ijse.microfinance.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.util.Navigation;
import lk.ijse.microfinance.util.Routers;

import java.io.IOException;

public class ManagerBackgrondController {
    public AnchorPane ancManagerBackground;
    public AnchorPane ancManagerMainAnchorPane;
    public Label lblDescribeWho;

    public void btnDebtorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.DEBTOR,ancManagerMainAnchorPane);
    }

    public void btnLoanOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.LOAN,ancManagerMainAnchorPane);
    }

    public void btnGuarantorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.GUARANTOR,ancManagerMainAnchorPane);
    }

    public void btnGuaranteeItemOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.GUARANTEEITEM,ancManagerMainAnchorPane);
    }

    public void btnPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.PAYMENT,ancManagerMainAnchorPane);
    }

    public void btnReportOnActiom(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.REPORT,ancManagerMainAnchorPane);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.LOGIN,ancManagerBackground);
    }

    public void btnAbout(ActionEvent actionEvent) throws IOException {
        Navigation.navigation(Routers.ABOUT,ancManagerMainAnchorPane);
    }
}

package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.custom.LoanBO;
import lk.ijse.microfinance.dto.LoanDTO;
import lk.ijse.microfinance.view.tm.LoanAddTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoanController {
    public AnchorPane ancLoan;
    public JFXTextField txtAmount;
    public JFXTextField txtPeriod;
    public Label lblMonthlyPremium;
    public JFXTextField txtDueDate;
    public JFXTextField txtDebtorID;
    public JFXTextField txtDate;
    public JFXTextField txtLoanID;
    public TableView<LoanAddTm> tblLoan;
    public TableColumn collLoanID;
    public TableColumn collDebtorID;
    public TableColumn collAmount;
    public TableColumn collPeriod;
    public TableColumn collDate;
    public TableColumn collDeuDate;
    public TableColumn collPercentage;
    public TableColumn collMonthlyPremium;
    public JFXTextField txtPercentage;
    public JFXTextField txtMonthlyPremium;
    public JFXTextField txtSearch;
    public Label lblLoanId;
    public Label lblAmount;
    public Label lblPeriod;
    public Label lblDebtorId;
    public JFXTextField txtLoanAmount;
    public JFXButton btnSaveId;
    public JFXButton btnUpdateId;
    public JFXButton btnNewLoanId;
    private String searchText = "";
    LoanBO loanBO = (LoanBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOANBO);


    public void initialize() {
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collDebtorID.setCellValueFactory(new PropertyValueFactory<>("idDebtor"));
        collAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        collDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        collDeuDate.setCellValueFactory(new PropertyValueFactory<>("loanDueDate"));
        collPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        collPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        collMonthlyPremium.setCellValueFactory(new PropertyValueFactory<>("monthlyPremium"));


        tblLoan.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnUpdateId.setDisable(newValue == null);
            btnSaveId.setText(newValue != null ? "update" : "save");


            if (newValue != null) {
                txtLoanID.setText(newValue.getId());
                txtDebtorID.setText(newValue.getIdDebtor());
                txtAmount.setText(String.valueOf(newValue.getAmount()));
                txtDate.setText(newValue.getLoanDate());
                txtDueDate.setText(newValue.getLoanDueDate());
                txtPeriod.setText(String.valueOf(newValue.getPeriod()));
                txtPercentage.setText(String.valueOf(newValue.getPercentage()));
                txtMonthlyPremium.setText(String.valueOf(newValue.getMonthlyPremium()));

                txtLoanID.setDisable(false);
                txtDebtorID.setDisable(false);
                txtAmount.setDisable(false);
                txtDate.setDisable(false);
                txtDueDate.setDisable(false);
                txtPeriod.setDisable(false);
                txtPercentage.setDisable(false);
                txtMonthlyPremium.setDisable(false);


            }
        });

        loadAllLoan();

    }

    private void loadAllLoan() {
        try {
            loanBO.gelAllLoan();
            ArrayList<LoanDTO> allLoan = loanBO.gelAllLoan();
            for (LoanDTO dto : allLoan) {
                tblLoan.getItems().add(new LoanAddTm(dto.getlID(), dto.getdID(), dto.getLoanAmount(), dto.getLoanDate(), dto.getLoanDueDate(),
                        dto.getPeriod(), dto.getPercentage(), dto.getMonthlyPremium()));
            }

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        // String dID = txtDebtorID.getText();
        String lID = txtLoanID.getText();
        String dID =txtDebtorID.getText();
        double amount = Double.parseDouble(txtLoanAmount.getText());
        String loanDate = txtDate.getText();
        String loanDueDate =txtDueDate.getText();
        int period = Integer.parseInt(txtPeriod.getText());
        double percentage = Double.parseDouble(txtPercentage.getText());
        double monthlyPremium= Double.parseDouble(txtMonthlyPremium.getText());

        try {
            if(exitRegister(lID)){
                new Alert(Alert.AlertType.ERROR,lID+"Allready Register").show();

            }
            loanBO.addLoan(new LoanDTO(lID,dID,amount,loanDate,loanDueDate,period,percentage,monthlyPremium));
            tblLoan.getItems().add(new LoanAddTm(lID,dID,amount,loanDate,loanDueDate,period,percentage,monthlyPremium));

        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,lID+"Failed").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return loanBO.existLoan(code);
    }

    private String genarateNewIDs() {
        try {
            return loanBO.genaRateNewId();
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "L00-001";

    }

    public void btnNewSaveOnAction(ActionEvent actionEvent) {
        txtLoanID.setText(genarateNewIDs());

    }
    public void txtPercentageOnAction(ActionEvent actionEvent) {
        double amount = Double.parseDouble(txtLoanAmount.getText());
        int period  = Integer.parseInt(txtPeriod.getText());
        double precentage = Double.parseDouble(txtPercentage.getText());

        double temp =(double) (amount*precentage*0.01)/period;

        double premium =(amount/period)+temp;

        txtMonthlyPremium.setText(String.valueOf(premium));
    }

}

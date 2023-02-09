package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.model.LoanModel;
import lk.ijse.microfinance.to.LoanDTO;
import lk.ijse.microfinance.view.tm.LoanAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoanController {
    public AnchorPane ancLoan;
    public JFXTextField txtAmount;
    public JFXTextField txtPeriod;
    public Label lblMonthlyPremium;
    public JFXTextField txtDueDate;
    public JFXTextField txtDebtorID;
    public JFXTextField txtDate;
    public JFXTextField txtLoanID;
    public TableView tblLoan;
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
    private String searchText ="";


    public void initialize(){
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collDebtorID.setCellValueFactory(new PropertyValueFactory<>("idDebtor"));
        collAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        collDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        collDeuDate.setCellValueFactory(new PropertyValueFactory<>("loanDueDate"));
        collPeriod.setCellValueFactory(new PropertyValueFactory<>("period"));
        collPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        collMonthlyPremium.setCellValueFactory(new PropertyValueFactory<>("monthlyPremium"));

        AddLoanTbl(searchText);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddLoanTbl(searchText);
        });


    }

    private void AddLoanTbl(String text) {
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {

    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }




}

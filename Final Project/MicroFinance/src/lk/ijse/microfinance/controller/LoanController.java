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
    private Matcher lIdMatcher;
    private Matcher lDebtorIdMatcher;
    private Matcher lAmountMatcher;
    private Matcher lPeriodMatcher;

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

        setPattern();

    }

    public void setPattern(){

        Pattern userIdPattern = Pattern.compile("^(L0)([0-9]{1})([0-9]{1,})$");
        lIdMatcher = userIdPattern.matcher(txtLoanID.getText());

        Pattern debtorIdPattern = Pattern.compile("^(D0)([0-9]{1})([0-9]{1,})$");
        lDebtorIdMatcher = debtorIdPattern.matcher(txtDebtorID.getText());


        Pattern amountPattern = Pattern.compile("^[0-9]{1,}$");
        lAmountMatcher = amountPattern.matcher(txtLoanAmount.getText());

        Pattern periodPattern = Pattern.compile("^[0-9]{1,}$");
        lPeriodMatcher = periodPattern.matcher(txtPeriod.getText());


    }
    private void AddLoanTbl(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<LoanAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Loan WHERE lID LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                LoanAddTm loanAddTm = new LoanAddTm(
                        set.getString(1),
                        set.getString(2),
                        set.getDouble(3),
                        set.getString(4),
                        set.getString(5),
                        set.getInt(6),
                        set.getDouble(7),
                        set.getDouble(8));
                tmList.add(loanAddTm);
            }

            tblLoan.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){

        }
    }
    public static ArrayList<LoanDTO> getAllLoan() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet result = connection.prepareStatement("SELECT * FROM Loan").executeQuery();
        ArrayList<LoanDTO> data = new ArrayList<>();
        while(result.next()){
            LoanDTO loan = new LoanDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getString(5),
                    result.getInt(6),
                    result.getDouble(7),
                    result.getDouble(8));
            data.add(loan);
        }
        return data;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
       LoanDTO loan = new LoanDTO(txtLoanID.getText(),
               txtDebtorID.getText(),
               Double.parseDouble(txtLoanAmount.getText()),
               txtDate.getText(),
               txtDueDate.getText(),
               Integer.parseInt(txtPeriod.getText()),
               Double.parseDouble(txtPeriod.getText()),
               Double.parseDouble(txtMonthlyPremium.getText()));


        setPattern();
        if(lIdMatcher.matches()) {
            if(lDebtorIdMatcher.matches()) {
                if(lAmountMatcher.matches()) {
                    if(lPeriodMatcher.matches()) {


                    } else {
                        txtPeriod.requestFocus();
                        lblPeriod.setText("invalid Period ");
                    }
                } else {
                    txtLoanAmount.requestFocus();
                    lblAmount.setText("invalid Amount ");
                }
            } else {
                txtDebtorID.requestFocus();
                lblDebtorId.setText("invalid ID ");

            }
        } else {
            txtLoanID.requestFocus();
            lblLoanId.setText("invalid ID ");
        }
        try{
            boolean isAdded = LoanModel.register(loan);
            AddLoanTbl(searchText);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Loan Details Added!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void txtAmountKeyTypeOnAction(KeyEvent keyEvent) {
        lblAmount.setText("");

        Pattern amountPattern = Pattern.compile("^[0-9]{1,}$");
        lAmountMatcher = amountPattern.matcher(txtLoanAmount.getText());

        if (!lAmountMatcher.matches()) {
            txtLoanAmount.requestFocus();
            lblAmount.setText("invalid Amount");
        }
    }

    public void txtPeriodKeyTypeOnAction(KeyEvent keyEvent) {
        lblPeriod.setText("");

        Pattern periodPattern = Pattern.compile("^[0-9]{1,}$");
        lPeriodMatcher = periodPattern.matcher(txtPeriod.getText());

        if (!lPeriodMatcher.matches()) {
            txtPeriod.requestFocus();
            lblPeriod.setText("invalid Period");
        }
    }

    public void txtlIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblLoanId.setText("");

        Pattern userIdPattern = Pattern.compile("^(L0)([0-9]{1})([0-9]{1,})$");
        lIdMatcher = userIdPattern.matcher(txtLoanID.getText());

        if (!lIdMatcher.matches()) {
            txtLoanID.requestFocus();
            lblLoanId.setText("invalid ID");
        }
    }

    public void txtDebtorIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblDebtorId.setText("");

        Pattern debtorIdPattern = Pattern.compile("^(D0)([0-9]{1})([0-9]{1,})$");
        lDebtorIdMatcher = debtorIdPattern.matcher(txtDebtorID.getText());

        if (!lDebtorIdMatcher.matches()) {
            txtDebtorID.requestFocus();
            lblDebtorId.setText("invalid ID");
        }
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

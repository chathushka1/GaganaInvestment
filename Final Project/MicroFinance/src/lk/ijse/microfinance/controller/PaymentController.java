package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.model.PaymentModel;
import lk.ijse.microfinance.model.PlaceLoanModel;
import lk.ijse.microfinance.dto.*;
import lk.ijse.microfinance.view.tm.PaymentAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentController {
    public AnchorPane ancPayment;
    public JFXTextField txtPaymentID;
    public JFXTextField txtAmount;
    public JFXTextField txtDate;
    public TableView tblPayment;
    public TableColumn collPaymentID;
    public TableColumn collLoanID;
    public TableColumn collAmount;
    public TableColumn collDate;
    public TableColumn collTotalDeuAmount;
    public JFXTextField txtTotalAmountDeu;
    public JFXTextField txtSearch;
    public JFXTextField txtLoanId;

    private String searchText ="";

    public void initialize(){
        collPaymentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collDate.setCellValueFactory(new PropertyValueFactory<>("loanDate"));
        collAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        collTotalDeuAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmountDeu"));


        AddPaymentTbl(searchText);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddPaymentTbl(searchText);
        });

    }

    private void AddPaymentTbl(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<PaymentAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Payment WHERE pID LIKE ? || lID LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                PaymentAddTm paymentAddTm = new PaymentAddTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4),
                        set.getDouble(5));
                tmList.add(paymentAddTm);
            }

            tblPayment.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){

        }
    }

    public void btnPayOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PaymentDTO payment = new PaymentDTO(
                txtPaymentID.getText(),
                txtLoanId.getText(),
                txtDate.getText(),
                Double.parseDouble(txtAmount.getText()),
                Double.parseDouble(txtTotalAmountDeu.getText()));
        String loanId = txtLoanId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        try{
            boolean isAdded = PaymentModel.register(payment);
            AddPaymentTbl(searchText);

            if(isAdded){

               Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Payment SuccessFully!",ButtonType.YES,ButtonType.NO);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get()==ButtonType.YES){
                    boolean isPlace = PlaceLoanModel.placeLoan(loanId,amount);
                    System.out.println(isPlace);
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString());
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String pid=txtPaymentID.getText();
        String plid=txtLoanId.getText();
        Double amount=Double.parseDouble(txtAmount.getText());
        String lDate =txtDate.getText();
        Double totalDeu =Double.parseDouble(txtTotalAmountDeu.getText());


        String sql="UPDATE Payment SET lID  = ?, loanDate = ?, amount  = ?,  totalAmountDeu = ? WHERE pID = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre=connection.prepareStatement(sql);

            pre.setObject(1, txtLoanId.getText());
            pre.setObject(2, txtDate.getText());
            pre.setObject(3, txtAmount.getText());
            pre.setObject(4, txtTotalAmountDeu.getText());
            pre.setObject(5, txtPaymentID.getText());

            int executeUpdate = pre.executeUpdate();
            AddPaymentTbl(searchText);

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Updated!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }

        } catch (Exception e) {

        }
    }
}

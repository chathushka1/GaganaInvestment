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
import lk.ijse.microfinance.model.GuarantorModel;
import lk.ijse.microfinance.to.GuarantorDTO;
import lk.ijse.microfinance.view.tm.GuarantorAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuarantorFormController {
    public AnchorPane ancGuarantor;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtTelephone;
    public JFXTextField txtGuarantorID;
    public JFXTextField txtLoanID;
    public TableView tblGuarantor;
    public TableColumn collGuarantorID;
    public TableColumn collLoanID;
    public TableColumn collName;
    public TableColumn collAddress;
    public TableColumn collNIC;
    public TableColumn collTelephone;
    public JFXTextField txtSearch;
    public Label lblGuarantorId;
    public Label lblLoanId;
    public Label lblName;
    public Label lblAddress;
    public Label lblNic;
    public Label lblTelephne;
    private String searchText ="";
    private Matcher gIdMatcher;
    private Matcher gLoanIdMatcher;
    private Matcher gNameMatcher;
    private Matcher gAddressMatcher;
    private Matcher gNicMatcher;
    private Matcher gTelephoneMatcher;

    public void initialize(){
        collGuarantorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        AddGuarantorTbl(searchText);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddGuarantorTbl(searchText);
        });

    }

    public void setPattern(){
        Pattern userIdPattern = Pattern.compile("^(G0)([0-9]{1})([0-9]{1,})$");
        gIdMatcher = userIdPattern.matcher(txtGuarantorID.getText());

        Pattern loanIDPattern = Pattern.compile("^(L0)([0-9]{1})([0-9]{1,})$");
        gLoanIdMatcher = loanIDPattern.matcher(txtLoanID.getText());

        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gNameMatcher = userNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        Pattern nicPattern = Pattern.compile("^[0-9]{10}[vVxX]$");
        gNicMatcher = nicPattern.matcher(txtNIC.getText());

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        gTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());


    }

    private void AddGuarantorTbl(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<GuarantorAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Guarantor WHERE name LIKE ? || address LIKE ? || nic LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                GuarantorAddTm guarantorAddTm = new GuarantorAddTm(set.getString(1),set.getString(2),set.getString(3),
                        set.getString(4),set.getString(5),set.getString(6));
                tmList.add(guarantorAddTm);
            }
           // System.out.println(tmList);
            tblGuarantor.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){

        }
    }
    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       GuarantorDTO guarantor = new GuarantorDTO(txtGuarantorID.getText(),txtLoanID.getText(),txtName.getText(),txtAddress.getText(),txtNIC.getText(),txtTelephone.getText());
       // boolean isAdd = GuarantorRegisterModel.placeGuarantor(txtLoanID.getText(),txtGuarantorID.getText());

        setPattern();
        if(gIdMatcher.matches()) {
            if (gLoanIdMatcher.matches()){
                if(gNameMatcher.matches()) {
                    if(gAddressMatcher.matches()) {
                        if(gNicMatcher.matches()){
                                if(gTelephoneMatcher.matches()) {

                                } else {
                                    txtTelephone.requestFocus();
                                    lblTelephne.setText("invalid Contact ");
                                }

                        }else{
                            txtNIC.requestFocus();
                            lblNic.setText("invalid NIC");
                        }

                    } else {
                        txtAddress.requestFocus();
                        lblAddress.setText("invalid Address ");
                    }
                } else {
                    txtName.requestFocus();
                    lblName.setText("invalid Name ");
                }
            }else{
                txtLoanID.requestFocus();
                lblLoanId.setText("invalid ID ");
            }
        } else {
            txtGuarantorID.requestFocus();
            lblGuarantorId.setText("invalid ID ");

        }

        boolean isAdd = GuarantorModel.save(txtLoanID.getText(),txtGuarantorID.getText());
        if(isAdd){
            System.out.println("add");
        }
        try{
            boolean isAdded = GuarantorModel.register(guarantor);
            AddGuarantorTbl(searchText);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Guarantor Added!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String gId=txtGuarantorID.getText();
        String lId=txtLoanID.getText();
        String gName=txtName.getText();
        String gAddress=txtAddress.getText();
        String gNic=txtNIC.getText();
        String gTelephone=txtTelephone.getText();

        String sql="UPDATE Guarantor SET lID = ?, name = ?, address = ?, nic = ?, telephone = ?  where gID = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre=connection.prepareStatement(sql);

            pre.setObject(1, txtLoanID.getText());
            pre.setObject(2, txtName.getText());
            pre.setObject(3, txtAddress.getText());
            pre.setObject(4, txtNIC.getText());
            pre.setObject(5, txtTelephone.getText());
            pre.setObject(6, txtGuarantorID.getText());


            int executeUpdate = pre.executeUpdate();
            AddGuarantorTbl(searchText);

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Guarantor Updated!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }

        } catch (Exception e) {

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String gID=txtGuarantorID.getText();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="DELETE FROM Guarantor WHERE gID = ?";
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setObject(1, txtGuarantorID.getText());
            int executeUpdate = pre.executeUpdate();

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION," Deleted!").show();;

                //JOptionPane.showMessageDialog(rootPane, "Delete Items");
                AddGuarantorTbl(searchText);
            }
        } catch (Exception e) {
        }
    }

    public void txtgIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblGuarantorId.setText("");

        Pattern userIdPattern = Pattern.compile("^(G0)([0-9]{1})([0-9]{1,})$");
        gIdMatcher = userIdPattern.matcher(txtGuarantorID.getText());

        if (!gIdMatcher.matches()) {
            txtGuarantorID.requestFocus();
            lblGuarantorId.setText("invalid ID");
        }
    }

    public void txtgLoanIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblLoanId.setText("");

        Pattern loanIDPattern = Pattern.compile("^(L0)([0-9]{1})([0-9]{1,})$");
        gLoanIdMatcher = loanIDPattern.matcher(txtLoanID.getText());

        if (!gLoanIdMatcher.matches()) {
            txtLoanID.requestFocus();
            lblLoanId.setText("invalid ID");
        }
    }

    public void txtgNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");

        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gNameMatcher = userNamePattern.matcher(txtName.getText());

        if (!gNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }
    }

    public void txtgAddressKeyTypeOnAction(KeyEvent keyEvent) {
        lblAddress.setText("");

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        if (!gAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Address");
        }
    }

    public void txtgNicKeyTypeOnAction(KeyEvent keyEvent) {
        lblNic.setText("");

        Pattern nicPattern = Pattern.compile("^[0-9]{10}[vVxX]$");
        gNicMatcher = nicPattern.matcher(txtNIC.getText());

        if (!gNicMatcher.matches()) {
            txtNIC.requestFocus();
            lblNic.setText("invalid Nic");
        }
    }

    public void txtgTelephoneKeyTypeOnAction(KeyEvent keyEvent) {
        lblTelephne.setText("");

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        gTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());

        if (!gTelephoneMatcher.matches()) {
            txtTelephone.requestFocus();
            lblTelephne.setText("invalid Number");
        }
    }
}

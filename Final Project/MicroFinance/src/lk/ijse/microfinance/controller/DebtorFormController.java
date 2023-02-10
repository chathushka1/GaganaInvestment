package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.custom.DebtorBO;
import lk.ijse.microfinance.model.DebtorDTO;
import lk.ijse.microfinance.view.tm.DebtorAddTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;

public class DebtorFormController {

    public AnchorPane ancDebtor;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtTelephone;
    public JFXTextField txtLoanAmount;
    public JFXTextField txtDebtorID;
    public JFXTextField txtEmployeeID;
    public TableView<DebtorAddTm> tblDebtor;
    public TableColumn collDebtorID;
    public TableColumn collName;
    public TableColumn collAddress;
    public TableColumn collNIC;
    public TableColumn collLoanAmount;
    public TableColumn collTelephone;
    public TableColumn collEmployeeID;
    public JFXTextField txtSearch;
    public Label lblName;
    public Label lblAddress;
    public Label lblNic;
    public Label lblLoanAmount;
    public Label lblTelephone;
    public Label lblDId;
    public Label lblEmployeeID;
    public JFXButton btnRegisterID;
    public JFXButton btnNewRegisterID;
    public JFXButton btnDeleteId;
    private String searchText ="";
    private Matcher dIdMatcher;
    private Matcher emIdMatcher;
    private Matcher dNameMatcher;
    private Matcher dAddressMatcher;
    private Matcher dNicMatcher;
    private Matcher dAmountMatcher;
    private Matcher dPositionMatcher;
    private Matcher dTelephoneMatcher;
    DebtorBO debtorBO = (DebtorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEBTORBO);

    public void initialize(){
        collDebtorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        collLoanAmount.setCellValueFactory(new PropertyValueFactory<>("amountDeu"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        collEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        initUI();
        tblDebtor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteId.setDisable(newValue==null);
            btnRegisterID.setText(newValue!=null?"update":"save");
            btnRegisterID.setDisable(newValue==null);

            if(newValue!=null){
                txtDebtorID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtNIC.setText(newValue.getNic());
                txtLoanAmount.setText(String.valueOf(newValue.getAmountDeu()));
                txtTelephone.setText(newValue.getTelephone());
                txtEmployeeID.setText(newValue.getEmployeeId());

                txtDebtorID.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtNIC.setDisable(false);
                txtLoanAmount.setDisable(false);
                txtTelephone.setDisable(false);
                txtEmployeeID.setDisable(false);

            }
        });

        txtEmployeeID.setOnAction(event -> btnRegisterID.fire());
        loadAllDebtor();

    }
    public void initUI(){

        txtDebtorID.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtLoanAmount.clear();
        txtTelephone.clear();
        txtEmployeeID.clear();

        txtDebtorID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtNIC.setDisable(true);
        txtLoanAmount.setDisable(true);
        txtTelephone.setDisable(true);
        txtEmployeeID.setDisable(true);

        txtDebtorID.setEditable(false);
        btnRegisterID.setDisable(true);
        btnDeleteId.setDisable(true);

    }
    private void loadAllDebtor(){
        try{
            debtorBO.gelAllDebtor();
            ArrayList<DebtorDTO> allDebtor = debtorBO.gelAllDebtor();
            for(DebtorDTO dto : allDebtor){
                tblDebtor.getItems().add(new DebtorAddTm(dto.getdID(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getAmountDeu(),
                                                         dto.getTelephone(),dto.getEmployeeId()));
            }

        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        ancDebtor.getChildren().clear();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
         String dID = txtDebtorID.getText();
         String name =txtName.getText();
         String address = txtAddress.getText();
         String nic = txtNIC.getText();
         double amountDue = Double.parseDouble(txtLoanAmount.getText());
         String telephone = txtTelephone.getText();
         String eID = txtEmployeeID.getText();



        if (!name.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
//            txtName.setFocusColor(Paint.valueOf("Red"));
            return;
        } else if (!address.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should not ").show();
            txtAddress.requestFocus();
            return;
        }else if(!txtNIC.getText().matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "NIC should not ").show();
            txtNIC.requestFocus();
        }else if(!txtLoanAmount.getText().matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR, "Amount should not ").show();
            txtLoanAmount.requestFocus();
        }else if (!telephone.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Contact should be at long").show();
            txtTelephone.requestFocus();
            return;
        }

         if(btnRegisterID.getText().equalsIgnoreCase("Save Debtor")){
             try {
                 if(exitRegister(dID)){
                   new Alert(Alert.AlertType.ERROR,dID+"Allready Register").show();

                 }
                 debtorBO.addDebtor(new DebtorDTO(dID,name,address,nic,amountDue,telephone,eID));
                 tblDebtor.getItems().add(new DebtorAddTm(dID,name,address,nic,amountDue,telephone,eID));

             }
             catch (SQLException e){
                 new Alert(Alert.AlertType.ERROR,dID+"Failed").show();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }
         }else {
             try{
                 if(!exitRegister(dID)){
                     new Alert(Alert.AlertType.ERROR,dID+"Allready Update").show();
                 }
                 debtorBO.updateDebtor(new DebtorDTO(dID,name,address,nic,amountDue,telephone,eID));

                 DebtorAddTm debtorAddTm = tblDebtor.getSelectionModel().getSelectedItem();
                 debtorAddTm.setName(name);
                 debtorAddTm.setAddress(address);
                 debtorAddTm.setNic(nic);
                 debtorAddTm.setAmountDeu(amountDue);
                 debtorAddTm.setTelephone(telephone);
                 debtorAddTm.setEmployeeId(eID);
                 tblDebtor.refresh();

             }catch (SQLException e){} catch (ClassNotFoundException e) {
                 e.printStackTrace();
                 new Alert(Alert.AlertType.ERROR,dID+"Failed").show();
                 e.printStackTrace();
             }

         }
         btnNewRegisterID.fire();
    }

    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return debtorBO.existDebtor(code);
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        txtDebtorID.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtNIC.setDisable(false);
        txtLoanAmount.setDisable(false);
        txtTelephone.setDisable(false);
        txtEmployeeID.setDisable(false);

        txtDebtorID.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtLoanAmount.clear();
        txtTelephone.clear();
        txtEmployeeID.clear();

        txtDebtorID.setText(genarateNewID());
        btnRegisterID.setDisable(false);
        btnRegisterID.setText("Save Debtor");
    }

    private String genarateNewID() {
        try{
            return debtorBO.genaRateNewId();
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "D00-001";

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblDebtor.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure DELETE ?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                debtorBO.deleteDebtor(id);
                tblDebtor.getItems().remove(tblDebtor.getSelectionModel().getSelectedItem());
                tblDebtor.getSelectionModel().clearSelection();
                initUI();
            }catch (SQLException e){

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

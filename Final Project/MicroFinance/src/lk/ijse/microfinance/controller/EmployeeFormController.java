package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.custom.EmployeeBO;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.model.EmployeeModel;
import lk.ijse.microfinance.to.DebtorDTO;
import lk.ijse.microfinance.to.EmployeeDTO;
import lk.ijse.microfinance.view.tm.DebtorAddTm;
import lk.ijse.microfinance.view.tm.EmployeeAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public AnchorPane ancEmployee;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtTelephone;
    public JFXTextField txtPosition;
    public JFXTextField txteID;
    public TableView<EmployeeAddTm> tblEmployee;
    public TableColumn<EmployeeAddTm,String> collEmployeeID;
    public TableColumn<EmployeeAddTm,String> collName;
    public TableColumn<EmployeeAddTm,String> collAddress;
    public TableColumn<EmployeeAddTm,String> collNIC;
    public TableColumn<EmployeeAddTm,String> collPosition;
    public TableColumn<EmployeeAddTm,String> collTelephone;
    public JFXTextField txtSearch;
    public Label lblEID;
    public Label lblName;
    public Label lblAddress;
    public Label lblNic;
    public Label lblPosition;
    public Label lblTelephone;
    public JFXButton btnRegisterID;
    public JFXButton btnNewRegisterId;
    public JFXButton btnDeleteId;
    private String searchText ="";
    private Matcher emIdMatcher;
    private Matcher emNameMatcher;
    private Matcher emAddressMatcher;
    private Matcher emNicMatcher;
    private Matcher emPositionMatcher;
    private Matcher emTelephoneMatcher;
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEEBO);

    public void initialize(){

        collEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        collPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        initUI();
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteId.setDisable(newValue==null);
            btnRegisterID.setText(newValue!=null?"update":"save");
            btnRegisterID.setDisable(newValue==null);

            if(newValue!=null){
                txteID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtNIC.setText(newValue.getNic());
                txtPosition.setText(newValue.getPosition());
                txtTelephone.setText(newValue.getTelephone());


                txteID.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtNIC.setDisable(false);
                txtPosition.setDisable(false);
                txtTelephone.setDisable(false);
            }
        });

        txteID.setOnAction(event -> btnRegisterID.fire());
        loadAllEmployee();
    }
    public void initUI(){

        txteID.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtPosition.clear();
        txtTelephone.clear();

        txteID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtNIC.setDisable(true);
        txtPosition.setDisable(true);
        txtTelephone.setDisable(true);

        txteID.setEditable(false);
        btnRegisterID.setDisable(true);
        btnDeleteId.setDisable(true);

    }

    private void loadAllEmployee(){
        try {
            employeeBO.gelAllEmployee();
            ArrayList<EmployeeDTO> allEmployee = employeeBO.gelAllEmployee();
            for(EmployeeDTO dto: allEmployee){
                tblEmployee.getItems().add(new EmployeeAddTm(dto.geteID(),dto.getName(),dto.getAddress(),dto.getNic(),
                                                             dto.getPosition(),dto.getTelephone()));
            }
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(code);
    }
    public void btnRegisterOnAction(ActionEvent actionEvent) {
         String eID = txteID.getText();
         String name = txtName.getText();
         String address = txtAddress.getText();
         String nic = txtNIC.getText();
         String position = txtPosition.getText();
         String telephone = txtTelephone.getText();

        if (!eID.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
//            txtName.setFocusColor(Paint.valueOf("Red"));
            return;
        } else if (!txtAddress.getText().matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Wrong Address ").show();
            txtAddress.requestFocus();
            return;
        }else if(!txtNIC.getText().matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Wrong NIC").show();
            txtNIC.requestFocus();
        }else if (!txtPosition.getText().matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Wrong Position ").show();
            txtAddress.requestFocus();
            return;
        }else if (!txtTelephone.getText().matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Contact should be at long").show();
            txtTelephone.requestFocus();
            return;
        }
        if(btnRegisterID.getText().equalsIgnoreCase("Save Employee")){
            try{
                if(exitRegister(eID)){
                    new Alert(Alert.AlertType.ERROR,eID+"Allready Register").show();
                }
                employeeBO.addEmployee(new EmployeeDTO(eID,name,address,nic,position,telephone));
                tblEmployee.getItems().add(new EmployeeAddTm(eID,name,address,nic,position,telephone));

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                if(!exitRegister(eID)){
                    new Alert(Alert.AlertType.ERROR,eID+"Allready Update").show();
                }
                employeeBO.updateEmployee(new EmployeeDTO(eID,name,address,nic,position,telephone));
                EmployeeAddTm employeeAddTm = tblEmployee.getSelectionModel().getSelectedItem();

                employeeAddTm.setName(name);
                employeeAddTm.setAddress(address);
                employeeAddTm.setNic(nic);
                employeeAddTm.setPosition(position);
                employeeAddTm.setTelephone(telephone);
                tblEmployee.refresh();
            }catch (SQLException e){

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,eID+"Failed").show();
                e.printStackTrace();
            }
        }
        btnNewRegisterId.fire();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        txteID.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtNIC.setDisable(false);
        txtPosition.setDisable(false);
        txtTelephone.setDisable(false);

        txteID.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtPosition.clear();
        txtTelephone.clear();

        txteID.setText(genarateNewID());
        btnRegisterID.setDisable(false);
        btnRegisterID.setText("Save Employee");
    }
    private String genarateNewID() {
        try{
            return employeeBO.genaRateNewId();
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "E00-001";

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblEmployee.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure DELETE ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                employeeBO.deleteEmployee(id);
                tblEmployee.getItems().remove(tblEmployee.getSelectionModel().getSelectedItem());
                tblEmployee.getSelectionModel().clearSelection();
                initUI();
            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void txtEmNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");
        Pattern userNamePattern = Pattern.compile("");
        emNameMatcher = userNamePattern.matcher(txtName.getText());

        /*if (!emNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }*/
    }

    public void txtEmAddressKeyTypeOnAction(KeyEvent keyEvent) {
        lblAddress.setText("");
        Pattern userAddressPattern = Pattern.compile("");
        emAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

      /*  if (!emAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Name");
        }*/
    }

    public void txtEmNicKeyTypeOnAction(KeyEvent keyEvent) {
    }

    public void txtEmTelephoneKeyTypeOnAction(KeyEvent keyEvent) {
        lblTelephone.setText("");

        Pattern userContactPattern = Pattern.compile("");
        emTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());

        /*if (!emTelephoneMatcher.matches()) {
            txtTelephone.requestFocus();
            lblTelephone.setText("invalid Number");
        }*/
    }

    public void txtEmPositionKeyTypeOnAction(KeyEvent keyEvent) {
    }

    public void txtEmIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblEID.setText("");

        Pattern userIdPattern = Pattern.compile("");
        emIdMatcher = userIdPattern.matcher(txteID.getText());

        /*if (!emIdMatcher.matches()) {
            txteID.requestFocus();
            lblEID.setText("invalid ID");
        }*/
    }

}



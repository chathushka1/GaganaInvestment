package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.custom.EmployeeBO;
import lk.ijse.microfinance.model.EmployeeDTO;
import lk.ijse.microfinance.view.tm.EmployeeAddTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;

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
            btnNewRegisterId.setText(newValue!=null ? "update" : "save");
            btnNewRegisterId.setDisable(newValue==null);

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

        txteID.setOnAction(event -> btnNewRegisterId.fire());
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
        btnNewRegisterId.setDisable(true);
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

    public void btnRegisterOnAction(ActionEvent actionEvent) {
         String emID = txteID.getText();
         String name = txtName.getText();
         String address = txtAddress.getText();
         String nic = txtNIC.getText();
         String position = txtPosition.getText();
         String telephone = txtTelephone.getText();

       /* if (!name.matches(".*[a-zA-Z0-9]{4,}")) {
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
            txtPosition.requestFocus();
            return;
        }else if (!txtTelephone.getText().matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Contact should be at long").show();
            txtTelephone.requestFocus();
            return;
        }*/

        if(btnNewRegisterId.getText().equalsIgnoreCase("Register")){
            try{
                if(exitRegister(emID)){
                    new Alert(Alert.AlertType.ERROR,emID+"Allready Register").show();
                }
                employeeBO.addEmployee(new EmployeeDTO(emID,name,address,nic,position,telephone));
                tblEmployee.getItems().add(new EmployeeAddTm(emID,name,address,nic,position,telephone));

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try {
                if(!exitRegister(emID)){
                    new Alert(Alert.AlertType.ERROR,"Allready Update"+emID).show();
                }
                employeeBO.updateEmployee(new EmployeeDTO(emID,name,address,nic,position,telephone));

            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,emID+"Failed").show();

            }
            EmployeeAddTm employeeAddTm = tblEmployee.getSelectionModel().getSelectedItem();

            employeeAddTm.setName(name);
            employeeAddTm.setAddress(address);
            employeeAddTm.setNic(nic);
            employeeAddTm.setPosition(position);
            employeeAddTm.setTelephone(telephone);
            tblEmployee.refresh();
        }
        btnRegisterID.fire();
    }

    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(code);
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

        txteID.setText(genarateNewIDS());
        txtName.requestFocus();
        btnNewRegisterId.setDisable(false);
        btnNewRegisterId.setText("Register");

    }
    private String genarateNewIDS() {
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




}



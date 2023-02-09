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
import lk.ijse.microfinance.bo.custom.GuarantorBO;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.entity.GuaranteeItem;
import lk.ijse.microfinance.model.GuarantorModel;
import lk.ijse.microfinance.to.DebtorDTO;
import lk.ijse.microfinance.to.GuarantorDTO;
import lk.ijse.microfinance.view.tm.DebtorAddTm;
import lk.ijse.microfinance.view.tm.GuarantorAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
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
    public TableView<GuarantorAddTm> tblGuarantor;
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
    public JFXButton btnNewRegisterID;
    public JFXButton btnRegisterID;
    public JFXButton btnDeleteID;
    private String searchText ="";
    private Matcher gIdMatcher;
    private Matcher gLoanIdMatcher;
    private Matcher gNameMatcher;
    private Matcher gAddressMatcher;
    private Matcher gNicMatcher;
    private Matcher gTelephoneMatcher;
    GuarantorBO guarantorBO = (GuarantorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUARANTORBO);

    public void initialize(){

        collGuarantorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        initUI();
        tblGuarantor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteID.setDisable(newValue==null);
            btnNewRegisterID.setText(newValue!=null?"update":"save");
            btnNewRegisterID.setDisable(newValue==null);

            if(newValue!=null){
                txtGuarantorID.setText(newValue.getId());
                txtLoanID.setText(newValue.getLoanId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtTelephone.setText(newValue.getTelephone());

                txtGuarantorID.setDisable(false);
                txtLoanID.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtNIC.setDisable(false);
                txtTelephone.setDisable(false);

            }
        });
        txtGuarantorID.setOnAction(event -> btnNewRegisterID.fire());
        loadAllGuarantor();

        }

    public void initUI(){

        txtGuarantorID.clear();
        txtLoanID.clear();
        txtName.clear();
        txtAddress.clear();
        txtNIC.clear();
        txtTelephone.clear();

        txtGuarantorID.setDisable(true);
        txtLoanID.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtNIC.setDisable(true);
        txtTelephone.setDisable(true);

        txtGuarantorID.setEditable(false);
        btnNewRegisterID.setDisable(true);
        btnDeleteID.setDisable(true);


    }
    private void loadAllGuarantor(){
        try{
            guarantorBO.gelAllGuarantor();
            ArrayList<GuarantorDTO> allGuarantor = guarantorBO.gelAllGuarantor();
            for(GuarantorDTO dto : allGuarantor){
                tblGuarantor.getItems().add(new GuarantorAddTm(dto.getgID(),dto.getlID(),dto.getName(),dto.getAddress(),
                        dto.getNic(),dto.getTelephone()));
            }

        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String gId=txtGuarantorID.getText();
        String lId=txtLoanID.getText();
        String gName=txtName.getText();
        String gAddress=txtAddress.getText();
        String gNic=txtNIC.getText();
        String gTelephone=txtTelephone.getText();

        if (!gName.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
//            txtName.setFocusColor(Paint.valueOf("Red"));
            return;
        } else if (!gAddress.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should not ").show();
            txtAddress.requestFocus();
            return;
        }else if(!gNic.matches(".*[a-zA-Z0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "NIC should not ").show();
            txtNIC.requestFocus();
        }else if (!gTelephone.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Contact should be at long").show();
            txtTelephone.requestFocus();
            return;
        }
        if(btnNewRegisterID.getText().equalsIgnoreCase("Save Guarantor")){
            try {
                if(exitRegister(gId)){
                    new Alert(Alert.AlertType.ERROR,gId+"Allready Register").show();

                }
                guarantorBO.addGuarantor(new GuarantorDTO(gId,lId,gName,gAddress,gNic,gTelephone));
                tblGuarantor.getItems().add(new GuarantorAddTm(gId,lId,gName,gAddress,gNic,gTelephone));

            }
            catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,gId+"Failed").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try{
                if(!exitRegister(gId)){
                    new Alert(Alert.AlertType.ERROR,gId+"Allready Update").show();
                }
                guarantorBO.updateGuarantor(new GuarantorDTO(gId,lId,gName,gAddress,gNic,gTelephone));

                GuarantorAddTm guarantorAddTm = (GuarantorAddTm) tblGuarantor.getSelectionModel().getSelectedItem();

                guarantorAddTm.setLoanId(lId);
                guarantorAddTm.setName(gName);
                guarantorAddTm.setAddress(gAddress);
                guarantorAddTm.setNic(gNic);
                guarantorAddTm.setTelephone(gTelephone);

            }catch (SQLException e){} catch (ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,gId+"Failed").show();
                e.printStackTrace();
            }

        }
        btnRegisterID.fire();
    }
    //gId,lId,gName,gAddress,gNic,gTelephone
    public void btnUpdateOnAction(ActionEvent actionEvent) {
       txtGuarantorID.setDisable(false);
       txtLoanID.setDisable(false);
       txtName.setDisable(false);
       txtAddress.setDisable(false);
       txtNIC.setDisable(false);
       txtTelephone.setDisable(false);

       txtGuarantorID.clear();
       txtLoanID.clear();
       txtName.clear();
       txtAddress.clear();
       txtNIC.clear();
       txtTelephone.clear();

        txtGuarantorID.setText(genarateNewIDs());
        btnNewRegisterID.setDisable(false);
        btnNewRegisterID.setText("Save Debtor");
    }
    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return guarantorBO.existGuarantor(code);
    }

    private String genarateNewIDs() {
        try{
            return guarantorBO.genaRateNewId();
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "G00-001";

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblGuarantor.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure DELETE ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                guarantorBO.deleteGuarantor(id);
                tblGuarantor.getItems().remove(tblGuarantor.getSelectionModel().getSelectedItem());
                tblGuarantor.getSelectionModel().clearSelection();
                initUI();
            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

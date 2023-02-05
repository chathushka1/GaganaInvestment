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
import lk.ijse.microfinance.bo.custom.GuaranteeItemBO;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.model.GuaranteeItemModel;
import lk.ijse.microfinance.to.DebtorDTO;
import lk.ijse.microfinance.to.GuaranteeItemDTO;
import lk.ijse.microfinance.to.GuarantorDTO;
import lk.ijse.microfinance.view.tm.DebtorAddTm;
import lk.ijse.microfinance.view.tm.GuaranteeItemAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuaranteeItemController {

    public AnchorPane ancGuaranteeItem;
    public JFXTextField txtValivation;
    public JFXTextField txtItemID;
    public JFXTextField txtLoanID;
    public TableView <GuaranteeItemAddTm> tblGuaranteeItem;
    public TableColumn<GuaranteeItemAddTm,String>  collLoanID;
    public TableColumn<GuaranteeItemAddTm,String>collName;
    public TableColumn <GuaranteeItemAddTm,Double>collValivation;
    public JFXTextField txtName;
    public JFXTextField txtSearch;
    public TableColumn <GuaranteeItemAddTm,String>collId;
    public Label lblItemID;
    public Label lblLoanId;
    public Label lblName;
    public Label lblValivation;
    public JFXButton btnRegisterID;
    public JFXButton btnDeleteID;
    public JFXButton btnNewRegisterID;
    private String searchText ="";
    private Matcher gItemIdMatcher;
    private Matcher gILoanIdMatcher;
    private Matcher gINameMatcher;
    private Matcher gIValivationMatcher;
    GuaranteeItemBO guaranteeItemBO = (GuaranteeItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUARANTEEITEMBO);


    public void initialize(){
        collId.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collValivation.setCellValueFactory(new PropertyValueFactory<>("valivation"));

        initUI();
        tblGuaranteeItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteID.setDisable(newValue==null);
            btnRegisterID.setText(newValue!=null?"update":"save");
            btnRegisterID.setDisable(newValue==null);

            if(newValue!=null){
                txtItemID.setText(newValue.getId());
                txtLoanID.setText(newValue.getLoanId());
                txtName.setText(newValue.getName());
                txtValivation.setText(String.valueOf(newValue.getValivation()));


                txtItemID.setDisable(false);
                txtLoanID.setDisable(false);
                txtName.setDisable(false);
                txtValivation.setDisable(false);

            }
        });

        txtItemID.setOnAction(event -> btnRegisterID.fire());
        loadAllGItem();



    }
    public void initUI(){
        txtItemID.clear();
        txtLoanID.clear();
        txtName.clear();
        txtValivation.clear();

        txtItemID.setDisable(true);
        txtLoanID.setDisable(true);
        txtName.setDisable(true);
        txtValivation.setDisable(true);

        txtItemID.setEditable(false);
        btnRegisterID.setDisable(true);
        btnDeleteID.setDisable(true);

    }

    private void loadAllGItem(){
        try{
            guaranteeItemBO.gelAllGItem();
            ArrayList<GuaranteeItemDTO> allGItem =  guaranteeItemBO.gelAllGItem();
            for(GuaranteeItemDTO dto : allGItem){
                tblGuaranteeItem.getItems().add(new GuaranteeItemAddTm(dto.getgItemID(),dto.getLoanId(),dto.getName(),dto.getValivation()));
            }

        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String gItemID = txtItemID.getId();
        String lID = txtLoanID.getText();
        String name = txtName.getText();
        double valivation  = Double.parseDouble(txtValivation.getText());

        if (!name.matches(".*[a-zA-Z0-9]{4,}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
//            txtName.setFocusColor(Paint.valueOf("Red"));
            return;
        } else if(!txtValivation.getText().matches(".*[0-9]{4,}")){
            new Alert(Alert.AlertType.ERROR, "Valivation should not ").show();
            txtValivation.requestFocus();
        }
        if(btnRegisterID.getText().equalsIgnoreCase("Save GuaranteeItem")){
            try {
                if(exitRegister(gItemID)){
                    new Alert(Alert.AlertType.ERROR,gItemID+"Allready Register").show();

                }
                guaranteeItemBO.addGItem(new GuaranteeItemDTO(gItemID,lID,name,valivation));
                tblGuaranteeItem.getItems().add(new GuaranteeItemAddTm(gItemID,lID,name,valivation));

            }
            catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,gItemID+"Failed").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            try{
                if(!exitRegister(gItemID)){
                    new Alert(Alert.AlertType.ERROR,gItemID+"Allready Update").show();
                }
                guaranteeItemBO.updateGItem(new GuaranteeItemDTO(gItemID,lID,name,valivation));

                GuaranteeItemAddTm guaranteeItemAddTm = tblGuaranteeItem.getSelectionModel().getSelectedItem();
                guaranteeItemAddTm.setLoanId(lID);
                guaranteeItemAddTm.setName(name);
                guaranteeItemAddTm.setValivation(valivation);

                tblGuaranteeItem.refresh();

            }catch (SQLException e){} catch (ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,gItemID+"Failed").show();
                e.printStackTrace();
            }

        }
        btnNewRegisterID.fire();



    }
    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return guaranteeItemBO.existGItem(code);
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        txtItemID.setDisable(false);
        txtLoanID.setDisable(false);
        txtName.setDisable(false);
        txtValivation.setDisable(false);

        txtItemID.clear();
        txtLoanID.clear();
        txtName.clear();
        txtValivation.clear();

        txtItemID.setText(genarateNewID());
        btnRegisterID.setDisable(false);
        btnRegisterID.setText("Save GuaranteeItem");

    }
    private String genarateNewID() {
        try{
            return guaranteeItemBO.genaRateNewId();
        }catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "GI00-001";

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblGuaranteeItem.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure DELETE ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                guaranteeItemBO.deleteGItem(id);
                tblGuaranteeItem.getItems().remove(tblGuaranteeItem.getSelectionModel().getSelectedItem());
                tblGuaranteeItem.getSelectionModel().clearSelection();
                initUI();
            }catch (SQLException e){

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public void txtLoanIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblLoanId.setText("");

        Pattern userIdPattern = Pattern.compile("");
        gILoanIdMatcher = userIdPattern.matcher(txtItemID.getText());
/*
        if (!gILoanIdMatcher.matches()) {
            txtLoanID.requestFocus();
            lblLoanId.setText("invalid ID");*/
        //}
    }

    public void txtItemIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblItemID.setText("");

        Pattern userIdPattern = Pattern.compile("");
        gItemIdMatcher = userIdPattern.matcher(txtItemID.getText());

        /*if (!gItemIdMatcher.matches()) {
            txtItemID.requestFocus();
            lblItemID.setText("invalid ID");
        }*/
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");
        Pattern userNamePattern = Pattern.compile("");
        gINameMatcher = userNamePattern.matcher(txtName.getText());

       /* if (!gItemIdMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }*/
    }

    public void txtValivationKeyTypeOnAction(KeyEvent keyEvent) {
        lblValivation.setText("");

        Pattern amountPattern = Pattern.compile("");
        gIValivationMatcher = amountPattern.matcher(txtValivation.getText());

       /* if (!gIValivationMatcher.matches()) {
            txtValivation.requestFocus();
            lblValivation.setText("invalid Number");
        }*/
    }
}

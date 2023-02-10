package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.custom.GuaranteeItemBO;
import lk.ijse.microfinance.model.GuaranteeItemDTO;
import lk.ijse.microfinance.view.tm.GuaranteeItemAddTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class GuaranteeItemController {
    public AnchorPane ancGuaranteeItem;
    public ImageView btnNewRegisterID;
    public JFXTextField txtName;
    public JFXButton btnRegisterID;
    public JFXButton btnDeleteID;
    public JFXTextField txtValivation;
    public JFXTextField txtItemID;
    public JFXTextField txtLoanID;
    public TableView <GuaranteeItemAddTm>tblGuaranteeItem;
    public TableColumn<GuaranteeItemAddTm, String> collId;
    public TableColumn collLoanID;
    public TableColumn collName;
    public TableColumn collValivation;
    public JFXTextField txtSearch;
    public Label lblItemID;
    public Label lblLoanId;
    public Label lblName;
    public Label lblValivation;
    public JFXButton btnAddNewItemId;
    private String searchText = "";

    GuaranteeItemBO guaranteeItemBO = (GuaranteeItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.GUARANTEEITEMBO);
    public void initialize() {
        collId.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collValivation.setCellValueFactory(new PropertyValueFactory<>("valivation"));

        initUI();
        tblGuaranteeItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteID.setDisable(newValue == null);
            btnRegisterID.setText(newValue != null ? "update" : "save");
            btnRegisterID.setDisable(newValue == null);

            if (newValue != null) {
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

        txtValivation.setOnAction(event -> btnRegisterID.fire());
        loadAllGItem();
    }
    public void initUI() {
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
    private void loadAllGItem() {
        try {
            guaranteeItemBO.gelAllGItem();
            ArrayList<GuaranteeItemDTO> allGItem = guaranteeItemBO.gelAllGItem();
            for (GuaranteeItemDTO dto : allGItem) {
                tblGuaranteeItem.getItems().add(new GuaranteeItemAddTm(dto.getgItemID(), dto.getLoanId(), dto.getName(), dto.getValivation()));
            }

        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void btnGenarateNewIdOnAction(ActionEvent actionEvent) {


        txtItemID.clear();
        txtLoanID.clear();
        txtName.clear();
        txtValivation.clear();

        txtItemID.setText(genarateNewIDS());

        txtItemID.setDisable(false);
        txtLoanID.setDisable(false);
        txtName.setDisable(false);
        txtValivation.setDisable(false);

        btnRegisterID.setDisable(false);
        btnRegisterID.setText("Save GuaranteeItem");

    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String gItemID = txtItemID.getText();
        String lID = txtLoanID.getText();
        String name = txtName.getText();
        double valivation = Double.parseDouble(txtValivation.getText());

        if (btnRegisterID.getText().equalsIgnoreCase("Save GuaranteeItem")) {
            try {
                if (exitRegister(gItemID)) {
                    new Alert(Alert.AlertType.ERROR, gItemID + "Allready Register").show();

                }
                guaranteeItemBO.addGItem(new GuaranteeItemDTO(gItemID, lID, name, valivation));
                tblGuaranteeItem.getItems().add(new GuaranteeItemAddTm(gItemID, lID, name, valivation));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, gItemID + "Failed").show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!exitRegister(gItemID)) {
                    new Alert(Alert.AlertType.ERROR, gItemID + "Allready Update").show();
                }
                guaranteeItemBO.updateGItem(new GuaranteeItemDTO(gItemID, lID, name, valivation));

                GuaranteeItemAddTm guaranteeItemAddTm = tblGuaranteeItem.getSelectionModel().getSelectedItem();
                guaranteeItemAddTm.setLoanId(lID);
                guaranteeItemAddTm.setName(name);
                guaranteeItemAddTm.setValivation(valivation);

                tblGuaranteeItem.refresh();

            } catch (SQLException e) {
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, gItemID + "Failed").show();
                e.printStackTrace();
            }

        }
         btnAddNewItemId.fire();


    }

    private boolean exitRegister(String code) throws SQLException, ClassNotFoundException {
        return guaranteeItemBO.existGItem(code);
    }
    private String genarateNewIDS() {
        try {
            return guaranteeItemBO.genaRateNewId();
        } catch (SQLException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";

    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblGuaranteeItem.getSelectionModel().getSelectedItem().getId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure DELETE ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                guaranteeItemBO.deleteGItem(id);
                tblGuaranteeItem.getItems().remove(tblGuaranteeItem.getSelectionModel().getSelectedItem());
                tblGuaranteeItem.getSelectionModel().clearSelection();
                 initUI();
            } catch (SQLException e) {

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

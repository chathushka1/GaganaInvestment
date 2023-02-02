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
import lk.ijse.microfinance.model.GuaranteeItemModel;
import lk.ijse.microfinance.to.GuaranteeItemDTO;
import lk.ijse.microfinance.view.tm.GuaranteeItemAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private String searchText ="";
    private Matcher gItemIdMatcher;
    private Matcher gILoanIdMatcher;
    private Matcher gINameMatcher;
    private Matcher gIValivationMatcher;


    public void initialize(){
        collId.setCellValueFactory(new PropertyValueFactory<>("id"));
        collLoanID.setCellValueFactory(new PropertyValueFactory<>("loanId"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collValivation.setCellValueFactory(new PropertyValueFactory<>("valivation"));


        AddGuaranteeItemTbl(searchText);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddGuaranteeItemTbl(searchText);
        });
        setPattern();
    }
    public void setPattern(){
        Pattern userIdPattern = Pattern.compile("^(GI0)([0-9]{1})([0-9]{1,})$");
        gItemIdMatcher = userIdPattern.matcher(txtItemID.getText());

        Pattern LoanIdPattern = Pattern.compile("^(GI0)([0-9]{1})([0-9]{1,})$");
        gILoanIdMatcher = LoanIdPattern.matcher(txtLoanID.getText());

        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gINameMatcher = userNamePattern.matcher(txtName.getText());

        Pattern valivationtPattern = Pattern.compile("^[0-9]{1,}$");
        gIValivationMatcher = valivationtPattern.matcher(txtValivation.getText());

    }

    private void AddGuaranteeItemTbl(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<GuaranteeItemAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM GuaranteeItem WHERE gItemID LIKE ? || name LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                GuaranteeItemAddTm guaranteeItemAddTm = new GuaranteeItemAddTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getDouble(4));
                tmList.add(guaranteeItemAddTm);
            }

            tblGuaranteeItem.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){

        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       GuaranteeItemDTO guaranteeItem  = new GuaranteeItemDTO(
               txtItemID.getText(),
               txtLoanID.getText(),
               txtName.getText(),
               Double.parseDouble(txtValivation.getText()));
        boolean isAdd = GuaranteeItemModel.save(txtItemID.getText(),txtLoanID.getText());

        setPattern();
        if(gItemIdMatcher.matches()) {
            if(gILoanIdMatcher.matches()) {
                if(gINameMatcher.matches()) {
                    if(gIValivationMatcher.matches()) {


                    } else {
                        txtValivation.requestFocus();
                        lblValivation.setText("invalid Contact ");
                    }
                } else {
                    txtName.requestFocus();
                    lblName.setText("invalid Address ");
                }
            } else {
                txtLoanID.requestFocus();
                lblLoanId.setText("invalid Name ");

            }
        } else {
            txtItemID.requestFocus();
            lblItemID.setText("invalid ID ");
        }

        if(isAdd){
            System.out.println("add");
        }
        try{
            boolean isAdded = GuaranteeItemModel.register(guaranteeItem);
            AddGuaranteeItemTbl(searchText);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Guarantee Item Added!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String gIid=txtItemID.getText();
        String loanId=txtLoanID.getText();
        String gIName=txtName.getText();
        Double gIValivation=Double.parseDouble(txtValivation.getText());


        String sql="UPDATE GuaranteeItem SET  lID = ?, name = ?, valivation = ? where gItemID = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setObject(1, txtLoanID.getText());
            pre.setObject(2, txtName.getText());
            pre.setObject(3, txtValivation.getText());
            pre.setObject(4, txtItemID.getText());



            int executeUpdate = pre.executeUpdate();
            AddGuaranteeItemTbl(searchText);

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Guarantor Updated!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }

        } catch (Exception e) {

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String iID=txtItemID.getText();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="DELETE FROM GuaranteeItem WHERE gItemID=?";
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setObject(1, txtItemID.getText());
            int executeUpdate = pre.executeUpdate();

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION," Deleted!").show();;

                //JOptionPane.showMessageDialog(rootPane, "Delete Items");
                AddGuaranteeItemTbl(searchText);
            }
        } catch (Exception e) {
        }
    }


    public void txtLoanIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblLoanId.setText("");

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        gILoanIdMatcher = userIdPattern.matcher(txtItemID.getText());

        if (!gILoanIdMatcher.matches()) {
            txtLoanID.requestFocus();
            lblLoanId.setText("invalid ID");
        }
    }

    public void txtItemIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblItemID.setText("");

        Pattern userIdPattern = Pattern.compile("^(GI0)([0-9]{1})([0-9]{1,})$");
        gItemIdMatcher = userIdPattern.matcher(txtItemID.getText());

        if (!gItemIdMatcher.matches()) {
            txtItemID.requestFocus();
            lblItemID.setText("invalid ID");
        }
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");
        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        gINameMatcher = userNamePattern.matcher(txtName.getText());

        if (!gItemIdMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }
    }

    public void txtValivationKeyTypeOnAction(KeyEvent keyEvent) {
        lblValivation.setText("");

        Pattern amountPattern = Pattern.compile("^[0-9]{1,}$");
        gIValivationMatcher = amountPattern.matcher(txtValivation.getText());

        if (!gIValivationMatcher.matches()) {
            txtValivation.requestFocus();
            lblValivation.setText("invalid Number");
        }
    }
}

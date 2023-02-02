package lk.ijse.microfinance.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.microfinance.bo.BOFactory;
import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.bo.custom.DebtorBO;
import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.model.DebtorModel;
import lk.ijse.microfinance.to.DebtorDTO;
import lk.ijse.microfinance.view.tm.DebtorAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


//        AddDebtorTbl(searchText);
//        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
//            searchText=newValue;
//            AddDebtorTbl(searchText);
    //    });
       //  setPattern();

         /*try {
            loadAllEmployee();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }
   /* private void loadAllEmployee() throws SQLException, ClassNotFoundException {
        for (Employee employee : EmployeeFormController.getAllEmployee()){
            cmbEmployeeId.getItems().add(employee.geteID());

        }

    }*/

    public void setPattern(){

        Pattern userIdPattern = Pattern.compile("^(D0)([0-9]{1})([0-9]{1,})$");
        dIdMatcher = userIdPattern.matcher(txtDebtorID.getText());

        Pattern EmployeeIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        emIdMatcher = EmployeeIdPattern.matcher(txtEmployeeID.getText());

        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        dNameMatcher = userNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z0-9]{4,})$");
        dAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        dTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());

        Pattern amountPattern = Pattern.compile("^[0-9]{1,}$");
        dAmountMatcher = amountPattern.matcher(txtLoanAmount.getText());

        Pattern nicPattern = Pattern.compile("^[0-9]{10}[vVxX]$");
        dNicMatcher = nicPattern.matcher(txtNIC.getText());
    }
   /* private void AddDebtorTbl(String text){
        String searchText = "%"+text+"%";
        try {
            *//*ObservableList<DebtorAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Debtor WHERE name LIKE ? || address LIKE ? || nic LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            ResultSet set = statement.executeQuery();*//*
           *//* DebtorDAOImpl debtorDAO = new DebtorDAOImpl();
            ArrayList<Debtor> allDebtor = debtorDAO.getAllDebtor();
            for (Debtor debtor : allDebtor) {
                tblDebtor.getItems().add(new DebtorAddTm(debtor.getdID(),debtor.getName(),debtor.getAddress(),debtor.getNic(),debtor.getAmountDeu(),debtor.getTelephone(),debtor.getEmployeeId()));

            }
*//*

        }catch (ClassNotFoundException  e){

        }
    }*/
    public void btnClearOnAction(ActionEvent actionEvent) {
        ancDebtor.getChildren().clear();
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
//        DebtorDTO debtor = new DebtorDTO(
//                txtDebtorID.getText(),
//                txtName.getText(),
//                txtAddress.getText(),
//                txtNIC.getText(),
//                Double.parseDouble(txtLoanAmount.getText()),
//                txtTelephone.getText(),
//                txtEmployeeID.getText());
//        setPattern();
        /*if(dIdMatcher.matches()) {
            if (emIdMatcher.matches()){
                 if(dNameMatcher.matches()) {
                     if(dAddressMatcher.matches()) {
                         if(dNicMatcher.matches()){
                             if(dAmountMatcher.matches()){
                                 if(dTelephoneMatcher.matches()) {


                                 } else {
                                    txtTelephone.requestFocus();
                                    lblTelephone.setText("invalid Contact ");
                                }
                           }else{
                                 txtLoanAmount.requestFocus();
                                 lblLoanAmount.setText("invalid Amount ");
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
                txtEmployeeID.requestFocus();
                lblEmployeeID.setText("invalid ID ");
            }
        } else {
            txtDebtorID.requestFocus();
            lblDId.setText("invalid ID ");
        }*/
         String dID = txtDebtorID.getText();
         String name =txtName.getText();
         String address = txtAddress.getText();
         String nic = txtNIC.getText();
         double amountDue = Double.parseDouble(txtLoanAmount.getText());
         String telephone = txtTelephone.getText();
         String eID = txtEmployeeID.getText();

         if(btnRegisterID.getText().equalsIgnoreCase("Registered")){
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

             }catch (SQLException e){} catch (ClassNotFoundException e) {
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
        txtDebtorID.setText(genarateNewID());
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
        String debtorId=txtDebtorID.getText();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="DELETE FROM Debtor WHERE  dID=?";
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setObject(1, txtDebtorID.getText());
            int executeUpdate = pre.executeUpdate();

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION," Deleted!").show();;
//                AddDebtorTbl(searchText);
            }
        } catch (Exception e) {
        }
    }

    public void txtEmIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblEmployeeID.setText("");

        Pattern EmployeeIdPattern = Pattern.compile("");
        emIdMatcher = EmployeeIdPattern.matcher(txtEmployeeID.getText());

        /*if (!emIdMatcher.matches()) {
            txtEmployeeID.requestFocus();
            lblEmployeeID.setText("invalid ID");
        }*/
    }

    public void txtDIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblDId.setText("");

        Pattern userIdPattern = Pattern.compile("");
        dIdMatcher = userIdPattern.matcher(txtDebtorID.getText());

       /* if (!dIdMatcher.matches()) {
            txtDebtorID.requestFocus();
            lblDId.setText("invalid ID");
        }*/
    }

    public void txtNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");

        Pattern userNamePattern = Pattern.compile("");
        dNameMatcher = userNamePattern.matcher(txtName.getText());

        /*if (!dNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }*/
    }

    public void txtAddressKeyTypeOnAction(KeyEvent keyEvent) {
        lblAddress.setText("");

        Pattern userAddressPattern = Pattern.compile("");
        dAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

     /*   if (!dAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Address");
        }*/
    }

    public void txtNicKeyTypeOnAction(KeyEvent keyEvent) {
        lblNic.setText("");

        Pattern nicPattern = Pattern.compile("");
        dNicMatcher = nicPattern.matcher(txtNIC.getText());

     /*   if (!dNicMatcher.matches()) {
            txtNIC.requestFocus();
            lblNic.setText("invalid Nic");
        }*/
    }

    public void txtTelephoneKeyTypeOnAction(KeyEvent keyEvent) {
        lblTelephone.setText("");

        Pattern userContactPattern = Pattern.compile("");
        dTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());
/*
        if (!dTelephoneMatcher.matches()) {
            txtTelephone.requestFocus();
            lblTelephone.setText("invalid Number");
        }*/
    }

    public void txtLoanAmountKeyTypeOnAction(KeyEvent keyEvent) {
        lblLoanAmount.setText("");

        Pattern amountPattern = Pattern.compile("");
        dAmountMatcher = amountPattern.matcher(txtLoanAmount.getText());

        /*if (!dAmountMatcher.matches()) {
            txtLoanAmount.requestFocus();
            lblLoanAmount.setText("invalid Number");
        }*/
    }
}

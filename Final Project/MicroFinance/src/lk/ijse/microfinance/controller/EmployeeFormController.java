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
import lk.ijse.microfinance.model.EmployeeModel;
import lk.ijse.microfinance.to.EmployeeDTO;
import lk.ijse.microfinance.view.tm.EmployeeAddTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String searchText ="";
    private Matcher emIdMatcher;
    private Matcher emNameMatcher;
    private Matcher emAddressMatcher;
    private Matcher emNicMatcher;
    private Matcher emPositionMatcher;
    private Matcher emTelephoneMatcher;

    public void initialize(){
        collEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        collName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        collNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        collPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        collTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        AddEmployeeTbl(searchText);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            AddEmployeeTbl(searchText);
        });

        setPattern();
     }

    public void setPattern(){
        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");  //(c0)([1-9]{1})([0-9]{1})
        emIdMatcher = userIdPattern.matcher(txteID.getText());

        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$"); //[a-zA-Z0-9]{4,}
        emNameMatcher = userNamePattern.matcher(txtName.getText());

        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z0-9]{4,})$"); //^[a-zA-Z0-9]{4,}$
        emAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");//.*^(?:7|0|(?:\+94))[0-9]{9,10}$*
        emTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());
    }


    private void AddEmployeeTbl(String text){
        String searchText = "%"+text+"%";
        try {
            ObservableList<EmployeeAddTm> tmList = FXCollections.observableArrayList();

            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Employee WHERE eID LIKE ? || name LIKE ? || address LIKE ? || nic LIKE ?";
            PreparedStatement statement =connection.prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            statement.setString(3,searchText);
            statement.setString(4,searchText);
            ResultSet set = statement.executeQuery();
            while(set.next()){
                EmployeeAddTm employeeAddTm = new EmployeeAddTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getString(6));
                tmList.add(employeeAddTm);
            }
           // System.out.println(tmList);

            tblEmployee.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){

        }
    }

    public static ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet result = connection.prepareStatement("SELECT * FROM Employee").executeQuery();
        ArrayList<EmployeeDTO> data = new ArrayList<>();
        while(result.next()){
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6));
            data.add(employeeDTO);
        }
        return data;
    }
    public void btnRegisterOnAction(ActionEvent actionEvent) {
      EmployeeDTO employeeDTO = new EmployeeDTO(
              txteID.getText(),
              txtName.getText(),
              txtAddress.getText(),
              txtNIC.getText(),
              txtPosition.getText(),
              txtTelephone.getText());

      setPattern();
        if(emIdMatcher.matches()) {
            if(emNameMatcher.matches()) {
                if(emAddressMatcher.matches()) {
                    if(emTelephoneMatcher.matches()) {


                    } else {
                        txtTelephone.requestFocus();
                        lblTelephone.setText("invalid Contact ");
                    }
                } else {
                    txtAddress.requestFocus();
                    lblAddress.setText("invalid Address ");
                }
            } else {
                txtName.requestFocus();
                lblName.setText("invalid Name ");

            }
        } else {
            txteID.requestFocus();
            lblEID.setText("invalid ID ");
        }

        try{
            boolean isAdded = EmployeeModel.register(employeeDTO);
            AddEmployeeTbl(searchText);
            if(isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Added!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String eId=txteID.getText();
        String eName=txtName.getText();
        String eAddress=txtAddress.getText();
        String eNic=txtNIC.getText();
        String ePosition=txtPosition.getText();
        String eTelephone=txtTelephone.getText();

        String sql="UPDATE Employee SET  name = ?, address = ?, nic = ?, position = ?, telephone = ?  where eID = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pre=connection.prepareStatement(sql);

            pre.setObject(1, txtName.getText());
            pre.setObject(2, txtAddress.getText());
            pre.setObject(3, txtNIC.getText());
            pre.setObject(4, txtPosition.getText());
            pre.setObject(5, txtTelephone.getText());
            pre.setObject(6, txteID.getText());

            int executeUpdate = pre.executeUpdate();
            AddEmployeeTbl(searchText);

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION," Updated!").show();;
            }else {
                new Alert(Alert.AlertType.WARNING,"Something Happened!").show();
            }

        } catch (Exception e) {

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String eID=txteID.getText();


        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql="DELETE FROM Employee WHERE eID=?";
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setObject(1, txteID.getText());
            int executeUpdate = pre.executeUpdate();

            if(executeUpdate>0){
                new Alert(Alert.AlertType.CONFIRMATION," Deleted!").show();
            AddEmployeeTbl(searchText);
            }
        } catch (Exception e) {
        }
    }


    public void txtEmNameKeyTypeOnAction(KeyEvent keyEvent) {
        lblName.setText("");
        Pattern userNamePattern = Pattern.compile("^([a-zA-Z]{4,})$");
        emNameMatcher = userNamePattern.matcher(txtName.getText());

        if (!emNameMatcher.matches()) {
            txtName.requestFocus();
            lblName.setText("invalid Name");
        }
    }

    public void txtEmAddressKeyTypeOnAction(KeyEvent keyEvent) {
        lblAddress.setText("");
        Pattern userAddressPattern = Pattern.compile("^([a-zA-Z0-9]{4,})$");
        emAddressMatcher = userAddressPattern.matcher(txtAddress.getText());

        if (!emAddressMatcher.matches()) {
            txtAddress.requestFocus();
            lblAddress.setText("invalid Name");
        }
    }

    public void txtEmNicKeyTypeOnAction(KeyEvent keyEvent) {
    }

    public void txtEmTelephoneKeyTypeOnAction(KeyEvent keyEvent) {
        lblTelephone.setText("");

        Pattern userContactPattern = Pattern.compile("^(?:7|0|(?:\\+94))[0-9]{9,10}$");
        emTelephoneMatcher = userContactPattern.matcher(txtTelephone.getText());

        if (!emTelephoneMatcher.matches()) {
            txtTelephone.requestFocus();
            lblTelephone.setText("invalid Number");
        }
    }

    public void txtEmPositionKeyTypeOnAction(KeyEvent keyEvent) {
    }

    public void txtEmIdKeyTypeOnAction(KeyEvent keyEvent) {
        lblEID.setText("");

        Pattern userIdPattern = Pattern.compile("^(E0)([0-9]{1})([0-9]{1,})$");
        emIdMatcher = userIdPattern.matcher(txteID.getText());

        if (!emIdMatcher.matches()) {
            txteID.requestFocus();
            lblEID.setText("invalid ID");
        }
    }

}



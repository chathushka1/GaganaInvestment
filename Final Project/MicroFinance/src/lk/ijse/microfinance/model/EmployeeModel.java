package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.to.EmployeeDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModel {
    /*public static boolean register(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Employee VALUES(?,?,?,?,?,?)");
        pstm.setString(1, employeeDTO.geteID());
        pstm.setString(2, employeeDTO.getName());
        pstm.setString(3, employeeDTO.getAddress());
        pstm.setString(4, employeeDTO.getNic());
        pstm.setString(5, employeeDTO.getPosition());
        pstm.setString(6, employeeDTO.getTelephone());


        return pstm.executeUpdate()>0 ;

    }*/

}

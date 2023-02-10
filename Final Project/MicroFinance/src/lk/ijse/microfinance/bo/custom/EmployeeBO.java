package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> gelAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existEmployee(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

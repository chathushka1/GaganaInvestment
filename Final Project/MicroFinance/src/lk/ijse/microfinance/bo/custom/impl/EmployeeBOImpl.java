package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.EmployeeBO;
import lk.ijse.microfinance.to.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public ArrayList<EmployeeDTO> gelAllEmployee() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}

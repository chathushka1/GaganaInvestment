package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.EmployeeBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.EmployeeDAO;
import lk.ijse.microfinance.entity.Employee;
import lk.ijse.microfinance.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> gelAllEmployee() throws SQLException, ClassNotFoundException {
        //eID,name,address,nic,position,telephone
        ArrayList<EmployeeDTO> allEmployee = new ArrayList<>();
        ArrayList<Employee> allEntity = employeeDAO.getAll();
        for(Employee employee : allEntity){
            allEmployee.add(new EmployeeDTO(employee.geteID(),employee.getName(),employee.getAddress(),employee.getNic(),
                    employee.getPosition(),employee.getTelephone()));
        }
        return allEmployee;
    }

    @Override
    public boolean addEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.geteID(),dto.getName(),dto.getAddress(),dto.getNic(),
                dto.getPosition(),dto.getTelephone()));
        }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateData(new Employee(dto.geteID(),dto.getName(),dto.getAddress(),dto.getNic(),
                dto.getPosition(), dto.getTelephone()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }
}

package lk.ijse.microfinance.dao.custom;

import lk.ijse.microfinance.dao.CrudDAO;
import lk.ijse.microfinance.dao.SuperDAO;
import lk.ijse.microfinance.entity.Employee;
import lk.ijse.microfinance.to.EmployeeDTO;

public interface EmployeeDAO extends SuperDAO, CrudDAO<Employee> {
}

package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.EmployeeDAO;
import lk.ijse.microfinance.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> allEmployee = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM Employee");
        while (set.next()){
            Employee employee = new Employee(set.getString("eID"),set.getString("name"),set.getString("address"),
                    set.getString("nic"),set.getString("position"),set.getString("telephone"));
            allEmployee.add(employee);
        }
        return allEmployee;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Employee(eID,name,address,nic,position,telephone) VALUES (?,?,?,?,?,?)",entity.geteID(),
                entity.getName(),entity.getAddress(),entity.getNic(),entity.getPosition(),entity.getTelephone());
    }

    @Override
    public boolean updateData(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE  Employee SET name = ?, address = ?, nic = ? ,position = ?, telephone=? WHERE eID=?",entity.getName(),
                entity.getAddress(),entity.getNic(),entity.getPosition(),entity.getTelephone(),entity.geteID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT eID FROM Employee WHERE eID = ?",id);
        return set.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT eID FROM Employee ORDER BY eID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("eID");
            int newEmployeeId=Integer.parseInt(ids.replace("E00-",""))+1;
            return String.format("E00-%03d",newEmployeeId);
        }else {
            return "E00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Employee WHERE eID =? ",id);
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM Employee WHERE eID = ?",id+"");
        set.next();
        return new Employee(id+"",set.getString("name"),set.getString("address"),set.getString("nic"),
                set.getString("position"),set.getString("telephone"));

    }
}

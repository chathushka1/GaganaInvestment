package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.DebtorDAO;
import lk.ijse.microfinance.entity.Debtor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DebtorDAOImpl implements DebtorDAO {
    @Override
    public ArrayList<Debtor> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Debtor> allDebtor = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM Debtor");
        while (set.next()){
            Debtor debtor = new Debtor(set.getString("dID"),set.getString("name"),set.getString("address"),set.getString("nic"),
                    set.getDouble("amountDue"),set.getString("telephone"),set.getString("eID"));
            allDebtor.add(debtor);
        }
        return allDebtor;
    }

    @Override
    public boolean add(Debtor entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Debtor(dID,name,address,nic,amountDue,telephone,eID) VALUES (?,?,?,?,?,?,?)"
                ,entity.getdID(),entity.getName(),
                entity.getAddress(),entity.getNic(),entity.getAmountDue(),entity.getTelephone(),entity.geteID());
    }

    @Override
    public boolean updateData(Debtor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Debtor SET name=?, address =?, nic=? ,amountDue=?,telephone=?,eID=? WHERE dID=?",entity.getName(),
                entity.getAddress(),entity.getNic(),entity.getAmountDue(),entity.getTelephone(),entity.geteID(),entity.getdID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT dID FROM Debtor WHERE dID=?",id);
        return set.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT dID FROM Debtor ORDER BY dID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("dID");
            int newDebtorId=Integer.parseInt(ids.replace("D00-",""))+1;
            return String.format("D00-%03d",newDebtorId);
        }else {
            return "D00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Debtor WHERE dID = ? ",id);
    }

    @Override
    public Debtor search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM Debtor WHERE dID = ?", id + "");
        set.next();
        return new Debtor(id + "", set.getString("name"), set.getString("address"), set.getString("nic"),
                set.getDouble("amountDue"), set.getString("telephone"), set.getString("eID"));

    }
}

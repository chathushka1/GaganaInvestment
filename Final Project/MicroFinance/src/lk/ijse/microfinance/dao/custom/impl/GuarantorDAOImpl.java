package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.GuarantorDAO;
import lk.ijse.microfinance.entity.Guarantor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuarantorDAOImpl implements GuarantorDAO {
    @Override
    public ArrayList<Guarantor> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Guarantor> allGuarantor = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM Guarantor");
        while (set.next()){
            Guarantor guarantor = new Guarantor(set.getString("gID"),set.getString("lID"),set.getString("name"),
                    set.getString("address"),set.getString("nic"),set.getString("telephone"));
            allGuarantor.add(guarantor);
        }
        return allGuarantor;
    }

    @Override
    public boolean add(Guarantor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Guarantor(gID,lID,name,address,nic,telephone) VALUES (?,?,?,?,?,?)"
                ,entity.getgID(),entity.getlID(),entity.getName(),entity.getAddress(),entity.getNic(),entity.getTelephone());
    }

    @Override
    public boolean updateData(Guarantor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Guarantor SET lID = ?, name = ?, address = ?, nic = ? ,telephone = ? WHERE gID=?",entity.getlID(),
                entity.getName(),entity.getAddress(),entity.getNic(),entity.getTelephone(),entity.getgID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT gID FROM Guarantor WHERE gID = ?",id);
        return rst.next();
          }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT gID FROM Guarantor ORDER BY gID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("gID");
            int newGuarantorId=Integer.parseInt(ids.replace("G00-",""))+1;
            return String.format("G00-%03d",newGuarantorId);
        }else {
            return "G00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Guarantor WHERE gID = ?",id);
    }

    @Override
    public Guarantor search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Guarantor WHERE gID = ?", id + "");
        rst.next();
        return new Guarantor(id + "", rst.getString("lID"),rst.getString("name"),rst.getString("address"),
                rst.getString("nic"),rst.getString("telephone"));
        //gID,lID,name,address,nic,telephone
    }

}

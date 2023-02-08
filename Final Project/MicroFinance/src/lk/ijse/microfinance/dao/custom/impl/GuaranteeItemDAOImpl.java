package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.GuaranteeItemDAO;
import lk.ijse.microfinance.entity.GuaranteeItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuaranteeItemDAOImpl implements GuaranteeItemDAO {
    @Override
    public ArrayList<GuaranteeItem> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<GuaranteeItem> allGItem = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM GuaranteeItem");
        while (set.next()){
            GuaranteeItem guaranteeItem = new GuaranteeItem(set.getString("gItemID"),set.getString("lID"),
                    set.getString("name"),set.getDouble("valivation"));
            allGItem.add(guaranteeItem);
        }
        return allGItem;
    }

    @Override
    public boolean add(GuaranteeItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO GuaranteeItem(gItemID,lID,name,valivation) VALUES (?,?,?,?)"
                ,entity.getgItemID(),entity.getlID(),entity.getName(),entity.getValivation());
    }

    @Override
    public boolean updateData(GuaranteeItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE GuaranteeItem SET lID = ?, name = ?, valivation = ?  WHERE gItemID = ?",
                entity.getlID(),entity.getName(),entity.getValivation(),entity.getgItemID());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT gItemID FROM GuaranteeItem WHERE gItemID=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT gItemID FROM GuaranteeItem ORDER BY gItemID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("gItemID");
            int newGItemId=Integer.parseInt(ids.replace("I00-",""))+1;
            return String.format("I00-%03d",newGItemId);
        }else {
            return "I00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM GuaranteeItem WHERE gItemID =? ",id);
    }

    @Override
    public GuaranteeItem search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM GuaranteeItem WHERE gItemID = ?",id+"");
        set.next();
        return new GuaranteeItem(id+"",set.getString("lID"),set.getString("name"),
                set.getDouble("valivation"));
    }

}

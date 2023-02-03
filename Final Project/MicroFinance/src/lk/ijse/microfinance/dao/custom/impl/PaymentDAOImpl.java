package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.PaymentDAO;
import lk.ijse.microfinance.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
 /*   private String pID;
    private String lID ;
    private String loanDate;
    private double amount;
    private double totalAmountDeu;*/
    @Override
    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> allPayment = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM Payment");
        while (set.next()){
            Payment payment = new Payment(set.getString("pID"),set.getString("lID"),set.getString("loanDate"),
                    set.getDouble("amount"),set.getDouble("totalAmountDeu"));
            allPayment.add(payment);
        }
        return allPayment;
    }

    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Payment(pID,lID,loanDate,amount,totalAmountDeu) VALUES (?,?,?,?,?)",entity.getpID(),
                entity.getlID(),entity.getLoanDate(),entity.getAmount(),entity.getTotalAmountDeu());
    }

    @Override
    public boolean updateData(Payment entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Payment SET lID=?,loanDate=?,amount=?,totalAmountDeu=? WHERE pID=?",entity.getlID(),entity.getLoanDate(),
                entity.getAmount(),entity.getTotalAmountDeu());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT pID FROM Payment WHERE pID=?",id);
        return set.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT pID FROM Payment ORDER BY pID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("pID");
            int newPaymentId=Integer.parseInt(ids.replace("P00-",""))+1;
            return String.format("P00-%03d",newPaymentId);
        }else {
            return "P00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Payment WHERE pID = ?",id);
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM Payment WHERE pID = ?", id + "");
        set.next();
        return new Payment(id + "", set.getString("lID"),set.getString("loanDate"),set.getDouble("amount"),
                set.getDouble("totalAmountDeu"));

    }
}
//pID,lID,loanDate,amount,totalAmountDeu
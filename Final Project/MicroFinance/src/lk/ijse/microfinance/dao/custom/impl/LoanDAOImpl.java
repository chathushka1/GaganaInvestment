package lk.ijse.microfinance.dao.custom.impl;

import lk.ijse.microfinance.dao.SQLUtil;
import lk.ijse.microfinance.dao.custom.LoanDAO;
import lk.ijse.microfinance.entity.Debtor;
import lk.ijse.microfinance.entity.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanDAOImpl implements LoanDAO {
    @Override
    public ArrayList<Loan> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Loan> allLoan = new ArrayList<>();
        ResultSet set = SQLUtil.execute("SELECT * FROM Loan");
        while (set.next()){
            Loan loan = new Loan(set.getString("lID"),set.getString("dID"),set.getDouble("amount"),
                    set.getString("loanDate"),set.getString("loanDueDate"),set.getInt("period"),
                    set.getDouble("percentage"),set.getDouble("monthlyPremium"));
            allLoan.add(loan);
        }
        return allLoan;
    }
  /*  private String lID;
    private String dID;
    private double amount;
    private String loanDate;
    private String loanDueDate;
    private int period ;
    private double percentage;
    private double monthlyPremium ;*/
    @Override
    public boolean add(Loan entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Loan(lID,dID,amount,loanDate,loanDueDate,period,percentage,monthlyPremium) VALUES (?,?,?,?,?,?,?,?)",
                entity.getlID(),entity.getdID(),entity.getAmount(),entity.getLoanDate(),entity.getLoanDueDate(),entity.getPeriod(),
                entity.getPercentage(),entity.getMonthlyPremium());
    }

    @Override
    public boolean updateData(Loan entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE  Loan SET dID=?,amount=?,loanDate=?,loanDueDate=?,period=?,percentage=?,monthlyPremium=? WHERE lID = ?",entity.getdID(),
               entity.getAmount(),entity.getLoanDate(),entity.getLoanDueDate(),entity.getPeriod(),entity.getPercentage(),entity.getMonthlyPremium());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT lID FROM Loan WHERE lID=?",id);
        return set.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQLUtil.execute("SELECT lID FROM Loan ORDER BY lID DESC LIMIT 1;");
        if (rst.next()){
            String ids=rst.getString("lID");
            int newLoanId=Integer.parseInt(ids.replace("L00-",""))+1;
            return String.format("L00-%03d",newLoanId);
        }else {
            return "L00-001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Loan WHERE lID = ? ",id);
    }

    @Override
    public Loan search(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT * FROM Loan WHERE lID = ?", id + "");
        set.next();
        return new Loan(id + "", set.getString("dID"),set.getDouble("amount"),set.getString("loanDate"),set.getString("loanDueDate"),
                set.getInt("period"),set.getDouble("percentage"),set.getDouble("monthlyPremium"));

    }
}

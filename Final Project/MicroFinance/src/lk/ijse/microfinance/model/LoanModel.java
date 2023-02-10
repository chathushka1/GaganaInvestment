package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.dto.LoanDTO;
import lk.ijse.microfinance.dto.LoanDetail;
import lk.ijse.microfinance.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanModel {
    public static boolean register(LoanDTO loan) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Loan VALUES(?,?,?,?,?,?,?,?)");
        pstm.setString(1,loan.getlID());
        pstm.setString(2,loan.getdID());
        pstm.setDouble(3,loan.getLoanAmount());
        pstm.setString(4,loan.getLoanDate());
        pstm.setString(5,loan.getLoanDueDate());
        pstm.setInt(6,loan.getPeriod());
        pstm.setDouble(7,loan.getPercentage());
        pstm.setDouble(8,loan.getMonthlyPremium());


        return pstm.executeUpdate()>0 ;

    }
    private static boolean updateLoan(LoanDTO loan) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Loan SET amount=amount-? WHERE  lID =?";
    return CrudUtil.execute(sql,loan.getLoanAmount(),loan.getlID());
    }
    public static boolean updateLoan(ArrayList<LoanDetail> loanDetails) throws SQLException, ClassNotFoundException {
    for(LoanDetail loanDetail : loanDetails ){
        if(! updateLoan(new LoanDTO(loanDetail.getlID(), loanDetail.getdID(), loanDetail.getAmount(), loanDetail.getLoanDate(), loanDetail.getLoanDueDate(),
                loanDetail.getPeriod(), loanDetail.getPercentage(), loanDetail.getMonthlyPremium()))){
            return false;
        }
    }
    return true;
    }

    public static double getAmount(String lId) throws SQLException, ClassNotFoundException {
        String sql = "select amount from Loan where lID=?";
        ResultSet result = CrudUtil.execute(sql,lId);

        if(result.next()){
            return result.getDouble(1);
        }
        return 0;
    }
    public static boolean updateAmount(double payAmount,String lID) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Loan SET amount=amount-? WHERE  lID =?";
        return CrudUtil.execute(sql,payAmount,lID);
    }
}

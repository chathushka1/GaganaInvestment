package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.to.PlaceLoan;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceLoanModel {
    public static boolean placeLoan(String lID,double lAmount) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
           // double amount = LoanModel.getAmount(lID);
            boolean isUpdateAmount = LoanModel.updateAmount(lAmount,lID);
            if (isUpdateAmount) {
                DBConnection.getInstance().getConnection().commit();
                return true;

            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }



}

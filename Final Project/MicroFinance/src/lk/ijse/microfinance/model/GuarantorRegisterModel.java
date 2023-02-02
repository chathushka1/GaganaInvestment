package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;

import java.sql.SQLException;
import java.time.LocalDate;

public class GuarantorRegisterModel {

        public static boolean placeGuarantor(String lID,String gID) throws SQLException, ClassNotFoundException {
            try {
                DBConnection.getInstance().getConnection().setAutoCommit(false);
                boolean isOrderAdded = GuarantorModel.save(lID,gID);
                if (isOrderAdded) {
                    DBConnection.getInstance().getConnection().commit();
                    return  true;
                }
                DBConnection.getInstance().getConnection().rollback();
                return false;
            } finally {
                DBConnection.getInstance().getConnection().setAutoCommit(true);
            }
        }
    }


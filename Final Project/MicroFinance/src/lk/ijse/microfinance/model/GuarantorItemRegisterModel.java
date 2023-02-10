package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;

import java.sql.SQLException;

public class GuarantorItemRegisterModel {
    /*public static boolean placeGuarantorItem(String gItemID,String lId) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = GuaranteeItemModel.save(gItemID,lId);
            if (isOrderAdded) {
                DBConnection.getInstance().getConnection().commit();
                return  true;
            }
            DBConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }*/
}

package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.to.GuaranteeItemDTO;
import lk.ijse.microfinance.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuaranteeItemModel {
   /* public static boolean register(GuaranteeItemDTO guaranteeItem) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO GuaranteeItem VALUES(?,?,?,?)");
        pstm.setString(1, guaranteeItem.getgItemID());
        pstm.setString(2, guaranteeItem.getLoanId());
        pstm.setString(3, guaranteeItem.getName());
        pstm.setDouble(4, guaranteeItem.getValivation());


        return pstm.executeUpdate()>0 ;

    }
    public static boolean save(String gIid,String lId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO baildetails VALUES(?, ?,now())";

        return CrudUtil.execute(sql,gIid,lId);

    }*/
}

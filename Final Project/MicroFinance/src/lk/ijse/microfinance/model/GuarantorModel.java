package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.to.GuarantorDTO;
import lk.ijse.microfinance.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GuarantorModel {
   /* public static boolean register(GuarantorDTO guarantor) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Guarantor VALUES(?,?,?,?,?,?)");
        pstm.setString(1,guarantor.getgID());
        pstm.setString(2,guarantor.getlID());
        pstm.setString(3,guarantor.getName());
        pstm.setString(4,guarantor.getAddress());
        pstm.setString(5,guarantor.getNic());
        pstm.setString(6,guarantor.getTelephone());



        return pstm.executeUpdate()>0 ;

    }

    public static boolean save(String lID,String gID) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SignDetails VALUES(?, ?,now())";

        return CrudUtil.execute(sql,lID,gID);

    }*/
}

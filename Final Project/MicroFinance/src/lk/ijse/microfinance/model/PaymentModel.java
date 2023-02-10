package lk.ijse.microfinance.model;

import lk.ijse.microfinance.db.DBConnection;
import lk.ijse.microfinance.dto.PaymentDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentModel {
    public static boolean register(PaymentDTO payment) throws SQLException, ClassNotFoundException {

        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("INSERT INTO Payment VALUES(?,?,?,?,?)");
        pstm.setString(1, payment.getpID());
        pstm.setString(2, payment.getlID());
        pstm.setString(3, payment.getLoanDate());
        pstm.setDouble(4, payment.getAmount());
        pstm.setDouble(5, payment.getTotalAmountDeu());


        return pstm.executeUpdate() > 0;
    }
}

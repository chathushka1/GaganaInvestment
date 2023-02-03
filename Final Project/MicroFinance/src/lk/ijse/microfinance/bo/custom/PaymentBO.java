package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.to.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDTO> gelAllPayment() throws SQLException, ClassNotFoundException;

    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existPayment(String id) throws SQLException, ClassNotFoundException;

    public  boolean deletePayment(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

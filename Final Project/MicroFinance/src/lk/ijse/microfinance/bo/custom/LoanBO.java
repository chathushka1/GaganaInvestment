package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.model.LoanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanBO extends SuperBO {
    public ArrayList<LoanDTO> gelAllLoan() throws SQLException, ClassNotFoundException;

    public boolean addLoan(LoanDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateLoan(LoanDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existLoan(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteLoan(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

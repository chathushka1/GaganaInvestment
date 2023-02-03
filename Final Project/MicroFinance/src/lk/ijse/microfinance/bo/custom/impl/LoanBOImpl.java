package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.LoanBO;
import lk.ijse.microfinance.to.LoanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoanBOImpl implements LoanBO {
    @Override
    public ArrayList<LoanDTO> gelAllLoan() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addLoan(LoanDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateLoan(LoanDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean existLoan(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteLoan(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}

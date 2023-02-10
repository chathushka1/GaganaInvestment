package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.dto.DebtorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DebtorBO extends SuperBO {
    public ArrayList<DebtorDTO> gelAllDebtor() throws SQLException, ClassNotFoundException;

    public boolean addDebtor(DebtorDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateDebtor(DebtorDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existDebtor(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteDebtor(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

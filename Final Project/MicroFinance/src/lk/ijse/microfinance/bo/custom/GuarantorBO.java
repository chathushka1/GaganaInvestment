package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.to.EmployeeDTO;
import lk.ijse.microfinance.to.GuarantorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuarantorBO extends SuperBO {
    public ArrayList<GuarantorDTO> gelAllGuarantor() throws SQLException, ClassNotFoundException;

    public boolean addGuarantor(GuarantorDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateGuarantor(GuarantorDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existGuarantor(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteGuarantor(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

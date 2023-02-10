package lk.ijse.microfinance.bo.custom;

import lk.ijse.microfinance.bo.SuperBO;
import lk.ijse.microfinance.dto.GuaranteeItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuaranteeItemBO extends SuperBO {
    public ArrayList<GuaranteeItemDTO> gelAllGItem() throws SQLException, ClassNotFoundException;

    public boolean addGItem(GuaranteeItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateGItem(GuaranteeItemDTO dto) throws SQLException, ClassNotFoundException;

    public  boolean existGItem(String id) throws SQLException, ClassNotFoundException;

    public  boolean deleteGItem(String id) throws SQLException, ClassNotFoundException;

    public String genaRateNewId() throws SQLException, ClassNotFoundException;
}

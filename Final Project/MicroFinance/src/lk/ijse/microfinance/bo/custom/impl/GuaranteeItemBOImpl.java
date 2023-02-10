package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.GuaranteeItemBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.GuaranteeItemDAO;
import lk.ijse.microfinance.entity.GuaranteeItem;
import lk.ijse.microfinance.model.GuaranteeItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuaranteeItemBOImpl implements GuaranteeItemBO {
    GuaranteeItemDAO guaranteeItemDAO = (GuaranteeItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GUARANTEEITEM);
    @Override
    public ArrayList<GuaranteeItemDTO> gelAllGItem() throws SQLException, ClassNotFoundException {
        ArrayList<GuaranteeItemDTO> allGItem = new ArrayList<>();
        ArrayList<GuaranteeItem> allEntity = guaranteeItemDAO.getAll();
        for(GuaranteeItem guaranteeItem : allEntity){
            allGItem.add(new GuaranteeItemDTO(guaranteeItem.getgItemID(),guaranteeItem.getlID(),guaranteeItem.getName(),
                    guaranteeItem.getValivation()));
        }
        return allGItem;
    }

    @Override
    public boolean addGItem(GuaranteeItemDTO dto) throws SQLException, ClassNotFoundException {
        return guaranteeItemDAO.add(new GuaranteeItem(dto.getgItemID(),dto.getLoanId(),dto.getName(),dto.getValivation()));

    }

    @Override
    public boolean updateGItem(GuaranteeItemDTO dto) throws SQLException, ClassNotFoundException {
        return guaranteeItemDAO.updateData(new GuaranteeItem(dto.getgItemID(),dto.getLoanId(),dto.getName(),dto.getValivation()));
    }

    @Override
    public boolean existGItem(String id) throws SQLException, ClassNotFoundException {
        return guaranteeItemDAO.exist(id);
    }

    @Override
    public boolean deleteGItem(String id) throws SQLException, ClassNotFoundException {
        return guaranteeItemDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return guaranteeItemDAO.generateNewID();
    }
}

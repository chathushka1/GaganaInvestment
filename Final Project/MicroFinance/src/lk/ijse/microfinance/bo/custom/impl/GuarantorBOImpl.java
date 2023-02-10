package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.GuarantorBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.GuarantorDAO;
import lk.ijse.microfinance.entity.Guarantor;
import lk.ijse.microfinance.dto.GuarantorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuarantorBOImpl implements GuarantorBO {
    GuarantorDAO guarantorDAO = (GuarantorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GUARANTOR);

    @Override
    public ArrayList<GuarantorDTO> gelAllGuarantor() throws SQLException, ClassNotFoundException {
        ArrayList<GuarantorDTO> allGuarantor = new ArrayList<>();
        ArrayList<Guarantor> allEntity = guarantorDAO.getAll();
        for(Guarantor guarantor : allEntity){
            allGuarantor.add(new GuarantorDTO(guarantor.getgID(),guarantor.getlID(),guarantor.getName(),guarantor.getAddress(),
                    guarantor.getNic(),guarantor.getTelephone()));
        }
        return allGuarantor;
    }

    @Override
    public boolean addGuarantor(GuarantorDTO dto) throws SQLException, ClassNotFoundException {
        return guarantorDAO.add(new Guarantor(dto.getgID(),dto.getlID(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getTelephone()));
    }

    @Override
    public boolean updateGuarantor(GuarantorDTO dto) throws SQLException, ClassNotFoundException {
        return guarantorDAO.updateData(new Guarantor(dto.getgID(),dto.getlID(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getTelephone()));
    }

    @Override
    public boolean existGuarantor(String id) throws SQLException, ClassNotFoundException {
        return guarantorDAO.exist(id);
    }

    @Override
    public boolean deleteGuarantor(String id) throws SQLException, ClassNotFoundException {
        return guarantorDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return guarantorDAO.generateNewID();
    }
}

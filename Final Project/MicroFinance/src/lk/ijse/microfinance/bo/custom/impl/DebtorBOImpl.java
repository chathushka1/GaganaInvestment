package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.DebtorBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.DebtorDAO;
import lk.ijse.microfinance.entity.Debtor;
import lk.ijse.microfinance.model.DebtorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class DebtorBOImpl implements DebtorBO {
    DebtorDAO debtorDAO = (DebtorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DEBTOR);


    @Override
    public ArrayList<DebtorDTO> gelAllDebtor() throws SQLException, ClassNotFoundException {
        ArrayList<DebtorDTO> allDebtor = new ArrayList<>();
        ArrayList<Debtor> allEntity = debtorDAO.getAll();
        for(Debtor debtor : allEntity){
            allDebtor.add(new DebtorDTO(debtor.getdID(),debtor.getName(),debtor.getAddress(),debtor.getNic(),debtor.getAmountDue(),
                    debtor.getTelephone(),debtor.geteID()));
        }
        return allDebtor;
    }

    @Override
    public boolean addDebtor(DebtorDTO dto) throws SQLException, ClassNotFoundException {
        return debtorDAO.add(new Debtor(dto.getdID(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getAmountDeu(),
                dto.getTelephone(),dto.getEmployeeId()));
    }

    @Override
    public boolean updateDebtor(DebtorDTO dto) throws SQLException, ClassNotFoundException {
        return debtorDAO.updateData(new Debtor(dto.getdID(),dto.getName(),dto.getAddress(),dto.getNic(),dto.getAmountDeu(),
                dto.getTelephone(),dto.getEmployeeId()));
    }

    @Override
    public boolean existDebtor(String id) throws SQLException, ClassNotFoundException {
        return debtorDAO.exist(id);
    }

    @Override
    public boolean deleteDebtor(String id) throws SQLException, ClassNotFoundException {
        return debtorDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return debtorDAO.generateNewID();
    }
}

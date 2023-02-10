package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.LoanBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.LoanDAO;
import lk.ijse.microfinance.entity.Loan;
import lk.ijse.microfinance.dto.LoanDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoanBOImpl implements LoanBO {
    LoanDAO loanDAO = (LoanDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOAN);
    //lID,dID,amount,loanDate,loanDueDate,period,percentage,monthlyPremium
    @Override
    public ArrayList<LoanDTO> gelAllLoan() throws SQLException, ClassNotFoundException {
        ArrayList<LoanDTO> allLoan = new ArrayList<>();
        ArrayList<Loan> allEntity = loanDAO.getAll();
        for(Loan loan : allEntity){
            allLoan.add(new LoanDTO(loan.getlID(),loan.getdID(),loan.getAmount(),loan.getLoanDate(),loan.getLoanDueDate(),
                    loan.getPeriod(),loan.getPercentage(),loan.getMonthlyPremium()));
        }
        return allLoan;
    }

    @Override
    public boolean addLoan(LoanDTO dto) throws SQLException, ClassNotFoundException {
        return loanDAO.add(new Loan(dto.getlID(),dto.getdID(),dto.getLoanAmount(),dto.getLoanDate(),dto.getLoanDueDate(),
                dto.getPeriod(),dto.getPercentage(),dto.getMonthlyPremium()));
    }

    @Override
    public boolean updateLoan(LoanDTO dto) throws SQLException, ClassNotFoundException {
        return loanDAO.updateData(new Loan(dto.getlID(),dto.getdID(),dto.getLoanAmount(),dto.getLoanDate(),dto.getLoanDueDate(),
                dto.getPeriod(),dto.getPercentage(),dto.getMonthlyPremium()));
    }

    @Override
    public boolean existLoan(String id) throws SQLException, ClassNotFoundException {
        return loanDAO.exist(id);
    }

    @Override
    public boolean deleteLoan(String id) throws SQLException, ClassNotFoundException {
        return loanDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return loanDAO.generateNewID();
    }
}

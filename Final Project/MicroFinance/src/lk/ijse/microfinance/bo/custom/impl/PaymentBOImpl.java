package lk.ijse.microfinance.bo.custom.impl;

import lk.ijse.microfinance.bo.custom.PaymentBO;
import lk.ijse.microfinance.dao.DAOFactory;
import lk.ijse.microfinance.dao.custom.PaymentDAO;
import lk.ijse.microfinance.entity.Debtor;
import lk.ijse.microfinance.entity.Payment;
import lk.ijse.microfinance.to.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public ArrayList<PaymentDTO> gelAllPayment() throws SQLException, ClassNotFoundException {
        //pID,lID,loanDate,amount,totalAmountDeu
        ArrayList<PaymentDTO> allPayment = new ArrayList<>();
        ArrayList<Payment> allEntity = paymentDAO.getAll();
        for(Payment payment : allEntity){
            allPayment.add(new PaymentDTO(payment.getpID(),payment.getlID(),payment.getLoanDate(),payment.getAmount(),
                    payment.getTotalAmountDeu()));
        }
        return allPayment;    }

    @Override
    public boolean addPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getpID(),dto.getlID(),dto.getLoanDate(),dto.getAmount(),dto.getTotalAmountDeu()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.updateData(new Payment(dto.getpID(),dto.getlID(),dto.getLoanDate(),dto.getAmount(),dto.getTotalAmountDeu()));
    }

    @Override
    public boolean existPayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.exist(id);
    }

    @Override
    public boolean deletePayment(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    @Override
    public String genaRateNewId() throws SQLException, ClassNotFoundException {
        return paymentDAO.generateNewID();
    }
}

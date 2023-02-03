package lk.ijse.microfinance.dao.custom;

import lk.ijse.microfinance.dao.CrudDAO;
import lk.ijse.microfinance.dao.SuperDAO;
import lk.ijse.microfinance.entity.Payment;

public interface PaymentDAO extends SuperDAO, CrudDAO<Payment> {
}
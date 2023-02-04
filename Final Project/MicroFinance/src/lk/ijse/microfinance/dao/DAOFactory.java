package lk.ijse.microfinance.dao;

import lk.ijse.microfinance.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        if(daoFactory==null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        DEBTOR,EMPLOYEE,GUARANTEEITEM,GUARANTOR,LOAN,PAYMENT
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case DEBTOR:
                return new DebtorDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case GUARANTEEITEM:
                return new GuaranteeItemDAOImpl();
            case GUARANTOR:
                return new GuarantorDAOImpl();
            case LOAN:
                return new LoanDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
        }
        return null;
    }
}

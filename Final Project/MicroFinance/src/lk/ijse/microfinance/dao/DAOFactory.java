package lk.ijse.microfinance.dao;

import lk.ijse.microfinance.dao.custom.impl.DebtorDAOImpl;

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
        }
        return null;
    }
}

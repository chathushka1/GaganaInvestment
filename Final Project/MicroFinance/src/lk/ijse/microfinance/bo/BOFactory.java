package lk.ijse.microfinance.bo;

import lk.ijse.microfinance.bo.custom.impl.*;
import lk.ijse.microfinance.dao.DAOFactory;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOTypes{
        DEBTORBO,EMPLOYEEBO,GUARANTEEITEMBO,GUARANTORBO,LOANBO,PAYMENTBO
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case DEBTORBO:
              return new DebtorBOImpl();
            case EMPLOYEEBO:
              return new EmployeeBOImpl();
            case GUARANTEEITEMBO:
              return new GuaranteeItemBOImpl();
            case GUARANTORBO:
              return new GuarantorBOImpl();
            case LOANBO:
              return new LoanBOImpl();
            case PAYMENTBO:
              return new PaymentBOImpl();
        }
        return null;

    }
}

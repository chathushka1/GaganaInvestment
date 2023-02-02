package lk.ijse.microfinance.bo;

import lk.ijse.microfinance.bo.custom.impl.DebtorBOImpl;
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
        DEBTORBO
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case DEBTORBO:
              return new DebtorBOImpl();
        }
        return null;

    }
}

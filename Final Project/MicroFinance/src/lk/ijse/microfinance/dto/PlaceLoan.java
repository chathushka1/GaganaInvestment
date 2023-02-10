package lk.ijse.microfinance.dto;

import java.util.ArrayList;

public class PlaceLoan {
    private String lid;
    private String paymentId;
    private ArrayList<LoanDetail> loanDetails = new ArrayList<>();

    public PlaceLoan(String lid, String paymentId, ArrayList<LoanDetail> loanDetails) {
        this.lid = lid;
        this.paymentId = paymentId;
        this.loanDetails = loanDetails;
    }

    public PlaceLoan() {
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public ArrayList<LoanDetail> getLoanDetails() {
        return loanDetails;
    }

    public void setLoanDetails(ArrayList<LoanDetail> loanDetails) {
        this.loanDetails = loanDetails;
    }

    @Override
    public String toString() {
        return "PlaceLoan{" +
                "lid='" + lid + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", loanDetails=" + loanDetails +
                '}';
    }
}

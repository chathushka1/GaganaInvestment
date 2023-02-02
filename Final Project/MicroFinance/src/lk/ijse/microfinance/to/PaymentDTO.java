package lk.ijse.microfinance.to;

public class PaymentDTO {
    private String pID;
    private String lID;
    private String loanDate;
    private double amount;
    private double totalAmountDeu;

    public PaymentDTO() {
    }

    public PaymentDTO(String pID, String lID, String loanDate, double amount, double totalAmountDeu) {
        this.pID = pID;
        this.lID = lID;
        this.loanDate = loanDate;
        this.amount = amount;
        this.totalAmountDeu = totalAmountDeu;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getlID() {
        return lID;
    }

    public void setlID(String lID) {
        this.lID = lID;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmountDeu() {
        return totalAmountDeu;
    }

    public void setTotalAmountDeu(double totalAmountDeu) {
        this.totalAmountDeu = totalAmountDeu;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "pID='" + pID + '\'' +
                ", lID='" + lID + '\'' +
                ", loanDate='" + loanDate + '\'' +
                ", amount=" + amount +
                ", totalAmountDeu=" + totalAmountDeu +
                '}';
    }
}

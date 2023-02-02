package lk.ijse.microfinance.view.tm;

public class PaymentAddTm {
    private String id;
    private String loanId;
    private String loanDate;
    private double amount;
    private double totalAmountDeu;

    public PaymentAddTm() {
    }

    public PaymentAddTm(String id, String loanId, String loanDate, double amount, double totalAmountDeu) {
        this.id = id;
        this.loanId = loanId;
        this.loanDate = loanDate;
        this.amount = amount;
        this.totalAmountDeu = totalAmountDeu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
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
        return "PaymentAddTm{" +
                "id='" + id + '\'' +
                ", loanId='" + loanId + '\'' +
                ", loanDate='" + loanDate + '\'' +
                ", amount=" + amount +
                ", totalAmountDeu=" + totalAmountDeu +
                '}';
    }
}

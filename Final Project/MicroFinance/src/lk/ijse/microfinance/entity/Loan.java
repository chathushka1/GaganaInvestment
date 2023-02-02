package lk.ijse.microfinance.entity;

public class Loan {
    private String lID;
    private String dID;
    private double amount;
    private String loanDate;
    private String loanDueDate;
    private int period ;
    private double percentage;
    private double monthlyPremium ;

    public Loan() {
    }

    public Loan(String lID, String dID, double amount, String loanDate, String loanDueDate, int period, double percentage, double monthlyPremium) {
        this.lID = lID;
        this.dID = dID;
        this.amount = amount;
        this.loanDate = loanDate;
        this.loanDueDate = loanDueDate;
        this.period = period;
        this.percentage = percentage;
        this.monthlyPremium = monthlyPremium;
    }

    public String getlID() {
        return lID;
    }

    public void setlID(String lID) {
        this.lID = lID;
    }

    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanDueDate() {
        return loanDueDate;
    }

    public void setLoanDueDate(String loanDueDate) {
        this.loanDueDate = loanDueDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getMonthlyPremium() {
        return monthlyPremium;
    }

    public void setMonthlyPremium(double monthlyPremium) {
        this.monthlyPremium = monthlyPremium;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "lID='" + lID + '\'' +
                ", dID='" + dID + '\'' +
                ", amount=" + amount +
                ", loanDate='" + loanDate + '\'' +
                ", loanDueDate='" + loanDueDate + '\'' +
                ", period=" + period +
                ", percentage=" + percentage +
                ", monthlyPremium=" + monthlyPremium +
                '}';
    }
}

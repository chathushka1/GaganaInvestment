package lk.ijse.microfinance.to;

public class LoanDTO {
    private String lID;
    private String dID;
    private double loanAmount;
    private String loanDate;
    private String loanDueDate;
    private int period ;
    private double percentage;
    private double monthlyPremium ;

    public LoanDTO() {
    }

    public LoanDTO(String lID, String dID, double loanAmount, String loanDate, String loanDueDate, int period, double percentage, double monthlyPremium) {
        this.lID = lID;
        this.dID = dID;
        this.loanAmount = loanAmount;
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

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
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
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanDueDate='" + loanDueDate + '\'' +
                ", period=" + period +
                ", percentage=" + percentage +
                ", monthlyPremium=" + monthlyPremium +
                '}';
    }
}

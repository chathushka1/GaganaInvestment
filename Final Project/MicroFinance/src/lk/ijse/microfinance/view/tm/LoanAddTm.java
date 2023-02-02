package lk.ijse.microfinance.view.tm;

public class LoanAddTm {
    private String id;
    private String idDebtor;
    private double amount;
    private String loanDate;
    private String loanDueDate;
    private int period ;
    private double percentage;
    private double monthlyPremium ;

    public LoanAddTm() {
    }

    public LoanAddTm(String id, String idDebtor, double amount, String loanDate, String loanDueDate, int period, double percentage, double monthlyPremium) {
        this.id = id;
        this.idDebtor = idDebtor;
        this.amount = amount;
        this.loanDate = loanDate;
        this.loanDueDate = loanDueDate;
        this.period = period;
        this.percentage = percentage;
        this.monthlyPremium = monthlyPremium;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(String idDebtor) {
        this.idDebtor = idDebtor;
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
        return "LoanAddTm{" +
                "id='" + id + '\'' +
                ", idDebtor='" + idDebtor + '\'' +
                ", amount=" + amount +
                ", loanDate='" + loanDate + '\'' +
                ", loanDueDate='" + loanDueDate + '\'' +
                ", period=" + period +
                ", percentage=" + percentage +
                ", monthlyPremium=" + monthlyPremium +
                '}';
    }
}

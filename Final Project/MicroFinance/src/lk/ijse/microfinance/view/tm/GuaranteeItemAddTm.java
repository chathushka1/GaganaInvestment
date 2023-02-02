package lk.ijse.microfinance.view.tm;

public class GuaranteeItemAddTm {
    private String id;
    private String loanId ;
    private String name;
    private double valivation ;

    public GuaranteeItemAddTm() {
    }

    public GuaranteeItemAddTm(String id, String loanId, String name, double valivation) {
        this.id = id;
        this.loanId = loanId;
        this.name = name;
        this.valivation = valivation;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValivation() {
        return valivation;
    }

    public void setValivation(double valivation) {
        this.valivation = valivation;
    }

    @Override
    public String toString() {
        return "GuaranteeItemAddTm{" +
                "id='" + id + '\'' +
                ", loanId='" + loanId + '\'' +
                ", name='" + name + '\'' +
                ", valivation=" + valivation +
                '}';
    }
}

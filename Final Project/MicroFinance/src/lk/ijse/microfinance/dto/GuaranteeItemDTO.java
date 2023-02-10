package lk.ijse.microfinance.dto;

public class GuaranteeItemDTO {
    private String gItemID ;
    private String loanId ;
    private String name;
    private double valivation ;

    public GuaranteeItemDTO() {
    }

    public GuaranteeItemDTO(String gItemID, String loanId, String name, double valivation) {
        this.gItemID = gItemID;
        this.loanId = loanId;
        this.name = name;
        this.valivation = valivation;
    }

    public String getgItemID() {
        return gItemID;
    }

    public void setgItemID(String gItemID) {
        this.gItemID = gItemID;
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
        return "GuaranteeItem{" +
                "gItemID='" + gItemID + '\'' +
                ", loanId='" + loanId + '\'' +
                ", name='" + name + '\'' +
                ", valivation=" + valivation +
                '}';
    }
}

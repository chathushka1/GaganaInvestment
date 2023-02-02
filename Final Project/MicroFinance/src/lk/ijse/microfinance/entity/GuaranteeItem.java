package lk.ijse.microfinance.entity;

public class GuaranteeItem {
    private String gItemID ;
    private String lID ;
    private String name;
    private double valivation ;

    public GuaranteeItem() {
    }

    public GuaranteeItem(String gItemID, String lID, String name, double valivation) {
        this.gItemID = gItemID;
        this.lID = lID;
        this.name = name;
        this.valivation = valivation;
    }

    public String getgItemID() {
        return gItemID;
    }

    public void setgItemID(String gItemID) {
        this.gItemID = gItemID;
    }

    public String getlID() {
        return lID;
    }

    public void setlID(String lID) {
        this.lID = lID;
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
                ", lID='" + lID + '\'' +
                ", name='" + name + '\'' +
                ", valivation=" + valivation +
                '}';
    }
}

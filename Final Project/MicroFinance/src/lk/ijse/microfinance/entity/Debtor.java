package lk.ijse.microfinance.entity;

public class Debtor {
    private String dID;
    private String name;
    private String address;
    private String nic;
    private double amountDue;
    private String telephone;
    private String eID;

    public Debtor() {
    }

    public Debtor(String dID, String name, String address, String nic, double amountDue, String telephone, String eID) {
        this.dID = dID;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.amountDue = amountDue;
        this.telephone = telephone;
        this.eID = eID;
    }

    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    @Override
    public String toString() {
        return "Debtor{" +
                "dID='" + dID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", amountDue=" + amountDue +
                ", telephone='" + telephone + '\'' +
                ", eID='" + eID + '\'' +
                '}';
    }
}

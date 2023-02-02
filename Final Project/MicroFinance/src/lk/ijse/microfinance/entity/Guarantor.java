package lk.ijse.microfinance.entity;

public class Guarantor {
    private String gID ;
    private String lID ;
    private String name;
    private String address;
    private String nic ;
    private String telephone ;

    public Guarantor() {
    }

    public Guarantor(String gID, String lID, String name, String address, String nic, String telephone) {
        this.gID = gID;
        this.lID = lID;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
    }

    public String getgID() {
        return gID;
    }

    public void setgID(String gID) {
        this.gID = gID;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Guarantor{" +
                "gID='" + gID + '\'' +
                ", lID='" + lID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

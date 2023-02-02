package lk.ijse.microfinance.view.tm;

public class GuarantorAddTm {
    private String id ;
    private String loanId ;
    private String name;
    private String address;
    private String nic ;
    private String telephone ;

    public GuarantorAddTm() {
    }

    public GuarantorAddTm(String id, String loanId, String name, String address, String nic, String telephone) {
        this.id = id;
        this.loanId = loanId;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.telephone = telephone;
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
        return "GuarantorAddTm{" +
                "id='" + id + '\'' +
                ", loanId='" + loanId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

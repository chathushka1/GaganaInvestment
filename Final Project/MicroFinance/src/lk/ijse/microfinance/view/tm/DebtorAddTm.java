package lk.ijse.microfinance.view.tm;

public class DebtorAddTm {
    private String id;
    private String name;
    private String address;
    private String nic;
    private double amountDeu;
    private String telephone;
    private String employeeId;

    public DebtorAddTm() {
    }

    public DebtorAddTm(String id, String name, String address, String nic, double amountDeu, String telephone, String employeeId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.amountDeu = amountDeu;
        this.telephone = telephone;
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getAmountDeu() {
        return amountDeu;
    }

    public void setAmountDeu(double amountDeu) {
        this.amountDeu = amountDeu;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "DebtorAddTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", amountDeu=" + amountDeu +
                ", telephone='" + telephone + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}

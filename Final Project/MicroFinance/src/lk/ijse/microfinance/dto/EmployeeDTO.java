package lk.ijse.microfinance.dto;

public class EmployeeDTO {
 private String eID;
 private String name;
 private String address;
 private String nic;
 private String position;
 private String telephone;

 public EmployeeDTO() {
 }

 public EmployeeDTO(String eID, String name, String address, String nic, String position, String telephone) {
  this.eID = eID;
  this.name = name;
  this.address = address;
  this.nic = nic;
  this.position = position;
  this.telephone = telephone;
 }

 public String geteID() {
  return eID;
 }

 public void seteID(String eID) {
  this.eID = eID;
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

 public String getPosition() {
  return position;
 }

 public void setPosition(String position) {
  this.position = position;
 }

 public String getTelephone() {
  return telephone;
 }

 public void setTelephone(String telephone) {
  this.telephone = telephone;
 }

 @Override
 public String toString() {
  return "Employee{" +
          "eID='" + eID + '\'' +
          ", name='" + name + '\'' +
          ", address='" + address + '\'' +
          ", nic='" + nic + '\'' +
          ", position='" + position + '\'' +
          ", telephone='" + telephone + '\'' +
          '}';
 }
}

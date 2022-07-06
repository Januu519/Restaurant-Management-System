package model;

public class Customer {
    private String Customerid;
    private String Customername;
    private String Customernic;
    private String Customeraddress;
    private String Customeremail;
    private String Customercontact;

    public Customer() {
    }

    public Customer(String customerid, String customername, String customernic, String customeraddress, String customeremail, String customercontact) {
        Customerid = customerid;
        Customername = customername;
        Customernic = customernic;
        Customeraddress = customeraddress;
        Customeremail = customeremail;
        Customercontact = customercontact;
    }

    public String getCustomerid() {
        return Customerid;
    }

    public void setCustomerid(String customerid) {
        Customerid = customerid;
    }

    public String getCustomername() {
        return Customername;
    }

    public void setCustomername(String customername) {
        Customername = customername;
    }

    public String getCustomernic() {
        return Customernic;
    }

    public void setCustomernic(String customernic) {
        Customernic = customernic;
    }

    public String getCustomeraddress() {
        return Customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        Customeraddress = customeraddress;
    }

    public String getCustomeremail() {
        return Customeremail;
    }

    public void setCustomeremail(String customeremail) {
        Customeremail = customeremail;
    }

    public String getCustomercontact() {
        return Customercontact;
    }

    public void setCustomercontact(String customercontact) {
        Customercontact = customercontact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Customerid='" + Customerid + '\'' +
                ", Customername='" + Customername + '\'' +
                ", Customernic='" + Customernic + '\'' +
                ", Customeraddress='" + Customeraddress + '\'' +
                ", Customeremail='" + Customeremail + '\'' +
                ", Customercontact='" + Customercontact + '\'' +
                '}';
    }
}

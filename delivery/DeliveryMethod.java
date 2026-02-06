package delivery;

public abstract class DeliveryMethod {
    protected String city;
    protected String branchNumber;

    public DeliveryMethod(String city, String branchNumber) {
        this.city = city;
        this.branchNumber = branchNumber;
    }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getBranchNumber() { return branchNumber; }

    public void setBranchNumber(String branchNumber) { this.branchNumber = branchNumber; }

    public abstract void deliver(String customerName);
}
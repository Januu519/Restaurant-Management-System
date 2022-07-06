package model;

public class Order {
    private String orderId;
    private String foodId;
    private String foodName;
    private String customerId;
    private String customerName;
    private String orderType;
    private String dateTime;
    private String qty;

    public Order() {
    }

    public Order(String orderId, String foodId, String foodName, String customerId, String customerName, String orderType, String dateTime, String qty) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderType = orderType;
        this.dateTime = dateTime;
        this.qty = qty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderType='" + orderType + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}

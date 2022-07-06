package model;

public class Food {
    private String foodId;
    private String foodName;
    private String foodType;
    private String foodSize;
    private String foodPrice;

    public Food() {
    }

    public Food(String foodId, String foodName, String foodType, String foodSize, String foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodSize = foodSize;
        this.foodPrice = foodPrice;
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

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodSize() {
        return foodSize;
    }

    public void setFoodSize(String foodSize) {
        this.foodSize = foodSize;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId='" + foodId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodSize='" + foodSize + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                '}';
    }
}

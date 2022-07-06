package controller.dashboardControllers;

import Util.CrudUtil;
import Util.Utill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Food;
import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DetailsControl {
    public AnchorPane WorkingContex;
    public LineChart lineChart;
    public TableView tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerNic;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerEmail;
    public TableColumn colCustomerContact;
    public TableView tblFood;
    public TableColumn colFoodID;
    public TableColumn colFoodName;
    public TableColumn colFoodType;
    public TableColumn colFoodSize;
    public TableColumn colFoodPrice;
    public TableView ordersTable;
    public TableColumn colOrderId;
    public TableColumn colOrderFoodId;
    public TableColumn colOrderFoodName;
    public TableColumn colOrderCustomerId;
    public TableColumn colOrderCustomerName;
    public TableColumn colOrderType;
    public TableColumn colOrderDateTime;
    public TableColumn colOrderQty;


    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllTable();
        loadIncome();

    }

    private void loadIncome() throws SQLException, ClassNotFoundException {

        XYChart.Series income = new XYChart.Series();


        String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        String date_time =dtf.format(now);

        for (int i = 0; i < month.length; i++) {

            if (i<10){

                income.getData().add(new XYChart.Data(month[i], Utill.CuIds(date_time+"-0"+(i+1))));
            }else {

                income.getData().add(new XYChart.Data(month[i],Utill.CuIds(date_time+"-"+(i+1))));
            }

        }

        income.setName("Monthly Income");

        lineChart.getData().add(income);

    }


    private void loadAllTable() throws SQLException, ClassNotFoundException {
        ObservableList<Order> orderTable= FXCollections.observableArrayList();
        ResultSet order = CrudUtil.execute("SELECT * FROM orders");
        while (order.next()){
            orderTable.add(
                    new Order(
                            order.getString(1),order.getString(2),order.getString(3),
                            order.getString(4),order.getString(5),order.getString(6),
                            order.getString(7),order.getString(8)
                    )
            );
        }
        ordersTable.setItems(orderTable);

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderFoodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        colOrderFoodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        colOrderCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colOrderCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colOrderType.setCellValueFactory(new PropertyValueFactory<>("orderType"));
        colOrderDateTime.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("qty"));


        ObservableList<Food> foodTable= FXCollections.observableArrayList();

        ResultSet food=  CrudUtil.execute("SELECT * FROM food");

        while (food.next()){
            foodTable.add(
                    new Food(food.getString(1),food.getString(2),food.getString(3),food.getString(4),food.getString(5))
            );
        }

        tblFood.setItems(foodTable);

        colFoodID.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        colFoodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        colFoodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        colFoodSize.setCellValueFactory(new PropertyValueFactory<>("foodSize"));
        colFoodPrice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));


        ObservableList<Customer> customerTable= FXCollections.observableArrayList();

        ResultSet customer = CrudUtil.execute("SELECT * FROM customer");

        while (customer.next()){
            customerTable.add(
                    new Customer(customer.getString(1),customer.getString(2),customer.getString(3),customer.getString(4),
                            customer.getString(5),customer.getString(6))
            );
        }
        tblCustomer.setItems(customerTable);

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customerid"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Customername"));
        colCustomerNic.setCellValueFactory(new PropertyValueFactory<>("Customernic"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Customeraddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("Customeremail"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("Customercontact"));


    }

}

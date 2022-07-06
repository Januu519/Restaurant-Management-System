package controller.dashboardControllers;

import Util.CrudUtil;
import Util.Utill;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Food;
import model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ManageController {

    public AnchorPane WorkingContex;
    public JFXComboBox cmbOrderFoodID;
    public JFXTextField txtOrderFoodName;
    public JFXComboBox cmbOrderCustomerID;
    public JFXTextField txtOrderQty;
    public JFXComboBox cmbOrderstype;
    public JFXTextField txtOrderCustomerName;


    public TableView ordersTable;
    public TableColumn colOrderId;
    public TableColumn colOrderFoodId;
    public TableColumn colOrderFoodName;
    public TableColumn colOrderCustomerId;
    public TableColumn colOrderCustomerName;
    public TableColumn colOrderType;
    public TableColumn colOrderDateTime;
    public TableColumn colOrderQty;
    public JFXTextField txtFoodName;

    public JFXComboBox cmbFoodType;
    public JFXComboBox cmbFoodSize;
    public JFXComboBox cmbFoodId;
    public JFXTextField txtprice;
    public TableView tblFood;
    public TableColumn colFoodID;
    public TableColumn colFoodName;
    public TableColumn colFoodType;
    public TableColumn colFoodSize;
    public TableColumn colFoodPrice;
    public JFXComboBox cmbCustomer;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerEmail;
    public JFXTextField txtCustomerNIC;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerContact;
    public TableView tblCustomer;
    public TableColumn colCustomerID;
    public TableColumn colCustomerName;
    public TableColumn colCustomerEmail;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerNic;
    public TableColumn colCustomerContact;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadAllComboBox();
        loadAllTable();

        cmbFoodId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->  {
            try {
                if (newValue==" "){
                    txtFoodName.clear();
                    txtprice.clear();
                    cmbFoodType.setValue(null);
                    cmbFoodSize.setValue(null);
                }
                foodSearch((String)newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbOrderFoodID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchOrderFood((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbOrderCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                searchOrderCustomer((String) newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                customerSearch((String) newValue);
                if (newValue==" "){
                    txtCustomerName.clear();
                    txtCustomerNIC.clear();
                    txtCustomerAddress.clear();
                    txtCustomerEmail.clear();
                    txtCustomerContact.clear();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void customerSearch(String id) throws SQLException, ClassNotFoundException {




        ResultSet resultSet =CrudUtil.execute("SELECT * FROM customer WHERE customer_id=?",id);

        while (resultSet.next()){
            txtCustomerName.setText(resultSet.getString(2));
            txtCustomerNIC.setText(resultSet.getString(3));
            txtCustomerAddress.setText(resultSet.getString(4));
            txtCustomerEmail.setText(resultSet.getString(5));
            txtCustomerContact.setText(resultSet.getString(6));
        }
    }

    private void foodSearch(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM food WHERE food_id=?",id);

        while (resultSet.next()){
            txtFoodName.setText(resultSet.getString(2));
            cmbFoodType.setValue(resultSet.getString(3));
            cmbFoodSize.setValue(resultSet.getString(4));
            txtprice.setText(resultSet.getString(5));
        }
    }

    private void loadAllTable() throws SQLException, ClassNotFoundException {
         ObservableList<Order> orderTable= FXCollections.observableArrayList();
        ResultSet order =CrudUtil.execute("SELECT * FROM orders");
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

    private void searchOrderCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT customer_name FROM customer WHERE customer_id=?",id);

        while (resultSet.next()){
            txtOrderCustomerName.setText(resultSet.getString(1));
        }
    }

    private void searchOrderFood(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT food_name FROM food WHERE food_id=?",id);

        while (resultSet.next()){
            txtOrderFoodName.setText(resultSet.getString(1));
        }


    }

    private void loadAllComboBox() throws SQLException, ClassNotFoundException {
        ObservableList foodID= FXCollections.observableArrayList();
        ObservableList addFoodId= FXCollections.observableArrayList();
        ResultSet food= CrudUtil.execute("SELECT food_id FROM food");
        while (food.next()){
            foodID.add(
                    food.getString(1)
            );
            addFoodId.add(
                    food.getString(1)
            );

        }
        cmbOrderFoodID.setItems(foodID);
        addFoodId.add(" ");
        cmbFoodId.setItems(addFoodId);


        ObservableList customerID= FXCollections.observableArrayList();
        ResultSet customer= CrudUtil.execute("SELECT customer_id FROM customer");
        while (customer.next()){
            customerID.add(
                    customer.getString(1)
            );
        }
        cmbOrderCustomerID.setItems(customerID);

        ObservableList type= FXCollections.observableArrayList();
        type.add("To eat");
        type.add("To carry");

        cmbOrderstype.setItems(type);


        ObservableList foodsType= FXCollections.observableArrayList();

        foodsType.add("chicken");
        foodsType.add("vegetable");
        foodsType.add("see food");
        foodsType.add("pork");
        foodsType.add("dessert");

        cmbFoodType.setItems(foodsType);

        ObservableList foodsSize= FXCollections.observableArrayList();

        foodsSize.add("half");
        foodsSize.add("full");
        foodsSize.add("large");
        foodsSize.add("small");

        cmbFoodSize.setItems(foodsSize);

        ObservableList customers= FXCollections.observableArrayList();

        ResultSet cid = CrudUtil.execute("SELECT * FROM customer");

        while (cid.next()){

            customers.add(cid.getString(1));
        }
        customers.add(" ");
        cmbCustomer.setItems(customers);

    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {

        if (cmbOrderFoodID.getValue()!=null){
            if (cmbOrderCustomerID.getValue()!=null){
                if (cmbOrderstype.getValue()!=null){
                    if (txtOrderQty.getText().matches("^[0-9]*$")){
                        String orderid=Utill.iDIncrement("Orders","order_id");
                        String foodid= (String) cmbOrderFoodID.getValue();
                        String foodname=txtOrderFoodName.getText();
                        String customerid= (String) cmbOrderCustomerID.getValue();
                        String customername=txtOrderCustomerName.getText();
                        String ordertype= (String) cmbOrderstype.getValue();
                        int qty= Integer.parseInt(txtOrderQty.getText());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        String date_time=dtf.format(now);
                        double Price=0;
                        ResultSet resultSet = CrudUtil.execute("SELECT food_price FROM food WHERE food_id=?",foodid);

                        while (resultSet.next()){
                            Price=resultSet.getDouble(1);
                        }




                        if (CrudUtil.execute("insert into orders values(?,?,?,?,?,?,?,?,?)",orderid,foodid,foodname,customerid,customername,ordertype,date_time,qty,Price)){
                            Utill.notifycateconfrm("Order Has Been placed","ORDER CONFIRM");

                            cmbOrderFoodID.setValue(null);
                            txtOrderFoodName.clear();
                            cmbOrderCustomerID.setValue(null);
                            txtOrderCustomerName.clear();
                            cmbOrderstype.setValue(null);
                            txtOrderQty.clear();

                            loadAllTable();



                            HashMap<String, Object> reserve = new HashMap<>();

                            String qtyonee= String.valueOf(qty);
                            String priceee= String.valueOf(Price);

                            reserve.put("foodName",foodname);
                            reserve.put("customerName",customername);
                            reserve.put("type",ordertype);
                            reserve.put("qty",qtyonee);
                            reserve.put("price",priceee);

                            JasperReport compileReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/hasi.jasper"));

                            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, reserve, new JREmptyDataSource(1));

                            JasperViewer.viewReport(jasperPrint, false);


                        }


                    }else {
                        if (txtOrderQty.getText().equals("")){
                            Utill.notifycate("First Fill Qty","ERROR");
                        }else {
                            Utill.notifycate("NOT Valid   >'"+txtOrderQty.getText()+"'<   Please Enter Valid Qty","ERROR");
                        }
                    }
                }else {
                    Utill.notifycate("First Select Type","ERROR");
                }
            }else {
                Utill.notifycate("First Select Customer","ERROR");
            }
        }else {
            Utill.notifycate("First Select Food","ERROR");
        }

    }

    public void foodAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbFoodId.getValue()==null || cmbFoodId.getValue().equals(" ")){
            if (txtFoodName.getText().matches("^[A-z ]*$")){
                if (cmbFoodType.getValue()!=null){
                    if (cmbFoodSize.getValue()!=null){
                        if (txtprice.getText().matches("^[0-9.]*$")){

                            String foodid=Utill.iDIncrement("Food","food_id");
                            String foodname=txtFoodName.getText();
                            String foodType= (String) cmbFoodType.getValue();
                            String foodsize= (String) cmbFoodSize.getValue();
                            double foodprice= Double.parseDouble(txtprice.getText());



                            if (CrudUtil.execute("insert into food values (?,?,?,?,?)",foodid,foodname,foodType,foodsize,foodprice)){
                                loadAllComboBox();
                                loadAllTable();
                                Utill.notifycateconfrm("food has been added","ADDED");

                                cmbFoodId.setValue(null);
                                txtFoodName.clear();
                                cmbFoodType.setValue(null);
                                cmbFoodSize.setValue(null);
                                txtprice.clear();



                            }



                        }else {
                            if (txtprice.getText().equals("")){
                                Utill.notifycate("First Fill Price","ERROR");
                            }else {
                                Utill.notifycate("NOT Valid   >'"+txtprice.getText()+"'<   Please Enter Valid Price","ERROR");
                            }
                        }
                    }else {
                        Utill.notifycate("First Select Size","ERROR");
                    }
                }else {
                    Utill.notifycate("First Select Type","ERROR");
                }
            }else{
                if (txtFoodName.getText().equals("")){
                    Utill.notifycate("First Fill name","ERROR");
                }else {
                    Utill.notifycate("NOT Valid   >'"+txtFoodName.getText()+"'<   Please Enter Valid name","ERROR");
                }
            }
        }else {
            Utill.notifycate("Can't Add","ERROR");
        }
    }

    public void foodDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (cmbFoodId.getValue()!=null || cmbFoodId.getValue()!=" "){

            String id = (String) cmbFoodId.getValue();

            if (CrudUtil.execute("DELETE FROM food where food_id=?",id)){
                Utill.notifycateconfrm("food has been Deleted","DELETED");
                loadAllComboBox();
                loadAllTable();
                cmbFoodId.setValue(null);
                txtFoodName.clear();
                cmbFoodType.setValue(null);
                cmbFoodSize.setValue(null);
                txtprice.clear();
            }

        }

    }

    public void foodModifyOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbFoodId.getValue()!=null){
            if (txtFoodName.getText().matches("^[A-z ]*$")){
                if (cmbFoodType.getValue()!=null){
                    if (cmbFoodSize.getValue()!=null){
                        if (txtprice.getText().matches("^[0-9.]*$")){

                            String foodid= (String) cmbFoodId.getValue();
                            String foodname=txtFoodName.getText();
                            String foodType= (String) cmbFoodType.getValue();
                            String foodsize= (String) cmbFoodSize.getValue();
                            double foodprice= Double.parseDouble(txtprice.getText());



                            if (CrudUtil.execute("UPDATE food SET food_name=?,food_type=?,food_size=?,food_price=? WHERE food_id=?",foodname,foodType,foodsize,foodprice,foodid)){
                                loadAllTable();
                                Utill.notifycateconfrm("food has been updated","UPDATED");

                                cmbFoodId.setValue(null);
                                txtFoodName.clear();
                                cmbFoodType.setValue(null);
                                cmbFoodSize.setValue(null);
                                txtprice.clear();

                            }

                        }else {
                            if (txtprice.getText().equals("")){
                                Utill.notifycate("First Fill Price","ERROR");
                            }else {
                                Utill.notifycate("NOT Valid   >'"+txtprice.getText()+"'<   Please Enter Valid Price","ERROR");
                            }
                        }
                    }else {
                        Utill.notifycate("First Select Size","ERROR");
                    }
                }else {
                    Utill.notifycate("First Select Type","ERROR");
                }
            }else{
                if (txtFoodName.getText().equals("")){
                    Utill.notifycate("First Fill name","ERROR");
                }else {
                    Utill.notifycate("NOT Valid   >'"+txtFoodName.getText()+"'<   Please Enter Valid name","ERROR");
                }
            }
        }else {
            Utill.notifycate("Can't Modify","ERROR");
        }
    }


    public void addCustomerOnaction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbCustomer.getValue()==null || cmbCustomer.getValue().equals(" ")){
            if (txtCustomerName.getText().matches("^[A-Z][a-z]*[ ][A-Z][a-z]*$")){
                if (txtCustomerNIC.getText().matches("^([0-9]{9}[V]|[0-9]{12})$")){
                    if (txtCustomerAddress.getText().matches("^[A-z ]*$")){
                        if (txtCustomerEmail.getText().matches("^([A-z\\d.]{3,})@(gmail|yahoo|Outlook|Inbox|iCloud|Mail|AOL|Zoho)(.com|.co.uk)$")){
                            if (txtCustomerContact.getText().matches("^(\\+|0)(94|[1-9]{2,3})(-| |)([0-9]{7}|[0-9]{2} [0-9]{7})$")){

                            String id = Utill.iDIncrement("Customer","customer_id");
                            String name = txtCustomerName.getText();
                            String nic = txtCustomerNIC.getText();
                            String address = txtCustomerAddress.getText();
                            String email = txtCustomerEmail.getText();
                            String Contact = txtCustomerContact.getText();


                            if (CrudUtil.execute("insert into customer values (?,?,?,?,?,?)",id,name,nic,address,email,Contact)){
                                Utill.notifycateconfrm("Customer has been added","ADDED");
                                loadAllComboBox();
                                loadAllTable();

                                cmbCustomer.setValue(null);
                                txtCustomerName.clear();
                                txtCustomerNIC.clear();
                                txtCustomerAddress.clear();
                                txtCustomerEmail.clear();
                                txtCustomerContact.clear();
                            }


                        }else {
                            if (txtCustomerContact.getText().equals("")){
                                Utill.notifycate("First Fill Phone","ERROR");
                            }else {
                                Utill.notifycate("NOT Valid   >'"+txtCustomerContact.getText()+"'<   Please Enter Valid Phone","ERROR");
                            }
                        }
                    }else {
                        if (txtCustomerEmail.getText().equals("")){
                            Utill.notifycate("First Fill Email","ERROR");
                        }else {
                            Utill.notifycate("NOT Valid   >'"+txtCustomerEmail.getText()+"'<   Please Enter Valid Email","ERROR");
                        }
                    }
                     }else {
                        if (txtCustomerAddress.getText().equals("")){
                            Utill.notifycate("First Fill NIC","ERROR");
                        }else {
                            Utill.notifycate("NOT Valid   >'"+txtCustomerAddress.getText()+"'<   Please Enter Valid NIC","ERROR");
                        }
                    }




            }else {
                if (txtCustomerNIC.getText().equals("")){
                    Utill.notifycate("First Fill NIC","ERROR");
                }else {
                    Utill.notifycate("NOT Valid   >'"+txtCustomerNIC.getText()+"'<   Please Enter Valid NIC","ERROR");
                }
            }
        }
        else
        {
            if (txtCustomerName.getText().equals("")){
                Utill.notifycate("First Fill Name","ERROR");
            }else {
                Utill.notifycate("NOT Valid   >'"+txtCustomerName.getText()+"'<   Please Enter Valid Name","ERROR");
            }
        }



    }else {
            Utill.notifycate("Can't Add","ERROR");
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        if (cmbCustomer.getValue()!=null || cmbCustomer.getValue()!=" "){

            String id = (String) cmbCustomer.getValue();

            if (CrudUtil.execute("DELETE FROM customer where customer_id=?",id)){
                Utill.notifycateconfrm("customer has been Deleted","DELETED");
                loadAllComboBox();
                loadAllTable();
                cmbCustomer.setValue(null);
                txtCustomerName.clear();
                txtCustomerNIC.clear();
                txtCustomerAddress.clear();
                txtCustomerEmail.clear();
                txtCustomerContact.clear();

            }

        }

    }

    public void modifyCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (cmbCustomer.getValue()!=null || cmbCustomer.getValue()!=" "){
            if (txtCustomerName.getText().matches("^[A-Z][a-z]*[ ][A-Z][a-z]*$")){
                if (txtCustomerNIC.getText().matches("^([0-9]{9}[V]|[0-9]{12})$")){
                    if (txtCustomerAddress.getText().matches("^[A-z ]*$")){
                        if (txtCustomerEmail.getText().matches("^([A-z\\d.]{3,})@(gmail|yahoo|Outlook|Inbox|iCloud|Mail|AOL|Zoho)(.com|.co.uk)$")){
                            if (txtCustomerContact.getText().matches("^(\\+|0)(94|[1-9]{2,3})(-| |)([0-9]{7}|[0-9]{2} [0-9]{7})$")){

                                String id = (String) cmbCustomer.getValue();
                                String name = txtCustomerName.getText();
                                String nic = txtCustomerNIC.getText();
                                String address = txtCustomerAddress.getText();
                                String email = txtCustomerEmail.getText();
                                String Contact = txtCustomerContact.getText();


                                if (CrudUtil.execute("UPDATE Customer SET customer_name=?,NiC=?,address=?,Email=?,contact=? WHERE customer_id=?",name,nic,address,email,Contact,id)){
                                    Utill.notifycateconfrm("Customer has been Updated","Updated");
                                    loadAllComboBox();
                                    loadAllTable();

                                    cmbCustomer.setValue(null);
                                    txtCustomerName.clear();
                                    txtCustomerNIC.clear();
                                    txtCustomerAddress.clear();
                                    txtCustomerEmail.clear();
                                    txtCustomerContact.clear();
                                }


                            }else {
                                if (txtCustomerContact.getText().equals("")){
                                    Utill.notifycate("First Fill Phone","ERROR");
                                }else {
                                    Utill.notifycate("NOT Valid   >'"+txtCustomerContact.getText()+"'<   Please Enter Valid Phone","ERROR");
                                }
                            }
                        }else {
                            if (txtCustomerEmail.getText().equals("")){
                                Utill.notifycate("First Fill Email","ERROR");
                            }else {
                                Utill.notifycate("NOT Valid   >'"+txtCustomerEmail.getText()+"'<   Please Enter Valid Email","ERROR");
                            }
                        }
                    }else {
                        if (txtCustomerAddress.getText().equals("")){
                            Utill.notifycate("First Fill NIC","ERROR");
                        }else {
                            Utill.notifycate("NOT Valid   >'"+txtCustomerAddress.getText()+"'<   Please Enter Valid NIC","ERROR");
                        }
                    }




                }else {
                    if (txtCustomerNIC.getText().equals("")){
                        Utill.notifycate("First Fill NIC","ERROR");
                    }else {
                        Utill.notifycate("NOT Valid   >'"+txtCustomerNIC.getText()+"'<   Please Enter Valid NIC","ERROR");
                    }
                }
            }
            else
            {
                if (txtCustomerName.getText().equals("")){
                    Utill.notifycate("First Fill Name","ERROR");
                }else {
                    Utill.notifycate("NOT Valid   >'"+txtCustomerName.getText()+"'<   Please Enter Valid Name","ERROR");
                }
            }



        }else {
            Utill.notifycate("Can't Add","ERROR");
        }
    }
}

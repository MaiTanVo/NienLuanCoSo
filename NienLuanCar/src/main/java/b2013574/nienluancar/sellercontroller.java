package b2013574.nienluancar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class sellercontroller implements Initializable {

    @FXML
    private Button Thanhtoan;

    @FXML
    private Button addCar_addBtn;

    @FXML
    private Button addCar_btn;

    @FXML
    private TextField addCar_car;

    @FXML
    private TextField addCar_carID;

    @FXML
    private Button addCar_clearBtn;
    @FXML
    private TableColumn<carData, String> addCar_col_idCar;
    @FXML
    private TableColumn<carData, String> addCar_col_car;

    @FXML
    private TableColumn<carData, String> addCar_col_dateInsert;

    @FXML
    private TableColumn<carData, String> addCar_col_model;

    @FXML
    private TableColumn<carData, String> addCar_col_price;

    @FXML
    private TableColumn<carData, String> addCar_col_status;

    @FXML
    private Button addCar_deleteBtn;

    @FXML
    private AnchorPane addCar_form;

    @FXML
    private TextField addCar_model;

    @FXML
    private TextField addCar_price;

    @FXML
    private ComboBox<String> addCar_status;

    @FXML
    private TableView<carData> addCar_tableView;

    @FXML
    private Button addCar_updateBtn;

    @FXML
    private Label greet_username;

    @FXML
    private Button logout;

    @FXML
    private Button thanhtoan_addBtn;

    @FXML
    private TextField thanhtoan_carid;

    @FXML
    private TextField thanhtoan_carname;

    @FXML
    private Button thanhtoan_clearBtn;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_brand;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_carid;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_date;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_gender;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_model;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_name;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_phone;

    @FXML
    private TableColumn<customerData, String> thanhtoan_col_total;

    @FXML
    private AnchorPane thanhtoan_form;

    @FXML
    private TextField thanhtoan_gender;

    @FXML
    private TextField thanhtoan_gia;

    @FXML
    private Button thanhtoan_list;

    @FXML
    private TextField thanhtoan_model;

    @FXML
    private TextField thanhtoan_name;

    @FXML
    private TextField thanhtoan_phone;

    @FXML
    private TableView<customerData> thanhtoan_tableView;

    @FXML
    private Button thanhtoan_updateBtn;

    @FXML
    private TableColumn<customerData, String> thantoan_col_customer_id;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private AlertMessage alert = new AlertMessage();

    public void ThanhtoanlistBtn() {
        addcustomerDisplayData();
        setFieldsTT();
    }

    public void addCarAddBtn() {
        connect = Database.connectDB();
        String checkExistingQuery = "SELECT COUNT(*) FROM car WHERE car_id = ? AND date_remove IS NULL";
        String sql = "INSERT INTO car (car_id, brand, model, price, status, date) " + "VALUES(?,?,?,?,?,?)";
        try {
            prepare = connect.prepareStatement(checkExistingQuery);
            prepare.setString(1, addCar_carID.getText());
            ResultSet resultSet = prepare.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                alert.thatbai("Car ID Đã tồn tại");
            } else {
                if(addCar_car.getText().isEmpty()
                        || addCar_model.getText().isEmpty()
                        || addCar_price.getText().isEmpty()
                        || addCar_status.getSelectionModel().getSelectedItem().isEmpty()){
                    alert.thatbai("Vui lòng điền đủ thông tin.");
                }else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1,addCar_carID.getText());
                    prepare.setString(2, addCar_car.getText());
                    prepare.setString(3, addCar_model.getText());
                    prepare.setString(4, addCar_price.getText());
                    prepare.setString(5, addCar_status.getSelectionModel().getSelectedItem());
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    alert.thanhcong("Thêm thành công!");

                    addCarDisplayData();

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addCarClear() {
        addCar_carID.clear();
        addCar_car.clear();
        addCar_model.clear();
        addCar_price.clear();
        addCar_status.getSelectionModel().clearSelection();
    }

    public void addCarDeleteBtn() {
        Timer timer = new Timer();
        long delay = 24 * 60 * 60 * 1000;
        long period = 24 * 60 * 60 * 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                String deleteQuery = "DELETE FROM car WHERE date_remove IS NOT NULL";
                connect = Database.connectDB();
                try {
                    prepare = connect.prepareStatement(deleteQuery);
                    prepare.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Xóa dữ liệu cũ...");
            }
        }, delay, period);
        int num = addCar_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1 ) {
            alert.thatbai("Vui lòng chọn xe");
            return;
        }else{
            if (alert.xacnhan("Bạn có chắc chắn xóa dữ liệu này không!!!")){
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String deleteData = "UPDATE car SET date_remove = ? WHERE car_id = ?";
                connect = Database.connectDB();

                try {
                    carData selectedCar = addCar_tableView.getSelectionModel().getSelectedItem();
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.setInt(2, selectedCar.getCarId());
                    prepare.executeUpdate();
                    addCarDisplayData();
                    alert.thanhcong("Xóa thành công!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addCarUpdateBtn() {
        connect = Database.connectDB();
        if (addCar_car.getText().isEmpty()
                || addCar_model.getText().isEmpty()
                || addCar_price.getText().isEmpty()
                || addCar_status.getSelectionModel().getSelectedItem().isEmpty()) {
            alert.thatbai("Vui lòng điền đủ thông tin.");
        } else {
            if (alert.xacnhan("Bạn có muốn cập nhật thông tin này không" + addCar_carID.getText() + "?")) {
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                String updateData = "UPDATE car SET car_id = ?, brand = ?, model = ?, data_update = ?, status = ? WHERE car_id = ?";
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, addCar_carID.getText());
                    prepare.setString(2, addCar_car.getText());
                    prepare.setString(3, addCar_model.getText());
                    prepare.setDate(4, sqlDate);
                    prepare.setString(5, addCar_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, addCar_carID.getText());
                    prepare.executeUpdate();
                    addCarDisplayData();
                    alert.thanhcong("Cập nhật thành công!");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alert.thatbai("Hủy");
            }
        }
    }
    public ObservableList<carData> addCarGetData(){
        ObservableList<carData> listdata = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM car WHERE date_remove IS NULL";
        connect = Database.connectDB();
        carData cData;
        try{
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            while (result.next()) {
                cData = new carData(result.getInt("car_id"),
                        result.getString("brand"),
                        result.getString("model"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getDate("date"));
                listdata.add(cData);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listdata;
    }
    private ObservableList<carData> addCarListData;
    public void addCarDisplayData(){
        addCarListData = addCarGetData();
        addCar_col_idCar.setCellValueFactory(new PropertyValueFactory<>("carId"));
        addCar_col_car.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addCar_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        addCar_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addCar_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addCar_col_dateInsert.setCellValueFactory(new PropertyValueFactory<>("date"));
        addCar_tableView.setItems(addCarListData);
    }

    public void logout(ActionEvent event) {

        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();

        try {
            if(alert.xacnhan("Đăng xuất chứ")) {
                Parent root = FXMLLoader.load(getClass().getResource("IU/login.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("Car Dealer");
                loginStage.setScene(new Scene(root));

                loginStage.show();

                currentStage.close(); // Đóng cửa sổ hiện tại
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void thanhtoanAddBtn() {
        connect = Database.connectDB();

        String sql = "INSERT INTO customer (customer_id, name, gender, phone, car_id, brand, model, total, date) " + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            if(thanhtoan_name.getText().isEmpty()
                    || thanhtoan_gender.getText().isEmpty()
                    || thanhtoan_phone.getText().isEmpty()
                    || thanhtoan_carid.getText().isEmpty()
                    || thanhtoan_carname.getText().isEmpty()
                    || thanhtoan_model.getText().isEmpty()
                    || thanhtoan_gia.getText().isEmpty()){
                alert.thatbai("Vui lòng điền đủ thông tin.");
            }else {
                String sellerNum = "4622";
                int temp_cusID = Integer.parseInt(sellerNum) + cusIDGenerator();
                prepare = connect.prepareStatement(sql);
                prepare.setString(1,String.valueOf(temp_cusID));
                prepare.setString(2,thanhtoan_name.getText());
                prepare.setString(3, thanhtoan_gender.getText());
                prepare.setString(4,thanhtoan_phone.getText());
                prepare.setString(5, thanhtoan_carid.getText());
                prepare.setString(6, thanhtoan_carname.getText());
                prepare.setString(7, thanhtoan_model.getText());
                prepare.setString(8, thanhtoan_gia.getText());
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(9, String.valueOf(sqlDate));
                prepare.executeUpdate();
                alert.thanhcong("Thêm thành công!");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void thanhtoanClearBtn() {
        thanhtoan_name.clear();
        thanhtoan_gender.clear();
        thanhtoan_phone.clear();
        thanhtoan_carid.clear();
        thanhtoan_carname.clear();
        thanhtoan_model.clear();
        thanhtoan_gia.clear();
    }

    public void thanhtoanUpdateBtn() {
        customerData cusData = thanhtoan_tableView.getSelectionModel().getSelectedItem();
        int num = thanhtoan_tableView.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1 ){
            alert.thatbai("Vui lòng chọn người dùng");
            return;
        }else {
            listdata.temp_customerID = cusData.getCustomerID();
            listdata.temp_TTName = cusData.getName();
            listdata.temp_TTGT = cusData.getGender();
            listdata.temp_TTPhone = cusData.getPhone();
            listdata.temp_carID = cusData.getCarId();
            listdata.temp_carName = cusData.getBrand();
            listdata.temp_model = cusData.getModel();
            listdata.temp_gia = cusData.getPrice();
            if (alert.xacnhan("Bạn có chắc chắn muốn xóa chứ: " + listdata.temp_TTName)) {
                try {
                    // Tạo lệnh SQL DELETE
                    String deleteData = "DELETE FROM customer WHERE customer_id = ?";

                    // Chuẩn bị và thực thi lệnh SQL DELETE
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, String.valueOf(listdata.temp_customerID));
                    prepare.executeUpdate();

                    alert.thanhcong("Xóa thành công");
                    addcustomerDisplayData();

                } catch (Exception e) {
                    e.printStackTrace();
                    alert.thatbai("Xóa thất bại");
                }
            } else {
                alert.thatbai("Hủy");
            }
        }
    }
    public ObservableList<customerData> addcustomerGetData(){
        ObservableList<customerData> listdata = FXCollections.observableArrayList();
        String ten = thanhtoan_name.getText().toString();
        if (!ten.isEmpty()){
            String selectData = "SELECT * FROM customer WHERE name = '" +  ten + "'";
            connect = Database.connectDB();
            customerData cusData;

            try{
                prepare = connect.prepareStatement(selectData);
                result = prepare.executeQuery();
                while (result.next()) {
                    cusData = new customerData(result.getInt("customer_id"),
                            result.getInt("car_id"),
                            result.getString("brand"),
                            result.getString("gender"),
                            result.getString("name"),
                            result.getInt("phone"),
                            result.getString("model"),
                            result.getDouble("total"),
                            result.getDate("date"));
                    listdata.add(cusData);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return listdata;

    }

    private ObservableList<customerData> addcustomerListData;
    public void addcustomerDisplayData(){
        addcustomerListData = addcustomerGetData();
        thantoan_col_customer_id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        thanhtoan_col_carid.setCellValueFactory(new PropertyValueFactory<>("carId"));
        thanhtoan_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        thanhtoan_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        thanhtoan_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        thanhtoan_col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        thanhtoan_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
        thanhtoan_col_total.setCellValueFactory(new PropertyValueFactory<>("price"));
        thanhtoan_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        thanhtoan_tableView.setItems(addcustomerListData);
        setFieldsTT();
    }

    public void setFieldsTT() {
        try{
            if(!thanhtoan_name.getText().isEmpty()) {
                String sql = "SELECT * FROM customer WHERE name = '" + thanhtoan_name.getText().toString() + "'";
                connect = Database.connectDB();
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                if (result.next()) {
                    if (result.getString("name") != null) {
                        thanhtoan_name.setText(thanhtoan_name.getText().toString());
                        thanhtoan_gender.setText(result.getString("gender"));
                        thanhtoan_phone.setText(result.getString("phone"));
                        thanhtoan_carid.setText(result.getString("car_id"));
                        thanhtoan_carname.setText(result.getString("brand"));
                        thanhtoan_model.setText(result.getString("model"));
                        thanhtoan_gia.setText(result.getString("total"));

                    } else {
                        alert.thatbai("Khong tim thay");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private int cusID = 0;

    public int cusIDGenerator() {

        String sql = "SELECT MAX(customer_id) FROM customer";

        connect = Database.connectDB();
        int temp_cusID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_cusID = result.getInt("MAX(customer_id)");
            }

            if (temp_cusID == 0) {
                cusID = 1;
            } else {
                cusID = temp_cusID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cusID;
    }
    public void carStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : listdata.carstatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        addCar_status.setItems(listData);
    }
    public void switchForm(ActionEvent event) {
        if (event.getSource() == addCar_btn) {
            addCar_form.setVisible(true);
            thanhtoan_form.setVisible(false);
            addCarDisplayData();
        }
        else if (event.getSource() == Thanhtoan) {
            addCar_form.setVisible(false);
            thanhtoan_form.setVisible(true);
            addcustomerDisplayData();
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        greet_username.setText("Chào Mừng " + listdata.temp_greedname);
        carStatusList();
        addcustomerDisplayData();
        addCarDisplayData();
    }
}

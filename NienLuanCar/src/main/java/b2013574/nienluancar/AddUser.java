package b2013574.nienluancar;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddUser implements Initializable{

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button user_addBtn;

    @FXML
    private DatePicker user_birthDate;

    @FXML
    private ComboBox<String> user_cn;

    @FXML
    private TextField user_email;

    @FXML
    private ComboBox<String> user_gender;

    @FXML
    private TextField user_name;

    @FXML
    private TextField user_number;

    @FXML
    private TextField user_password;

    @FXML
    private ComboBox<String> user_status;

    @FXML
    private Button user_updateBtn;

    @FXML
    private TextField user_username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    public void addBtn() {
        if(user_number.getText().isEmpty()
                || user_username.getText().isEmpty()
                || user_password.getText().isEmpty()
                || user_cn.getSelectionModel().getSelectedItem().isEmpty()){
            alert.thatbai("Vui lòng điền những thông tin có *.");
        }else {
            connect = Database.connectDB();
            try {
                if (userExists(connect, user_number.getText())) {
                    alert.thatbai("ID: " + user_number.getText() + " Đã tồn tại");
                }else {
                    String insertData = "INSERT INTO Users (user_id, Username, Password, email, " +
                            "cn, hoten, gioitinh, ngaysinh, ngaythem, trangthai)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, user_number.getText());
                    prepare.setString(2, user_username.getText());
                    prepare.setString(3, user_password.getText());
                    prepare.setString(4, user_email.getText());
                    prepare.setString(5, user_cn.getSelectionModel().getSelectedItem());
                    prepare.setString(6, user_name.getText());
                    prepare.setString(7, user_gender.getSelectionModel().getSelectedItem());
                    LocalDate birthDate = user_birthDate.getValue();
                    java.sql.Date sqlBirthDate = (birthDate != null) ? java.sql.Date.valueOf(birthDate) : null;
                    prepare.setString(8, (sqlBirthDate != null) ? String.valueOf(sqlBirthDate) : null);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(9, String.valueOf(sqlDate));
                    prepare.setString(10, user_status.getSelectionModel().getSelectedItem());
                    prepare.executeUpdate();
                    alert.thanhcong("Thêm thành công!");
                    clearFields();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    private boolean userExists(Connection connection, String userId) throws SQLException {
        // Kiểm tra xem người dùng có tồn tại hay không
        String checkUserNum = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(checkUserNum)) {
            preparedStatement.setString(1, userId);
            return preparedStatement.executeQuery().next();
        }
    }
    public void updateBtn() {
        if(user_number.getText().isEmpty()
                || user_username.getText().isEmpty()
                || user_password.getText().isEmpty()
                || user_cn.getSelectionModel().getSelectedItem().isEmpty()){
            alert.thatbai("Vui lòng điền những thông tin có *.");
        }else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            if (alert.xacnhan("Bạn có chắc chắn muốn thay đổi  chứ: "
                    + user_number.getText())){
                String updateData = "UPDATE Users SET "
                        + "hoten = ?, ngaysinh = ?, gioitinh = ?, trangthai = ?, Password = ?, email = ?, ngaycapnhat = ?"
                        + " WHERE user_id = ?";
                try{
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, user_name.getText());
                    LocalDate birthDate = user_birthDate.getValue();
                    if (birthDate != null) {
                        prepare.setDate(2, java.sql.Date.valueOf(birthDate));
                    } else {
                        prepare.setNull(2, java.sql.Types.DATE);
                    }
                    prepare.setString(3, user_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, user_status.getSelectionModel().getSelectedItem());
                    prepare.setString(5, user_password.getText());
                    prepare.setString(6, user_email.getText());
                    prepare.setString(7, String.valueOf(sqlDate));
                    prepare.setString(8, user_number.getText());
                    prepare.executeUpdate();
                    alert.thanhcong("Cập nhật thành công");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                alert.thatbai("Hủy");
            }
        }
    }
    public void setFields() {
        try{
            if(listdata.temp_userNumber != null){
                String sql = "SELECT * FROM Users WHERE user_id = '" + listdata.temp_userNumber + "'";
                connect = Database.connectDB();
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                if (result.next()){
                    if (result.getString("Username")!=null){
                        user_number.setText(listdata.temp_userNumber);
                        user_username.setText(result.getString("Username"));
                        user_password.setText(result.getString("Password"));
                        user_email.setText(result.getString("email"));
                        user_cn.getSelectionModel().select(result.getString("cn"));
                        user_name.setText(result.getString("hoten"));
                        String dateString = result.getString("ngaysinh");
                        LocalDate birthDate = null;
                        if (dateString != null && !dateString.isEmpty()) {
                            birthDate = LocalDate.parse(dateString);
                        }
                        user_birthDate.setValue(birthDate);
                        user_gender.getSelectionModel().select(result.getString("gioitinh"));
                        user_status.getSelectionModel().select(result.getString("trangthai"));

                    }else {
                        user_number.setText(listdata.temp_userNumber);
                        user_status.getSelectionModel().select(result.getString("trangthai"));
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void gioitinhList() {
        List<String> listS = new ArrayList<>();

        for (String data : listdata.gioitinh) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        user_gender.setItems(listData);
    }
    public void cnList(){
        List<String> listcn = new ArrayList<>();
        for (String data : listdata.cn){
            listcn.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listcn);
        user_cn.setItems(listData);
    }
    public void statusList(){
        List<String> listtt = new ArrayList<>();
        for (String data : listdata.trangthai){
            listtt.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listtt);
        user_status.setItems(listData);
    }
    public void clearFields() {
        user_number.clear();
        user_username.clear();
        user_password.clear();
        user_email.clear();
        user_cn.getSelectionModel().clearSelection();
        user_birthDate.setValue(null);
        user_name.clear();
        user_gender.getSelectionModel().clearSelection();
        user_status.getSelectionModel().clearSelection();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gioitinhList();
        cnList();
        statusList();
        if(listdata.temp_userCN.toString().equals("Update")){
        setFields();
        }
    }
}

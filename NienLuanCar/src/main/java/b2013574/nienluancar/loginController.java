package b2013574.nienluancar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class loginController implements Initializable
{


    @FXML
    private Button Login_btn;

    @FXML
    private ComboBox<String> Login_cn;

    @FXML
    private PasswordField Login_password;

    @FXML
    private TextField Login_username;

    @FXML
    private Button admin_btnregister;

    @FXML
    private PasswordField admin_cpassword;

    @FXML
    private Hyperlink admin_dn;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button seller_btnregister;

    @FXML
    private PasswordField seller_cpassword;

    @FXML
    private Hyperlink seller_dn;

    @FXML
    private TextField seller_email;

    @FXML
    private AnchorPane seller_form;

    @FXML
    private PasswordField seller_password;

    @FXML
    private TextField seller_username;

    @FXML
    private Button user_btnregister;

    @FXML
    private PasswordField user_cpassword;

    @FXML
    private Hyperlink user_dn;

    @FXML
    private TextField user_email;

    @FXML
    private AnchorPane user_form;

    @FXML
    private PasswordField user_password;

    @FXML
    private TextField user_username;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private AlertMessage alert = new AlertMessage();

    public void loginAccount() {
        if (Login_username.getText().isEmpty() || Login_password.getText().isEmpty()) {
            alert.thatbai("Vui lòng điền đầy đủ thông tin");
        } else {
            String selectData = "SELECT * FROM Users WHERE username = ? AND password = ?";
            connect = Database.connectDB();
            String role = "";
            String greedname = "";
            ResultSet loginResult = null; // Sử dụng biến khác để lưu kết quả truy vấn

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, Login_username.getText());
                prepare.setString(2, Login_password.getText());
                loginResult = prepare.executeQuery(); // Lưu kết quả vào biến mới

                if (loginResult.next()) {
                    if (loginResult.getString("ngayxoa") == null) {
                        role = loginResult.getString("cn");
                        greedname = loginResult.getString("Username");
                        listdata.temp_greedname = greedname;

                        Thread.sleep(1000);

                        if (role.equals("Admin")) {
                            listdata.admin_username = Login_username.getText();
                            Parent root = FXMLLoader.load(getClass().getResource("IU/Admin.fxml"));
                            Stage stage = new Stage();
                            stage.setTitle("Car dealer System | Admin Portal");
                            stage.setScene(new Scene(root));
                            stage.show();
                            Login_btn.getScene().getWindow().hide();
                        } else if (role.equals("Seller")) {
                            String tempsellerID = loginResult.getString("user_id");
                            String checkData = "SELECT * FROM Users WHERE user_id = '" + tempsellerID + "'";
                            statement = connect.createStatement();
                            ResultSet sellerResult = statement.executeQuery(checkData); // Kết quả truy vấn mới
                            if (sellerResult.next()) {
                                String trangthai = sellerResult.getString("trangthai");
                                if (trangthai.equals("yeucau")) {
                                    alert.thatbai("Cần sự phê duyệt của admin!");
                                } else if (trangthai.equals("nguoidung")) {
                                    alert.thatbai("ERROR!!!!");
                                } else {
                                    listdata.seller_username = Login_username.getText();
                                    Parent root = FXMLLoader.load(getClass().getResource("IU/seller.fxml"));
                                    Stage stage = new Stage();
                                    stage.setTitle("Car dealer System | Seller Portal");
                                    stage.setScene(new Scene(root));
                                    stage.show();
                                    Login_btn.getScene().getWindow().hide();
                                }
                            }
                        }
                    } else {
                        alert.thatbai("Hiện tài khoản đã bị xóa");
                    }
                } else {
                    alert.thatbai("Sai Tài khoản/Mật khẩu");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Đóng ResultSet nếu cần thiết
                try {
                    if (loginResult != null) {
                        loginResult.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerAdmin(){
        if(admin_username.getText().isEmpty() || admin_password.getText().isEmpty() ||admin_cpassword.getText().isEmpty()){
            alert.thatbai("Vui lòng điền đầy đủ thông tin không để trống");
        }else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM Users WHERE username = '" + admin_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);
                if (result.next()) {
                    alert.thatbai(admin_username.getText() + "Đã tồn tại");
                } else if (!admin_password.getText().equals(admin_cpassword.getText())) {
                    alert.thatbai("Mật khẩu không trùng khớp vui lòng nhập lại");
                } else if (admin_password.getText().length() < 8) {
                    alert.thatbai("Mật khẩu quá ngắn ít nhất phải hơn 8 kí tự");
                } else {
                    String insertData = "INSERT INTO Users (username, password, cn, ngaythem, user_id) " + "VALUES(?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, admin_username.getText());
                    prepare.setString(2, admin_password.getText());
                    prepare.setString(3, "Admin");
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.setString(5, "0");

                    prepare.executeUpdate();

                    alert.thanhcong("Đăng ký thành công!");

                    login_form.setVisible(true);
                    admin_form.setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void registerseller() {

        if (seller_email.getText().isEmpty()
                || seller_username.getText().isEmpty()
                || seller_password.getText().isEmpty()
                || seller_cpassword.getText().isEmpty()) {
            alert.thatbai("Vui lòng điền đầy đủ thông tin");
        } else {
            connect = Database.connectDB();

            String selectData = "SELECT * FROM Users WHERE username = '" + seller_username.getText() + "'";

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(selectData);

                if (result.next()) {
                    alert.thatbai(seller_username.getText() + " tên này đã tồn tại");
                } else if (!seller_password.getText().equals(seller_cpassword.getText())) {
                    alert.thatbai("Mật khẩu không trùng khớp.");
                } else if (seller_password.getText().length() < 8) {
                    alert.thatbai("Độ dài mật khẩu phải nhiều hơn 8");
                } else {

                    String insertData = "INSERT INTO Users " + "(email, username, password, cn, user_id, ngaythem,trangthai) "
                            + "VALUES(?,?,?,?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    SimpleDateFormat format = new SimpleDateFormat("yyyy");
                    String getYear = format.format(date);
                    String sellerNum = getYear + "4620";

                    int temp_sellerID = Integer.parseInt(sellerNum) + sellerIDGenerator();

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, seller_email.getText());
                    prepare.setString(2, seller_username.getText());
                    prepare.setString(3, seller_password.getText());
                    prepare.setString(4, "Seller");
                    prepare.setString(5,  String.valueOf(temp_sellerID));
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.setString(7, "yeucau");
                    prepare.executeUpdate();

                    alert.thanhcong("Đăng ký thành công!");

                    login_form.setVisible(true);
                    seller_form.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int sellerID = 0;

    public int sellerIDGenerator() {

        String sql = "SELECT MAX(id) FROM Users WHERE cn = 'Seller'";

        connect = Database.connectDB();
        int temp_sellerID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_sellerID = result.getInt("MAX(id)");
            }

            if (temp_sellerID == 0) {
                sellerID = 1;
            } else {
                sellerID = temp_sellerID + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sellerID;
    }

    public void loginform(){
        login_form.setVisible(true);
        admin_form.setVisible(false);
        seller_form.setVisible(false);
    }
    public void switchForm(ActionEvent event){
        switch (Login_cn.getSelectionModel().getSelectedItem()) {
            case "Admin" -> {
                login_form.setVisible(false);
                admin_form.setVisible(true);
                seller_form.setVisible(false);
            }
            case "Người bán" -> {
                login_form.setVisible(false);
                admin_form.setVisible(false);
                seller_form.setVisible(true);
            }
        }
    }
    public void cnlist(){
        List<String> ListCN = new ArrayList<>();
        for (String data : listdata.cn){
            ListCN.add(data);
        }
        ObservableList listData  = FXCollections.observableArrayList(ListCN);
        Login_cn.setItems(listData);
    }
    public void initialize(URL url, ResourceBundle rb){
        cnlist();
    }
}

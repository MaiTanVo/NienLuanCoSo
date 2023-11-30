package b2013574.nienluancar;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessage {
    private Alert alert;

    public void thanhcong(String tinnhan){
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo tin nhắn: ");
        alert.setHeaderText(null);
        alert.setContentText(tinnhan);
        alert.show();
    }
    public void thatbai(String tinnhan){
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Thông báo lỗi: ");
        alert.setContentText(tinnhan);
        alert.show();
    }
    public boolean xacnhan(String tinnhan){
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo thành công");
        alert.setHeaderText(null);
        alert.setContentText(tinnhan);
        Optional<ButtonType> option = alert.showAndWait();

        return option.get().equals(ButtonType.OK);

    }
}

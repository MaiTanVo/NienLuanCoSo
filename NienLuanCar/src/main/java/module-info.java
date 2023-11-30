module b2013574.nienluancar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens b2013574.nienluancar to javafx.fxml;
    exports b2013574.nienluancar;
}